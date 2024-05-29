public class OffByN implements CharacterComparator{
    private int disc;
    public OffByN(int disc) {
        this.disc = disc;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == disc;
    }
}
