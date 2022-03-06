package model;

public class Position {
    Integer positionId;

    public Position() {
        this.positionId = 0;
    }

    public Position(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}
