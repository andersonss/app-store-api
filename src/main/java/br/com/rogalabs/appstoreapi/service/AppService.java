package br.com.rogalabs.appstoreapi.service;

import br.com.rogalabs.appstoreapi.domain.App;
import br.com.rogalabs.appstoreapi.repositories.AppRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Anderson on 06/10/2021
 * @project app-store-api
 */
@Service
public class AppService {

    private final AppRepository appRepository;

    public AppService(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public List<App> findAll() {
        return appRepository.findAll();
    }

    public Optional<App> findById(Long id) {
        return appRepository.findById(id);
    }

    public App addOrUpdateApp(App newApp) {
        return appRepository.save(newApp);
    }

}
