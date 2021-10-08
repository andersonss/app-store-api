package br.com.rogalabs.appstoreapi.controllers;

import br.com.rogalabs.appstoreapi.controllers.dto.request.AppRequest;
import br.com.rogalabs.appstoreapi.controllers.dto.request.CategoryRequest;
import br.com.rogalabs.appstoreapi.controllers.dto.response.AppResponse;
import br.com.rogalabs.appstoreapi.controllers.dto.response.CategoryResponse;
import br.com.rogalabs.appstoreapi.domain.App;
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
        return CategoryResponse.converter(category);
    }

    @PostMapping("/")
    public void addNewCategory(@RequestBody CategoryRequest categoryRequest) {
        var newCategory = new Category();
        newCategory.setName(categoryRequest.getName());
        categoryService.addOrUpdateCategory(newCategory);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable("id") Long categoryId, @RequestBody CategoryRequest categoryRequest)
            throws Exception {
        var category = categoryService.findCategoryById(categoryId);
        //TODO keep apps from category
        category.setName(categoryRequest.getName());
        return CategoryResponse.converter(categoryService.addOrUpdateCategory(category));
    }

    @PostMapping("/{id}")
    public void addNewAppToCategory(@PathVariable("id") Long categoryId, @RequestBody AppRequest appRequest) throws Exception {
        var newApp = new App();
        newApp.setName(appRequest.getName());
        newApp.setDescription(appRequest.getDescription());
        newApp.setPrice(appRequest.getPrice());
        categoryService.addNewAppToCategory(categoryId, newApp);
    }

    @GetMapping("/cheapest-app/{id}")
    public AppResponse findCheapestAppOfCategory(@PathVariable("id") Long categoryId) throws Exception {
        return AppResponse.converter(categoryService.findCheapestAppOfCategory(categoryId));
    }
}
