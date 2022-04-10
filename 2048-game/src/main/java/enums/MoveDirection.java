package enums;

public enum MoveDirection {
    LEFT,
    RIGHT,
    TOP,
    BOTTOM;

    public static MoveDirection getDirection(int direction){
        for(MoveDirection moveDirection: MoveDirection.values()){
            if(moveDirection.ordinal() == direction){
                return moveDirection;
            }
        }
        return null;
    }
}
