public class Personagem {
    protected int x;
    protected int y;

    public Personagem(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void mover(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
