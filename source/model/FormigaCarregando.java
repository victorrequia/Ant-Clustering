package source.model;

import java.awt.Point;

public class FormigaCarregando extends Formiga {
    Item item;

    public FormigaCarregando(int WIDTH, int HEIGHT, int UNIT_SIZE, Item item) {
        super(WIDTH, HEIGHT, UNIT_SIZE);
        this.item = item;
    }

    public FormigaCarregando(int WIDTH, int HEIGHT, int UNIT_SIZE, Point point, Item item) {
        super(WIDTH, HEIGHT, UNIT_SIZE, point);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    
}
