public class Predmet {
    private int hmotnostPredmetu;
    private int cenaPredmetu;
    private int indexPredTriedenim;

    public Predmet(int hmotnostPredmetu, int cenaPredmetu, int indexPredTriedenim) {
        this.hmotnostPredmetu = hmotnostPredmetu;
        this.cenaPredmetu = cenaPredmetu;
        this.indexPredTriedenim = indexPredTriedenim;
    }

    public int getHmotnostPredmetu() {
        return hmotnostPredmetu;
    }

    public int getCenaPredmetu() {
        return cenaPredmetu;
    }

    public int getIndexPredTriedenim() {
        return indexPredTriedenim;
    }
}
