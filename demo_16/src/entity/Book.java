package entity;

public class Book {
    private String id;
    private String name;
    private String title;
    private int pages;
    private String author;
    private double price;
    private int typeId;
    private boolean status;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getTypeId() { return typeId; }
    public void setTypeId(int typeId) { this.typeId = typeId; }
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}