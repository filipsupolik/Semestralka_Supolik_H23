import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DualnaVymennaHeuristika {
    private FileLoader fileLoader;

    private int hmotnostPredmetovVBatohu;
    private int pocetPrvkovVBatohu;
    private int HUF;

    private List<Predmet> predmety;
    private List<Predmet> batoh;
    private FileWriter fileWriter;

    public DualnaVymennaHeuristika(String suborHmotnosti, String suborCeny) {

        this.fileLoader = new FileLoader();
        this.HUF = 0;
        this.predmety =

        this.pocetPrvkovVBatohu = 0;
        this.batoh = new ArrayList<>();
    };
}
