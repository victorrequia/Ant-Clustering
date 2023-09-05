package source;

import java.io.IOException;

import javax.swing.JFrame;

import source.view.AntClustering;

public class Main {
    public static void main(String[] args) {
        try {
            final JFrame frame = new JFrame("Ant Clustering");
            final AntClustering game;
            game = new AntClustering();
            frame.add(game);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
