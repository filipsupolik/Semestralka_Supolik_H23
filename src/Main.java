import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        DualnaVsuvaciaHeuristika heuristika = new DualnaVsuvaciaHeuristika("DataFiles/H6_a.txt", "DataFiles/H6_c.txt");
        heuristika.spustiDualnuVsuvaciuHeuristiku();
        DualnaVymennaHeuristika vymennaHeuristika = new DualnaVymennaHeuristika(heuristika.getBatoh(), heuristika.getNezaradenePredmety(), heuristika.getHUF(), heuristika.getHmotnostPredmetovVBatohu(), heuristika.getPocetPrvkovVBatohu());
        vymennaHeuristika.spustiDualnuVymennuHeuristiku();
        try {
            heuristika.dajVystup();
            vymennaHeuristika.dajVystup();
        } catch (IOException e) {
            System.out.println("Nastala chyba pri zapise do suboru: " + e.getMessage());
        }
    }
}
