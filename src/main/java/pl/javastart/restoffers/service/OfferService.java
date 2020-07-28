package pl.javastart.restoffers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.restoffers.model.Category;
import pl.javastart.restoffers.model.Offer;
import pl.javastart.restoffers.model.OfferWithCategoryName;
import pl.javastart.restoffers.repository.CategoryRepository;
import pl.javastart.restoffers.repository.OfferRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    OfferRepository offerRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<OfferWithCategoryName> getAll(){
        List<Offer> all = offerRepository.findAll();
        return all.stream()
                .map(offer -> new OfferWithCategoryName(offer))
                .collect(Collectors.toList());
    }

    public List<OfferWithCategoryName> getAllByTitle(String title){
        List<Offer> all = offerRepository.findAllByTitle(title);
        return all.stream()
                .map(offer -> new OfferWithCategoryName(offer))
                .collect(Collectors.toList());
    }

    public Long countOffers(){
        return offerRepository.count();
    }

    public void generateAndAddNewOffer(OfferWithCategoryName offerWithCategoryName){
        Category tmpCategory = categoryRepository.findByName(offerWithCategoryName.getCategoryName());
        Offer addOffer = new Offer();

        addOffer.setTitle(offerWithCategoryName.getTitle());
        addOffer.setDescription(offerWithCategoryName.getDescription());
        addOffer.setImgUrl(offerWithCategoryName.getImgUrl());
        addOffer.setPrice(offerWithCategoryName.getPrice());
        addOffer.setCategory(tmpCategory);

        offerRepository.save(addOffer);
    }

    public List<String> categoryNames(){
       return categoryRepository.findAll().stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }
}
