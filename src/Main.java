import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        DualnaVsuvaciaHeuristika heuristika = new DualnaVsuvaciaHeuristika("DataFiles/H6_a.txt", "DataFiles/H6_c.txt");
        heuristika.spustiDualnuVsuvaciuHeuristiku();
        try {
            heuristika.dajVystup();
        } catch (IOException e) {
            System.out.println("Nastala chyba pri zapise do suboru: " + e.getMessage());
        }

        heuristika.spustiDualnuVymennuHeuristiku();
        try {
            heuristika.dajVystupVymena();
        } catch (IOException e) {
            System.out.println("Nastala chyba pri zapise do suboru: " + e.getMessage());
        }

        System.out.println();
    }
}
