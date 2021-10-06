package br.com.rogalabs.appstoreapi.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Anderson on 05/10/2021
 * @project app-store-api
 */
@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @OneToMany
    @JoinColumn(name = "publisher_id")
    private Set<App> apps;

    public Publisher() {
    }

    public Publisher(Long id, String name) {
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

    public Set<App> getApps() {
        return apps;
    }

    public void setApps(Set<App> apps) {
        this.apps = apps;
    }
}
