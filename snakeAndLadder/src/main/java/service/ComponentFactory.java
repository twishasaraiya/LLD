package service;

import model.ComponentType;
import model.GameComponent;

public class ComponentFactory {

    public static GameComponent buildComponent(Integer head, Integer tail, ComponentType type){
        switch (type){
            case SNAKE:
                return new GameComponent(head, tail, ComponentType.SNAKE);
            case LADDER:
                return new GameComponent(head, tail, ComponentType.LADDER);
        }
        return null;
    }
}
