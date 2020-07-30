package pl.javastart.restoffers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pl.javastart.restoffers.service.OfferService;
import pl.javastart.restoffers.model.OfferWithCategoryName;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public List<OfferWithCategoryName> getAll(@RequestParam(required = false) String title) {
        System.out.println("title: " + title);
        if (StringUtils.isEmpty(title)) {
            return offerService.getAll();
        } else {
            return offerService.getAllByTitle(title);
        }
    }

    @GetMapping("/{id}")
    public OfferWithCategoryName get(@PathVariable Long id) {
        return offerService.getOfferById(id);
    }

    @GetMapping("/count")
    public Long countOffers() {
        return offerService.countOffers();
    }

    @PostMapping
    public void create(@RequestBody OfferWithCategoryName offerWithCategoryName) {
        offerService.generateAndAddNewOffer(offerWithCategoryName);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        offerService.deleteOfferById(id);
    }

}
