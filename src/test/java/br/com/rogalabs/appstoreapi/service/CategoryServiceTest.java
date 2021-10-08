package br.com.rogalabs.appstoreapi.service;

import br.com.rogalabs.appstoreapi.AbstractTest;
import br.com.rogalabs.appstoreapi.domain.App;
import br.com.rogalabs.appstoreapi.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * @author Anderson on 07/10/2021
 * @project app-store-api
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CategoryServiceTest extends AbstractTest {

    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService(categoryRepository, appRepository);

        Category foodCategory = new Category(1L, "Food");
        Category educationCategory = new Category(2L, "Education");
        Category gameCategory = new Category(3L, "Game");
        Category financeCategory = new Category(4L, "Finance");
        categoryService.addNewCategories(Arrays.asList(foodCategory, educationCategory, gameCategory, financeCategory));
    }

    @Test
    public void addNewAppToCategory() throws Exception {
        App app = new App(null, "New App", "The new App", 5);
        categoryService.addNewAppToCategory(4L, app);
        Category category = categoryService.findCategoryById(4L);
        assertThat(category.getName()).isEqualTo("Finance");
        var newApp = category.getAppsOfCategory().stream().findFirst();
        if (newApp.isPresent()) {
            assertThat(newApp.get()).usingRecursiveComparison().isEqualTo(app);
        } else fail("App not added do the category");

    }

    @Test
    public void findCheapestAppOfCategory() throws Exception {
        App app1 = new App(null, "Cheap App", "The Cheap App", 2);
        App app3 = new App(null, "Some App", "Some App", 5);
        App app2 = new App(null, "Expensive App", "The Expensive App", 98);
        categoryService.addAllNewAppsToCategory(3L, Arrays.asList(app1, app2, app3));

        App cheapestAppOfCategory = categoryService.findCheapestAppOfCategory(3L);

        assertThat(cheapestAppOfCategory)
                .extracting(App::getId, App::getName, App::getPrice, app -> app.getCategory().getName())
                .containsExactly(app1.getId(), app1.getName(), app1.getPrice(), app1.getCategory().getName());
    }
}
