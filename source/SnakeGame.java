package source;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int UNIT_SIZE = 20;
    private final int DELAY = 100;

    private ArrayList<Point> snake;
    private ArrayList<Point> food;
    private boolean isRunning = false;

    public SnakeGame() {
        snake = new ArrayList<>();
        food = new ArrayList<>();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        startGame();
    }

    public void startGame() {
        snake.clear();
        snake.add(new Point(UNIT_SIZE, UNIT_SIZE));
        snake.add(new Point(UNIT_SIZE+20, UNIT_SIZE+20));
        createFood();
        isRunning = true;
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    public void createFood() {
        for(int i=0; i<10; i++){
            int x = new Random().nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
            int y = new Random().nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
            food.add(new Point(x, y));
        }
    }

    public void checkCollision() {
        Point head = snake.get(0);
        if (head.equals(food)) {
            createFood();
        }

        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) {
            isRunning = false;
        }

        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                isRunning = false;
                break;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (isRunning) {
            g.setColor(Color.GREEN);
            for (Point point : snake) {
                g.fillRect(point.x, point.y, UNIT_SIZE, UNIT_SIZE);
            }
            g.setColor(Color.RED);
        for(int i=0; i<10; i++){
            int x = new Random().nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
            int y = new Random().nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
            food.add(new Point(x, y));
        
            g.fillRect(food.get(i).x , food.get(i).y , UNIT_SIZE, UNIT_SIZE);
        }
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            checkCollision();
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