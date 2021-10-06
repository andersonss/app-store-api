package br.com.rogalabs.appstoreapi.controllers;

import br.com.rogalabs.appstoreapi.controllers.dto.AppRequest;
import br.com.rogalabs.appstoreapi.controllers.dto.AppResponse;
import br.com.rogalabs.appstoreapi.domain.App;
import br.com.rogalabs.appstoreapi.repositories.AppRepository;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public List<AppResponse> findAll() {
        var apps = appRepository.findAll();
        return apps
                .stream()
                .map(AppResponse::converter)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AppResponse findById(@PathVariable("id") Long id) {
        var app = appRepository.getById(id);
        return AppResponse.converter(app);
    }

    @PostMapping("/")
    public void addNewApp(@RequestBody AppRequest appRequest) {
        //TODO save category
        //TODO save publisher
        var newApp = new App();
        newApp.setName(appRequest.getName());
        newApp.setDescription(appRequest.getDescription());
        newApp.setPrice(newApp.getPrice());
        appRepository.save(newApp);
    }

    @PutMapping("/{id}")
    public void updateApp(@PathVariable("id") Long id, @RequestBody AppRequest appRequest) throws Exception {
        var app = appRepository.findById(id);

        if (app.isPresent()) {
            var appToUpdate = app.get();
            appToUpdate.setName(appToUpdate.getName());
            appToUpdate.setDescription(appToUpdate.getDescription());
            appToUpdate.setPrice(appToUpdate.getPrice());
        } else {
            //TODO create an customizable exception
            throw new Exception("App not found");
        }
    }
}
