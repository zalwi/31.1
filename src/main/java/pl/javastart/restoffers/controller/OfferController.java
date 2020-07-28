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

    @GetMapping("/")
    public List<OfferWithCategoryName> getAll(@RequestParam(required = false) String title) {
        if (StringUtils.isEmpty(title)) {
            return offerService.getAll();
        } else {
            return offerService.getAllByTitle(title);
        }
    }

    @GetMapping("/count")
    public Long countOffers(@RequestParam(required = false) String title) {
        return offerService.countOffers();
    }

    @PostMapping
    public void create(@RequestBody OfferWithCategoryName offerWithCategoryName) {
        offerService.generateAndAddNewOffer(offerWithCategoryName);
    }

    @GetMapping("/count")
    public List<String> getCategoryNames() {
        return offerService.categoryNames();
    }
}
