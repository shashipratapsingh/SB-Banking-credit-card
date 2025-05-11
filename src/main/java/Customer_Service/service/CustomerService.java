package Customer_Service.service;
import Customer_Service.dto.CustomerRequest;
import Customer_Service.dto.CustomerResponse;
import Customer_Service.entity.Customer;
import Customer_Service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public CustomerResponse registerCustomer(CustomerRequest request) {
        var existing = repository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            Customer customer = existing.get();
            return new CustomerResponse(
                    customer.getUniqueCustomerId(),
                    customer.getFirstName(),
                    customer.getCustomerType(),
                    "Customer already exists, returning existing data"
            );
        }

        String uniqueId = UUID.randomUUID().toString();
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .dateOfBirth(request.getDateOfBirth())
                .address(request.getAddress())
                .customerType(request.getCustomerType())
                .customerStatus(request.getCustomerStatus())
                .kycStatus(request.getKycStatus())
                .profileImageUrl(request.getProfileImageUrl())
                .documentReferenceId(request.getDocumentReferenceId())
                .incomeRange(request.getIncomeRange())
                .uniqueCustomerId(uniqueId)
                .build();

        repository.save(customer);

        return new CustomerResponse(
                uniqueId,
                customer.getFirstName(),
                customer.getCustomerType(),
                "Customer registered successfully"
        );
    }
    public Optional<Customer> findByUniqueCustomerId(String uniqueCustomerId)
    {
        return repository.findByUniqueCustomerId(uniqueCustomerId);
    }
}