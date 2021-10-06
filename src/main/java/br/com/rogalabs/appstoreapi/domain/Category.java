package br.com.rogalabs.appstoreapi.domain;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToMany
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

}
