package Customer_Service.dto;
import Customer_Service.Enums.CustomerStatus;
import Customer_Service.Enums.CustomerType;
import Customer_Service.Enums.KycStatus;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private LocalDate dateOfBirth;
    private String address;
    private CustomerType customerType;  // INDIVIDUAL or CORPORATE
    private CustomerStatus customerStatus; // ACTIVE, INACTIVE, BLOCKED
    private KycStatus kycStatus;  // PENDING, VERIFIED, REJECTED
    private LocalDateTime onboardingDate;
    private String profileImageUrl;
    private String documentReferenceId;
    private String uniqueCustomerId;
    private String incomeRange;
}