package Offer_Service.service;

import Offer_Service.dto.CustomerDTO;
import Offer_Service.dto.OfferResponse;
import Offer_Service.utill.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    @Autowired
    private CustomerClient customerClient;

    public List<OfferResponse> getOffersByCibilScore(String cibilScore) {
        List<CustomerDTO> customers = customerClient.getCustomersByCibilScore(cibilScore);

        return customers.stream().map(customer -> {
            OfferResponse offer = new OfferResponse();
            String fullName = customer.getFirstName() + " " + customer.getLastName();
            offer.setFullName(fullName);

            int score = Integer.parseInt(customer.getCibilScore());
            if (score >= 750) {
                offer.setOfferType("Platinum Credit Card");
                offer.setCreditLimit(150000);
            } else if (score >= 650) {
                offer.setOfferType("Gold Credit Card");
                offer.setCreditLimit(80000);
            } else if (score >= 550) {
                offer.setOfferType("Silver Credit Card");
                offer.setCreditLimit(30000);
            } else {
                offer.setOfferType("No Offer");
                offer.setCreditLimit(0);
            }
            return offer;
        }).collect(Collectors.toList());
    }
}
