package Customer_Service.dto;
import Customer_Service.Enums.CustomerType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String uniqueCustomerId;
    private String firstName;
    private CustomerType customerType;
    private String message;
    private String cibiScore;


}