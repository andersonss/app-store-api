package br.com.rogalabs.appstoreapi.controllers;

import br.com.rogalabs.appstoreapi.controllers.dto.request.AppRequest;
import br.com.rogalabs.appstoreapi.controllers.dto.response.AppResponse;
import br.com.rogalabs.appstoreapi.domain.App;
import br.com.rogalabs.appstoreapi.service.AppService;
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

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/all")
    public List<AppResponse> getAllApps() {
        var apps = appService.getAllApps();
        return apps
                .stream()
                .map(AppResponse::converter)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AppResponse findAppById(@PathVariable("id") Long id) throws Exception {
        var app = appService.findAppById(id);

        if (app.isPresent()) {
            return AppResponse.converter(app.get());
        }

        throw new Exception("App not found");
    }

    @PostMapping("/")
    public AppResponse addNewApp(@RequestBody AppRequest appRequest) {
        //TODO save category
        //TODO save publisher
        var newApp = new App();
        newApp.setName(appRequest.getName());
        newApp.setDescription(appRequest.getDescription());
        newApp.setPrice(appRequest.getPrice());
        return AppResponse.converter(appService.addOrUpdateApp(newApp));
    }

    @PutMapping("/{id}")
    public AppResponse updateApp(@PathVariable("id") Long id, @RequestBody AppRequest appRequest) throws Exception {
        var app = appService.findAppById(id);

        if (app.isPresent()) {
            var appToUpdate = app.get();
            appToUpdate.setName(appRequest.getName());
            appToUpdate.setDescription(appRequest.getDescription());
            appToUpdate.setPrice(appRequest.getPrice());
            return AppResponse.converter(appService.addOrUpdateApp(appToUpdate));
        } else {
            //TODO create an customizable exception
            throw new Exception("App not found");
        }
    }
}
