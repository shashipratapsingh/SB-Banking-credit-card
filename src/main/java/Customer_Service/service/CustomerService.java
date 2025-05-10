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
                    customer.getFullName(),
                    customer.getProductType(),
                    "Customer already exists, returning existing data"
            );
        }

        String uniqueId = UUID.randomUUID().toString();
        Customer customer = Customer.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .city(request.getCity())
                .incomeRange(request.getIncomeRange())
                .productType(request.getProductType())
                .uniqueCustomerId(uniqueId)
                .build();

        repository.save(customer);

        return new CustomerResponse(
                uniqueId,
                customer.getFullName(),
                customer.getProductType(),
                "Customer registered successfully"
        );
    }
    public Optional<Customer> findByUniqueCustomerId(String uniqueCustomerId)
    {
        return repository.findByUniqueCustomerId(uniqueCustomerId);
    }
}