package pl.javastart.restoffers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.restoffers.model.Category;
import pl.javastart.restoffers.model.CategoryWithCounter;
import pl.javastart.restoffers.repository.CategoryRepository;
import pl.javastart.restoffers.repository.OfferRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;
    OfferRepository offerRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, OfferRepository offerRepository) {
        this.categoryRepository = categoryRepository;
        this.offerRepository = offerRepository;
    }

    public List<String> categoryNames(){
        return categoryRepository.findAll().stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    public List<CategoryWithCounter> allCategoriesWithOffersCount(){
        List<CategoryWithCounter> categoryWithCounts = new ArrayList<>();
        List<Category> allCategories = categoryRepository.findAll();
        for(Category cat: allCategories){
            Long offers = offerRepository.countByCategory_Id(cat.getId());
            categoryWithCounts.add(new CategoryWithCounter(cat.getName(), cat.getDescription(), offers));
        }
        return categoryWithCounts;
    }

    public void addNewCategory(Category category){
        categoryRepository.save(category);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
