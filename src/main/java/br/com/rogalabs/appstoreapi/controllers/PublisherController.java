package br.com.rogalabs.appstoreapi.controllers;

import br.com.rogalabs.appstoreapi.controllers.dto.request.PublisherRequest;
import br.com.rogalabs.appstoreapi.controllers.dto.response.PublisherResponse;
import br.com.rogalabs.appstoreapi.domain.Publisher;
import br.com.rogalabs.appstoreapi.service.PublisherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anderson on 06/10/2021
 * @project app-store-api
 */
@RestController
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/all")
    public List<PublisherResponse> getAllPublishers() {
        var publishers = publisherService.getAllPublishers();
        return publishers
                .stream()
                .map(PublisherResponse::converter)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PublisherResponse findPublisherById(@PathVariable("id") Long id) throws Exception {
        var publisher = publisherService.findPublisherById(id);

        if (publisher.isPresent()) {
            return PublisherResponse.converter(publisher.get());
        }

        throw new Exception("Publisher not found");
    }

    @PostMapping("/")
    public void addNewPublisher(@RequestBody PublisherRequest publisherRequest) {
        var newPublisher = new Publisher();
        newPublisher.setName(publisherRequest.getName());
        publisherService.addOrUpdatePublisher(newPublisher);
    }

    @PutMapping("/{id}")
    public PublisherResponse updatePublisher(@PathVariable("id") Long id,
                                             @RequestBody PublisherRequest publisherRequest) throws Exception {
        var publisher = publisherService.findPublisherById(id);

        if (publisher.isPresent()) {
            var publisherToUpdate = publisher.get();
            publisherToUpdate.setName(publisherRequest.getName());
            return PublisherResponse.converter(publisherService.addOrUpdatePublisher(publisherToUpdate));
        } else {
            //TODO create an customizable exception
            throw new Exception("Publisher not found");
        }
    }
}
