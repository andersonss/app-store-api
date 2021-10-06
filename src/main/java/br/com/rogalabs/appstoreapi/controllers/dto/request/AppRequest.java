package br.com.rogalabs.appstoreapi.controllers.dto.request;

import br.com.rogalabs.appstoreapi.domain.App;

/**
 * @author Anderson on 05/10/2021
 * @project app-store-api
 */
public class AppRequest {

    private String name;
    private String description;
    private float price;

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
