package model;

public enum HintCodes {
    C("correct"),
    I("partially_correct"),
    P("incorrect");

    private String meaning;

    HintCodes(String meaning) {
        this.meaning = meaning;
    }

    public String getMeaning() {
        return meaning;
    }
}
