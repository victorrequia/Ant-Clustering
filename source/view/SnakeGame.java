package source.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import source.model.Formiga;
import source.model.Item;
import source.controller.Controlador;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SnakeGame extends JPanel implements ActionListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int UNIT_SIZE = 20;
    private Image bgImage;

    public static ArrayList<Formiga> carregando = new ArrayList<>();
    private Image formigaImage;
    private Image formigaCarregandoImage;
    private Image folhaImage;
    private ArrayList<Formiga> formigas = new ArrayList<>();
    private ArrayList<Item> itens = new ArrayList<>();
    private Controlador controller = new Controlador();
    private char direcao = 'U';
    private boolean isRunning = false;
    private final int DELAY = 200;

    public SnakeGame() throws IOException {
        bgImage = ImageIO.read(new File("source/images/textura-do-fundo-da-estrada-de-terra-128043659.jpg"));
        formigaImage = ImageIO.read(new File("source/images/formiga.png"));
        folhaImage = ImageIO.read(new File("source/images/folha.png"));
        formigaCarregandoImage = ImageIO.read(new File("source/images/formiga_carregando.png"));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        startGame();
    }

    public void startGame() {
        controller.criarFormigas(formigas, WIDTH, HEIGHT, UNIT_SIZE);
        controller.criarItens(itens, WIDTH, HEIGHT, UNIT_SIZE);
        isRunning = true;
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
        draw(g);
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
                for (Point point : item.getPontos()) {
                    g.drawImage(folhaImage, point.x, point.y, UNIT_SIZE, UNIT_SIZE, null);
                }
            }

            g.setColor(Color.BLUE);
            for (Formiga formiga : carregando) {
                for (Point point : formiga.getPontos()) {
                    g.drawImage(formigaCarregandoImage, point.x, point.y, UNIT_SIZE, UNIT_SIZE, null);
                }
            }
        
    }

    public void move() {
        for (Formiga formiga : formigas) {
            Point head = formiga.getPontos().get(0);
            direcao = controller.move(head, direcao, UNIT_SIZE, HEIGHT, WIDTH);
        }
    }

    public void decisao() {
        for (Formiga formiga : formigas) {
            Point head = formiga.getPontos().get(0);
            controller.decisao(formiga, itens, head);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            move();
            decisao();
            repaint();
        }
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Ant Clustering");
        SnakeGame game = new SnakeGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}