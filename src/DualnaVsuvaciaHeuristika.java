import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DualnaVsuvaciaHeuristika {

    private int hmotnostPredmetovVBatohu;
    private int pocetPrvkovVBatohu;
    private int HUF;

    private PriorityQueue<Predmet> zoznamDoposialNespracovanychPredmetov;
    private List<Predmet> batoh;
    private List<Predmet> nezaradenePredmety;

    public DualnaVsuvaciaHeuristika(String suborHmotnosti, String suborCeny) {
        FileLoader fileLoader = new FileLoader();
        this.HUF = 0;
        try{
            this.zoznamDoposialNespracovanychPredmetov = fileLoader.nacitajPrvky(suborHmotnosti, suborCeny);
        } catch (IOException e) {
            System.out.println("Nastala chyba pri citani suboru: " + e.getMessage());
        }

        this.pocetPrvkovVBatohu = 0;
        this.batoh = new ArrayList<>();
        this.nezaradenePredmety = new ArrayList<>();
    };

    public void spustiDualnuVsuvaciuHeuristiku() {
        while (!this.zoznamDoposialNespracovanychPredmetov.isEmpty()) {
            if (this.pocetPrvkovVBatohu >= 250 && this.hmotnostPredmetovVBatohu >= 10000) {
                Predmet nezaradenyPredmet = this.zoznamDoposialNespracovanychPredmetov.poll();
                this.nezaradenePredmety.add(nezaradenyPredmet);
            }
            Predmet vkladanyPredmet = this.zoznamDoposialNespracovanychPredmetov.poll();
            if (vkladanyPredmet == null) break;
            this.batoh.add(vkladanyPredmet);
            this.HUF += vkladanyPredmet.getCenaPredmetu();
            this.hmotnostPredmetovVBatohu += vkladanyPredmet.getHmotnostPredmetu();
            this.pocetPrvkovVBatohu++;
        }
    }

    public void spustiDualnuVymennuHeuristiku() {
        boolean zlepsenie;
        do {
            zlepsenie = false;
            int najvacsiRozdiel = 0;
            int najlepsiIn = 0;
            int najhorsiOut = 0;
            for (int i = 0; i < batoh.size(); i++) {
                for (int j = 0; j < nezaradenePredmety.size(); j++) {
                    int aktualnaHUF = this.HUF - this.batoh.get(i).getCenaPredmetu() + nezaradenePredmety.get(j).getCenaPredmetu();
                    int aktualnaHmotnostPredmetov = this.hmotnostPredmetovVBatohu - this.batoh.get(i).getHmotnostPredmetu() + nezaradenePredmety.get(j).getHmotnostPredmetu();
                    if (aktualnaHUF <= this.HUF && aktualnaHmotnostPredmetov >= 10000) {
                        int rozdiel = this.HUF - aktualnaHUF;
                        if (rozdiel > najvacsiRozdiel) {
                            najvacsiRozdiel = rozdiel;
                            najlepsiIn = j;
                            najhorsiOut = i;
                            zlepsenie = true;
                        }
                    }
                }
            }

            if (zlepsenie) {
                Predmet out = this.batoh.get(najhorsiOut);
                Predmet in = this.nezaradenePredmety.get(najlepsiIn);

                this.batoh.add(in);
                this.batoh.remove(out);

                this.nezaradenePredmety.add(out);
                this.nezaradenePredmety.remove(in);

                this.HUF = this.HUF - out.getCenaPredmetu() + in.getCenaPredmetu();
                this.hmotnostPredmetovVBatohu = this.hmotnostPredmetovVBatohu - out.getHmotnostPredmetu() + in.getHmotnostPredmetu();
            }
        } while (zlepsenie);
    }

    public void dajVystup() throws IOException {
        FileWriter fileWriter = new FileWriter("vystup.txt");
        System.out.println("HUF = " + this.HUF);

        try (BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write("Pocet predmetov = " + this.pocetPrvkovVBatohu);
            writer.newLine();
            writer.write("Hmotnost = " + this.hmotnostPredmetovVBatohu);
            writer.newLine();
            writer.write("HUF = " + this.HUF);
            writer.newLine();
            writer.newLine();

            for (Predmet p : this.batoh) {
                writer.write(String.valueOf(p.getIndexPredTriedenim()));
                writer.write(" ");
            }
        }
    }

    public void dajVystupVymena() throws IOException {
        FileWriter fileWriter = new FileWriter("vystupVymena.txt");
        System.out.println("HUF = " + this.HUF);

        try (BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write("Pocet predmetov = " + this.pocetPrvkovVBatohu);
            writer.newLine();
            writer.write("Hmotnost = " + this.hmotnostPredmetovVBatohu);
            writer.newLine();
            writer.write("HUF = " + this.HUF);
            writer.newLine();
            writer.newLine();

            for (Predmet p : this.batoh) {
                writer.write(String.valueOf(p.getIndexPredTriedenim()));
                writer.write(" ");
            }
        }
    }
}
