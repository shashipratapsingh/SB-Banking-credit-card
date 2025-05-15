package Offer_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferResponse {
    private String fullName;
    private String offerType;
    private int creditLimit;
}
