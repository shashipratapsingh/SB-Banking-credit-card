package Customer_Service.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String fullName;
    private String email;
    private String mobileNumber;
    private String city;
    private String incomeRange;
    private String productType;
}