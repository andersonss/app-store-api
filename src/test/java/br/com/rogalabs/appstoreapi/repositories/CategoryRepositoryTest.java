package br.com.rogalabs.appstoreapi.repositories;

import br.com.rogalabs.appstoreapi.AbstractTest;
import br.com.rogalabs.appstoreapi.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Anderson on 07/10/2021
 * @project app-store-api
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CategoryRepositoryTest extends AbstractTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        Category foodCategory = new Category(1L, "Food");
        Category educationCategory = new Category(2L, "Education");
        Category gameCategory = new Category(3L, "Game");
        Category financeCategory = new Category(4L, "Finance");
        categoryRepository.saveAll(Arrays.asList(foodCategory, educationCategory, gameCategory, financeCategory));
    }

    @Test
    public void shouldSave() {
        Category entertainmentCategory = new Category(null, "Entertainment");
        Category categorySaved = categoryRepository.save(entertainmentCategory);
        assertThat(categorySaved).usingRecursiveComparison().ignoringFields("id").isEqualTo(entertainmentCategory);
    }

    @Test
    public void shouldFindAll() {
        var allCategories = categoryRepository.findAll();
        assertThat(allCategories.size()).isEqualTo(4);
    }

    @Test
    public void shouldFindById() {
        Optional<Category> byId = categoryRepository.findById(2L);
        assertThat(byId.isPresent()).isTrue();
        assertThat(byId.get().getName()).isEqualTo("Education");
    }

}
