package Customer_Service.entity;
import Customer_Service.Enums.CustomerStatus;
import Customer_Service.Enums.CustomerType;
import Customer_Service.Enums.KycStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String uniqueCustomerId;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String mobileNumber;
    private LocalDate dateOfBirth;
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerType customerType;  // INDIVIDUAL or CORPORATE
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerStatus customerStatus;  // ACTIVE, INACTIVE, BLOCKED
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KycStatus kycStatus;   // PENDING, VERIFIED, REJECTED
    // Dates
    @Column(nullable = false, updatable = false)
    private LocalDateTime onboardingDate;
    // Profile/Document Reference
    private String profileImageUrl;     // stored as reference to S3/file store
    private String documentReferenceId; // points to DocumentService
    private String incomeRange;
    private String cibilScore;
    // Metadata
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.onboardingDate = now;
        this.createdBy = "ADMIN";
    }
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "ADMIN";
    }
}