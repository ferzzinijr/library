package features.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    private int id;
    private String author;
    private String name;
    private boolean is_available;
    private int reserved_days;

    public Book() {}

    public Book(int id, String author, String name, boolean isAvailable, int reservedDays) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.is_available = isAvailable;
        this.reserved_days = reservedDays;
    }

    public int getId() {return this.id;}
    public String getAuthor() {return this.author;}
    public String getName() {return this.name;}
    public int getReservedDays() {return this.reserved_days;}

    public boolean isAvailable() {return this.is_available;}

    public void setAvailable() {this.is_available = true;}

    public void setReservedDays(int days) {this.reserved_days = days;}
}
