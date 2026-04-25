import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DualnaVsuvaciaHeuristika {
    private FileLoader fileLoader;

    private int hmotnostPredmetovVBatohu;
    private int pocetPrvkovVBatohu;
    private int HUF;

    private PriorityQueue<Predmet> zoznamDoposialNespracovanychPredmetov;
    private List<Predmet> batoh;
    private FileWriter fileWriter;

    public DualnaVsuvaciaHeuristika(String suborHmotnosti, String suborCeny) {
        this.fileLoader = new FileLoader();
        this.HUF = 0;
        try{
            this.zoznamDoposialNespracovanychPredmetov = this.fileLoader.nacitajPrvky(suborHmotnosti, suborCeny);
        } catch (IOException e) {
            System.out.println("Nastala chyba pri citani suboru: " + e.getMessage());
        }

        this.pocetPrvkovVBatohu = 0;
        this.batoh = new ArrayList<>();
    };

    public void spustiDualnuVsuvaciuHeuristiku() {
        while (!this.zoznamDoposialNespracovanychPredmetov.isEmpty()) {
            if (this.pocetPrvkovVBatohu >= 250 && this.hmotnostPredmetovVBatohu >= 10000) {
                break;
            }
            Predmet vkladanyPredmet = this.zoznamDoposialNespracovanychPredmetov.poll();
            if (vkladanyPredmet == null) break;
            this.batoh.add(vkladanyPredmet);
            this.HUF += vkladanyPredmet.getCenaPredmetu();
            this.hmotnostPredmetovVBatohu += vkladanyPredmet.getHmotnostPredmetu();
            this.pocetPrvkovVBatohu++;
        }
    }

    public void dajVystup() throws IOException {
        this.fileWriter = new FileWriter("vystup.txt");
        System.out.println("HUF = " + this.HUF);

        try (BufferedWriter writer = new BufferedWriter(this.fileWriter)) {
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
