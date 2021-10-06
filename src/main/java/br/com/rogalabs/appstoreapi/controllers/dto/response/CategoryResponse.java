package br.com.rogalabs.appstoreapi.controllers.dto.response;

import br.com.rogalabs.appstoreapi.domain.Category;

/**
 * @author Anderson on 06/10/2021
 * @project app-store-api
 */
public class CategoryResponse {

    private Long id;
    private String name;

    public static CategoryResponse converter(Category category) {
        var categoryRs = new CategoryResponse();
        categoryRs.setId(category.getId());
        categoryRs.setName(category.getName());
        return categoryRs;
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
}
