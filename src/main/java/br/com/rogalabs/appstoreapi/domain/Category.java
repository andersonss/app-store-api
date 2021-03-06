package br.com.rogalabs.appstoreapi.domain;

import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Anderson on 03/10/2021
 * @project app-store-api
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Set<App> appsOfCategory;

    public Category() {
        appsOfCategory = new HashSet<>();
    }

    public Category(Long id, String name) {
        this.appsOfCategory = new HashSet<>();
        this.id = id;
        this.name = name;
    }

    public void addAppToCategory(App app) {
        this.getAppsOfCategory().add(app);
    }

    public void addAppToCategory(List<App> apps) {
        this.getAppsOfCategory().addAll(apps);
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

    public Set<App> getAppsOfCategory() {
        return appsOfCategory;
    }

    public void setAppsOfCategory(Set<App> appsOfCategory) {
        this.appsOfCategory = appsOfCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != null ? !id.equals(category.id) : category.id != null) return false;
        return name != null ? name.equals(category.name) : category.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
