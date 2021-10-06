package br.com.rogalabs.appstoreapi.controllers;

import br.com.rogalabs.appstoreapi.controllers.dto.request.CategoryRequest;
import br.com.rogalabs.appstoreapi.controllers.dto.response.CategoryResponse;
import br.com.rogalabs.appstoreapi.domain.Category;
import br.com.rogalabs.appstoreapi.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anderson on 06/10/2021
 * @project app-store-api
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<CategoryResponse> getAllCategories() {
        var categories = categoryService.getAllCategories();
        return categories
                .stream()
                .map(CategoryResponse::converter)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryResponse findCategoryById(@PathVariable("id") Long id) throws Exception {
        var category = categoryService.findCategoryById(id);

        if (category.isPresent()) {
            return CategoryResponse.converter(category.get());
        }

        throw new Exception("Category not found");
    }

    @PostMapping("/")
    public void addNewCategory(@RequestBody CategoryRequest categoryRequest) {
        var newCategory = new Category();
        newCategory.setName(categoryRequest.getName());
        categoryService.addOrUpdateCategory(newCategory);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateApp(@PathVariable("id") Long id, @RequestBody CategoryRequest categoryRequest) throws Exception {
        var category = categoryService.findCategoryById(id);

        if (category.isPresent()) {
            var categoryToUpdate = category.get();
            categoryToUpdate.setName(categoryRequest.getName());
            return CategoryResponse.converter(categoryService.addOrUpdateCategory(categoryToUpdate));
        } else {
            //TODO create an customizable exception
            throw new Exception("Category not found");
        }
    }
}
