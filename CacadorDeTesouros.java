import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CacadorDeTesouros extends JPanel {
    private Jogador jogador;
    private List<ObjetoColetavel> objetos;
    private boolean vitoria;
    private static final int TAMANHO_JANELA = 600;
    private static final int TAMANHO_OBJETO = 20;

    public CacadorDeTesouros() {
        jogador = new Jogador(50, 50);
        objetos = new ArrayList<>();
        gerarObjetosColetaveis(3);
        vitoria = false;

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!vitoria) {
                    int key = e.getKeyCode();
                    switch (key) {
                        case KeyEvent.VK_LEFT:
                            jogador.mover(-10, 0);
                            break;
                        case KeyEvent.VK_RIGHT:
                            jogador.mover(10, 0);
                            break;
                        case KeyEvent.VK_UP:
                            jogador.mover(0, -10);
                            break;
                        case KeyEvent.VK_DOWN:
                            jogador.mover(0, 10);
                            break;
                    }
                    verificarColeta();
                    repaint();
                }
            }
        });
    }

    private void gerarObjetosColetaveis(int quantidade) {
        Random random = new Random();
        for (int i = 0; i < quantidade; i++) {
            int x = random.nextInt(550 / TAMANHO_OBJETO) * TAMANHO_OBJETO;
            int y = random.nextInt(550 / TAMANHO_OBJETO) * TAMANHO_OBJETO;
            objetos.add(new ObjetoColetavel(x, y));
        }
    }

    private void verificarColeta() {
        List<ObjetoColetavel> coletados = new ArrayList<>();
        for (ObjetoColetavel obj : objetos) {
            if (jogador.getX() == obj.getX() && jogador.getY() == obj.getY()) {
                jogador.coletarObjeto();
                coletados.add(obj);
            }
        }
        objetos.removeAll(coletados);

        if (objetos.isEmpty()) {
            vitoria = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (vitoria) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Você Venceu!", 200, 300);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(jogador.getX(), jogador.getY(), TAMANHO_OBJETO, TAMANHO_OBJETO);

            g.setColor(Color.RED);
            for (ObjetoColetavel obj : objetos) {
                g.fillRect(obj.getX(), obj.getY(), TAMANHO_OBJETO, TAMANHO_OBJETO);
            }

            g.setColor(Color.BLACK);
            g.drawString("Pontuação: " + jogador.getPontuacao(), 10, 10);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Caçador de Tesouros");
        CacadorDeTesouros jogo = new CacadorDeTesouros();
        frame.add(jogo);
        frame.setSize(TAMANHO_JANELA, TAMANHO_JANELA);
        frame.setResizable(false);  // Janela com tamanho fixo
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
