package source.controller;

import java.util.ArrayList;

import source.model.*;

public class Controlador {

    public void criarFormigas(ArrayList<Formiga> itens, int WIDTH, int HEIGHT, int UNIT_SIZE) {
        for (int i = 0; i < 10; i++) {
            itens.add(new Formiga(WIDTH, HEIGHT, UNIT_SIZE));
        }
    }

    public void criarItens(ArrayList<Item> itens, int WIDTH, int HEIGHT, int UNIT_SIZE) {
        for (int i = 0; i < 10; i++) {
            itens.add(new Item(WIDTH, HEIGHT, UNIT_SIZE));
        }
    }
}
