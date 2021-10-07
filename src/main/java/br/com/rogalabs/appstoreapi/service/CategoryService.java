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

    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category addOrUpdateCategory(Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    public void addNewAppToCategory(Long id, App app) throws Exception {
        Optional<Category> categoryById = categoryRepository.findById(id);
        if (categoryById.isPresent()) {
            Category category = categoryById.get();
            category.addAppToCategory(app);
            categoryRepository.save(category);
            app.setCategory(category);
            appRepository.save(app);
        } else {
            throw new Exception("Category not found");
        }
    }

    public App findCheapestAppOfCategory(Long id) throws Exception {
        Optional<Category> categoryById = categoryRepository.findById(id);
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
