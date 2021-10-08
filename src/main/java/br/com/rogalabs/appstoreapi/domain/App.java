package br.com.rogalabs.appstoreapi.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * @author Anderson on 03/10/2021
 * @project app-store-api
 */
@Entity
public class App {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    private String description;

    private float price;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Publisher publisher;

    public App() {
    }

    public App(Long id, String name, String description, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        App app = (App) o;

        if (Float.compare(app.price, price) != 0) return false;
        if (id != null ? !id.equals(app.id) : app.id != null) return false;
        if (name != null ? !name.equals(app.name) : app.name != null) return false;
        if (description != null ? !description.equals(app.description) : app.description != null) return false;
        if (category != null ? !category.equals(app.category) : app.category != null) return false;
        return publisher != null ? publisher.equals(app.publisher) : app.publisher == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        return result;
    }
}
