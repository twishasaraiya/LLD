package model;


public class Rack implements Comparable<Rack>{
    private final Integer id;// 1 , 2, 3, 4
    private Integer bookCopyId;

    public Rack(Integer id) {
        this.id = id;
        bookCopyId = null;
    }

    public Integer getId() {
        return id;
    }

    public void setBookCopyId(Integer bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public Integer getBookCopyId() {
        return bookCopyId;
    }


    @Override
    public int compareTo(Rack rack) {
        return id.compareTo(rack.getId());
    }
}
