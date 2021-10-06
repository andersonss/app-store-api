package br.com.rogalabs.appstoreapi.controllers.dto;

import br.com.rogalabs.appstoreapi.domain.App;

/**
 * @author Anderson on 05/10/2021
 * @project app-store-api
 */
public class AppResponse {

    private Long id;
    private String name;
    private String description;
    private float price;

    public static AppResponse converter(App app) {
        var appRs = new AppResponse();
        appRs.setId(app.getId());
        appRs.setName(app.getName());
        appRs.setDescription(app.getDescription());
        appRs.setPrice(app.getPrice());
        return appRs;
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
}
