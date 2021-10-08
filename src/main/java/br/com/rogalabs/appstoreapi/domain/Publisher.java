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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Publisher publisher = (Publisher) o;
//
//        if (id != null ? !id.equals(publisher.id) : publisher.id != null) return false;
//        if (name != null ? !name.equals(publisher.name) : publisher.name != null) return false;
//        return apps != null ? apps.equals(publisher.apps) : publisher.apps == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (apps != null ? apps.hashCode() : 0);
//        return result;
//    }
}
