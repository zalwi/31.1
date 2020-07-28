package pl.javastart.restoffers.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OfferWithCategoryName {
        long id;
        String title;
        String description;
        String imgUrl;
        BigDecimal price;
        String categoryName;

        public OfferWithCategoryName(Offer offer) {
            this.id = offer.getId();
            this.title = offer.getTitle();
            this.description = offer.getDescription();
            this.imgUrl = offer.getImgUrl();
            this.price = offer.getPrice();
            this.categoryName = offer.getCategory().getName();
        }
    }
