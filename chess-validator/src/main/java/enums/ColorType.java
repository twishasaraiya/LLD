package enums;

public enum ColorType {
    WHITE("W"),
    BLACK("B");

    private String colorType;

    ColorType(String colorType) {
        this.colorType = colorType;
    }

    public String getType() {
        return this.colorType;
    }

    public ColorType getColorType(String c) {
        for (ColorType ct: ColorType.values()) {
            if(ct.getType().equals(c)) return ct;
        }

        return null;
    }
}
