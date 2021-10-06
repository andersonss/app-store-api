package br.com.rogalabs.appstoreapi.controllers.dto.response;

import br.com.rogalabs.appstoreapi.domain.Publisher;

/**
 * @author Anderson on 06/10/2021
 * @project app-store-api
 */
public class PublisherResponse {

    private Long id;
    private String name;

    public static PublisherResponse converter(Publisher publisher) {
        var publisherRs = new PublisherResponse();
        publisherRs.setId(publisher.getId());
        publisherRs.setName(publisher.getName());
        return publisherRs;
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
