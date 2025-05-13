package Customer_Service.repository;
import Customer_Service.Enums.CustomerType;
import Customer_Service.Enums.KycStatus;
import Customer_Service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByUniqueCustomerId(String findByUniqueCustomerId);
    Optional<Customer> findByMobileNumber(String mobileNumber);
    List<Customer> findByKycStatus(KycStatus kycStatus);
    List<Customer> findByCustomerType(CustomerType customerType);
}