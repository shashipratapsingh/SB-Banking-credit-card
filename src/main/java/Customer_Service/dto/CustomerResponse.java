package Customer_Service.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String uniqueCustomerId;
    private String fullName;
    private String productType;
    private String message;
}