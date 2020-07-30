package pl.javastart.restoffers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.restoffers.model.Category;
import pl.javastart.restoffers.model.Offer;
import pl.javastart.restoffers.model.OfferWithCategoryName;
import pl.javastart.restoffers.repository.CategoryRepository;
import pl.javastart.restoffers.repository.OfferRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
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

    public OfferWithCategoryName getOfferById(Long id){
        Optional<Offer> optionalOfferById = offerRepository.findById(id);
        Offer offer = optionalOfferById.get();
        if(offer == null){
            return null;
        }else{
            return new OfferWithCategoryName(offer);
        }
    }

    public List<OfferWithCategoryName> getAll(){
        List<Offer> all = offerRepository.findAll();
        return all.stream()
                .map(offer -> new OfferWithCategoryName(offer))
                .collect(Collectors.toList());
    }

    public List<OfferWithCategoryName> getAllByTitle(String title){
        List<Offer> all = offerRepository.findAllByTitleIgnoreCaseContains(title);
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

    public void deleteOfferById(Long id) {
        offerRepository.deleteById(id);
    }
}
