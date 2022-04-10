package model;

public class ExpenseMetaData {
    private String expenseName;
    private String description;
    private String imageUrl;
    private String notes;

    public ExpenseMetaData() {
    }

    public ExpenseMetaData(String expenseName, String description, String imageUrl, String notes) {
        this.expenseName = expenseName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.notes = notes;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNotes() {
        return notes;
    }
}
