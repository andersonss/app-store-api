package br.com.rogalabs.appstoreapi.controllers;

import br.com.rogalabs.appstoreapi.controllers.dto.AppResponse;
import br.com.rogalabs.appstoreapi.repositories.AppRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anderson on 05/10/2021
 * @project app-store-api
 */
@RestController
@RequestMapping("/app")
public class AppController {

    private final AppRepository appRepository;

    public AppController(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @GetMapping("/")
    public List<AppResponse> findAll() {
        var apps = appRepository.findAll();
        return apps
                .stream()
                .map(AppResponse::converter)
                .collect(Collectors.toList());
    }
}
