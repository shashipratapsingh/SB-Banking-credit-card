package Offer_Service.controller;

import Offer_Service.dto.OfferResponse;
import Offer_Service.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping("/cibil/{score}")
    public List<OfferResponse> getCreditCardOffers(@PathVariable("score") String score) {
        return offerService.getOffersByCibilScore(score);
    }
}
