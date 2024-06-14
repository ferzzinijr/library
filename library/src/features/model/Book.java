package features.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String author;
    private String name;
    private boolean is_available;
    private int reserved_days;

    public Book() {}

    public Book(String author, String name, boolean isAvailable, int reservedDays) {
        this.author = author;
        this.name = name;
        this.is_available = isAvailable;
        this.reserved_days = reservedDays;
    }

    public int getId() {return this.id;}
    public String getAuthor() {return this.author;}
    public String getName() {return this.name;}
    public int getReservedDays() {return this.reserved_days;}
    public void setAuthor(String author) {this.author = author;}
    public void setName(String name) {this.name = name;}

    public boolean isAvailable() {return this.is_available;}

    public void setAvailable(boolean isAvailable) {this.is_available = isAvailable;}

    public void setReservedDays(int days) {this.reserved_days = days;}
}
