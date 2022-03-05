package enums;
public enum DisplayType {
    FREE_COUNT("free_count"),
    FREE_SLOTS("free_slots"),
    OCCUPIED_SLOTS("occupied_slots");

    private String type;
    DisplayType(String type){
        this.type = type;
    }
    public String getType(){ return this.type; }

    public static DisplayType getDisplayType(String displayType){
        for(DisplayType type: DisplayType.values()){
            if(displayType.equals(type.getType())){
                return type;
            }
        }
        return null;
    }
}
