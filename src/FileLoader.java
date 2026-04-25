import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FileLoader {
    public PriorityQueue<Predmet> nacitajPrvky(String cestaHmotnosti, String cestaCeny) throws IOException {
        List<String> riadkyHmotnosti = Files.readAllLines(Paths.get(cestaHmotnosti));
        List<String> riadkyCien = Files.readAllLines(Paths.get(cestaCeny));

        if (riadkyHmotnosti.size() != riadkyCien.size()) {
            throw new IllegalArgumentException("Počet riadkov v súboroch sa nezhoduje!");
        }

       PriorityQueue<Predmet> predmety = new PriorityQueue<>(Comparator.comparingDouble(p -> (double) p.getCenaPredmetu() /p.getHmotnostPredmetu()));
        for (int i = 0; i < riadkyHmotnosti.size(); i++) {
            int hmotnost = Integer.parseInt(riadkyHmotnosti.get(i).trim());
            int cena = Integer.parseInt(riadkyCien.get(i).trim());
            predmety.add((new Predmet(hmotnost, cena, i+1)));
        }

        return predmety;
    }
}
