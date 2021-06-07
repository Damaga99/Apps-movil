package es.imovildani.persistencia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

public class Book implements Serializable {
    private String title, author, isbn, editorial;
    private float price;

    public Book(@NonNull String title, @NonNull String author, @Nullable String isbn, @Nullable String editorial, @NonNull float price) {
        this.title = title;
        this.author = author;
        if (isbn != null)
            this.isbn = isbn;
        else
            this.isbn ="(null)";
        if (editorial != null)
            this.editorial = editorial;
        else
            this.editorial = "(null";
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }




}
