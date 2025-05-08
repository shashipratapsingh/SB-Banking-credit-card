package Customer_Service.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String mobileNumber;
    private String city;
    private String incomeRange;
    private String productType;
    private String uniqueCustomerId;
}