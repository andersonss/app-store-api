package br.com.rogalabs.appstoreapi.service;

import br.com.rogalabs.appstoreapi.domain.App;
import br.com.rogalabs.appstoreapi.domain.Category;
import br.com.rogalabs.appstoreapi.repositories.AppRepository;
import br.com.rogalabs.appstoreapi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Anderson on 06/10/2021
 * @project app-store-api
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final AppRepository appRepository;

    public CategoryService(CategoryRepository categoryRepository, AppRepository appRepository) {
        this.categoryRepository = categoryRepository;
        this.appRepository = appRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long categoryId) throws Exception {
        Optional<Category> categorybyId = categoryRepository.findById(categoryId);
        if (categorybyId.isPresent()) {
            return categorybyId.get();
        } else throw new Exception("Category not found");
    }

    public Category addOrUpdateCategory(Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    public List<Category> addNewCategories(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    public void addNewAppToCategory(Long categoryId, App app) throws Exception {
        App newApp = appRepository.save(app);

        Optional<Category> categoryById = categoryRepository.findById(categoryId);

        if (categoryById.isPresent()) {
            Category category = categoryById.get();
            category.addAppToCategory(newApp);
            categoryRepository.save(category);

            newApp.setCategory(category);
            appRepository.save(newApp);
        } else {
            throw new Exception("Category not found");
        }
    }

    public void addAllNewAppsToCategory(Long categoryId, List<App> apps) throws Exception {
        for (App app : apps) {
            addNewAppToCategory(categoryId, app);
        }
    }

    public App findCheapestAppOfCategory(Long categoryId) throws Exception {
        Optional<Category> categoryById = categoryRepository.findById(categoryId);
        if (categoryById.isPresent()) {
            Category category = categoryById.get();

            return category.getAppsOfCategory()
                    .stream()
                    .min(Comparator.comparing(App::getPrice))
                    .orElseThrow(NoSuchElementException::new);

        }
        throw new Exception("Category not found");
    }
}
