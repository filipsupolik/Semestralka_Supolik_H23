import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DualnaVymennaHeuristika {
    private int hmotnostPredmetovVBatohu;
    private int pocetPrvkovVBatohu;
    private int HUF;

    private List<Predmet> predmety;
    private List<Predmet> batoh;
    private FileWriter fileWriter;

    public DualnaVymennaHeuristika(List<Predmet> batoh, List<Predmet> nezaradenePredmety, int huf, int hmotnostPredmetovVBatohu, int pocetPrvkovVBatohu) {
        this.predmety = nezaradenePredmety;
        this.batoh = batoh;
        this.HUF = huf;
        this.hmotnostPredmetovVBatohu = hmotnostPredmetovVBatohu;
        this.pocetPrvkovVBatohu = pocetPrvkovVBatohu;
    };

    public void spustiDualnuVymennuHeuristiku() {
        boolean zlepsenie;
        do {
            zlepsenie = false;
            int najvacsiRozdiel = 0;
            int najlepsiIn = 0;
            int najhorsiOut = 0;
            for (int i = 0; i < batoh.size(); i++) {
                for (int j = 0; j < predmety.size(); j++) {
                    int aktualnaHUF = this.HUF - this.batoh.get(i).getCenaPredmetu() + predmety.get(j).getCenaPredmetu();
                    int aktualnaHmotnostPredmetov = this.hmotnostPredmetovVBatohu - this.batoh.get(i).getHmotnostPredmetu() + predmety.get(j).getHmotnostPredmetu();
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
                Predmet in = this.predmety.get(najlepsiIn);

                this.batoh.add(in);
                this.batoh.remove(out);

                this.predmety.add(out);
                this.predmety.remove(in);

                this.HUF = this.HUF - out.getCenaPredmetu() + in.getCenaPredmetu();
                this.hmotnostPredmetovVBatohu = this.hmotnostPredmetovVBatohu - out.getHmotnostPredmetu() + in.getHmotnostPredmetu();
            }
        } while (zlepsenie);
    }

    public void dajVystup() throws IOException {
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

    public List<Predmet> getBatoh() {
        return batoh;
    }
}
