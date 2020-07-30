package pl.javastart.restoffers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.javastart.restoffers.model.Category;
import pl.javastart.restoffers.model.CategoryForm;
import pl.javastart.restoffers.model.CategoryWithCounter;
import pl.javastart.restoffers.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryWithCounter> getCategories() {
        return categoryService.allCategoriesWithOffersCount();
    }

    @GetMapping("/names")
    public List<String> getCategoryNames() {
        return categoryService.categoryNames();
    }

    @PostMapping
    void saveNewCategory(@RequestBody CategoryForm categoryForm){
        Category category = new Category();
        category.setName(categoryForm.getName());
        category.setDescription(categoryForm.getDescription());
        categoryService.addNewCategory(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }
}