package br.com.rogalabs.appstoreapi.service;

import br.com.rogalabs.appstoreapi.domain.Publisher;
import br.com.rogalabs.appstoreapi.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Anderson on 06/10/2021
 * @project app-store-api
 */
@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> findPublisherById(Long id) {
        return publisherRepository.findById(id);
    }

    public Publisher addOrUpdatePublisher(Publisher newPublisher) {
        return publisherRepository.save(newPublisher);
    }
}
