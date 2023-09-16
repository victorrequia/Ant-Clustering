package source.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import source.controller.Controlador;
import source.model.Formiga;
import source.model.Item;

public class AntClustering extends JPanel implements ActionListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int UNIT_SIZE = 20;
    private Image bgImage;

    private ArrayList<Formiga> formigasCarregando = new ArrayList<>();
    private Image formigaImage;
    private Image formigaCarregandoImage;
    private Image folhaImage;
    private Image cupcakeImage;
    private ArrayList<Formiga> formigas;
    private ArrayList<Item> itens;
    private Controlador controller;
    private char direcao = 'U';
    private final int DELAY = 100;
    private int iteracoes = 0;

    public AntClustering() throws IOException {
        formigas = new ArrayList<>();
        itens = new ArrayList<>();
        controller = new Controlador(WIDTH, HEIGHT, UNIT_SIZE);
        bgImage = ImageIO.read(new File("source/images/background.jpg"));
        formigaImage = ImageIO.read(new File("source/images/formiga.png"));
        folhaImage = ImageIO.read(new File("source/images/folha.png"));
        cupcakeImage = ImageIO.read(new File("source/images/cupcake.png"));
        formigaCarregandoImage = ImageIO.read(new File("source/images/formiga_carregando.png"));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        startGame();
    }

    public void startGame() {
        controller.criarFormigas(formigas, 5);
        controller.criarItens(itens, 30);
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
        draw(g);
        drawIterationCounter(g);
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        for (Formiga formiga : formigas) {
            for (Point point : formiga.getPontos()) {
                g.drawImage(formigaImage, point.x, point.y, UNIT_SIZE, UNIT_SIZE, null);
            }
        }

        g.setColor(Color.RED);
        for (Item item : itens) {
            g.drawImage(folhaImage, (int) item.getPonto().getX(), (int) item.getPonto().getY(), UNIT_SIZE, UNIT_SIZE,
                    null);
        }

        g.setColor(Color.BLUE);
        for (Formiga formiga : formigasCarregando) {
            for (Point point : formiga.getPontos()) {
                g.drawImage(formigaCarregandoImage, point.x, point.y, UNIT_SIZE, UNIT_SIZE, null);
            }
        }
    }

    public void moveFormigas() {
        direcao = controller.move(formigas, direcao);
    }

    public void moveFormigasCarregando() {
        direcao = controller.move(formigasCarregando, direcao);
    }

    public void decisaoPegar() {
        controller.decisaoPegar(formigas, formigasCarregando, itens);
    }

    public void decisaoLargar() {
        controller.decisaoLargar(formigas, itens, formigasCarregando);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveFormigas();
        decisaoPegar();
        decisaoLargar();
        moveFormigasCarregando();
        iteracoes++;
        repaint();
    }

    private void drawIterationCounter(Graphics g) {
        g.setColor(Color.BLACK); // Define a cor do texto para branco
        g.setFont(new Font("Arial", Font.BOLD, 16)); // Define a fonte
        g.drawString("Iterações: " + iteracoes, 10, 20); // Desenha o texto no canto superior esquerdo
    }
}