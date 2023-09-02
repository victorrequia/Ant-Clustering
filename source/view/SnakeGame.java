package source.view;

import javax.swing.*;

import source.model.Formiga;
import source.model.Item;
import source.controller.Controlador;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int UNIT_SIZE = 20;

    private Random random = new Random();
    private ArrayList<Formiga> formigas = new ArrayList<>();
    private ArrayList<Item> itens = new ArrayList<>();
    private Controlador controller = new Controlador();
    private char direcao = 'U';
    private boolean isRunning = false;
    private final int DELAY = 100;

    public SnakeGame() {
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
        draw(g);
    }

    public void draw(Graphics g) {
        if (isRunning) {
            g.setColor(Color.GREEN);
            for (Formiga formiga : formigas) {
                for (Point point : formiga.getPontos()) {
                    g.fillRect(point.x, point.y, UNIT_SIZE, UNIT_SIZE);
                }
            }

            g.setColor(Color.RED);
            for (Item item : itens) {
                for (Point point : item.getPontos()) {
                    g.fillRect(point.x, point.y, UNIT_SIZE, UNIT_SIZE);
                }
            }

        }
    }

    public void move() {
        for(Formiga formiga : formigas){
            Point head = formiga.getPontos().get(0);
            direcao = controller.move(head, direcao, UNIT_SIZE, HEIGHT, WIDTH);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            move();
            repaint();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ant Clustering");
        SnakeGame game = new SnakeGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}