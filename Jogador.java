public class Jogador extends Personagem {
    private int pontuacao;

    public Jogador(int x, int y) {
        super(x, y);
        this.pontuacao = 0;
    }

    public void coletarObjeto() {
        pontuacao += 10;
    }

    public int getPontuacao() {
        return pontuacao;
    }
}
