package model;

public class GameComponent {
    private Integer head;
    private Integer tail;
    private ComponentType componentType;

    public GameComponent(int head, int tail, ComponentType componentType) {
        this.head = head;
        this.tail = tail;
        this.componentType = componentType;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }


}
