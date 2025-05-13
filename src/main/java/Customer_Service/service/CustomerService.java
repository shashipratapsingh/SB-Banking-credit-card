package Customer_Service.service;
import Customer_Service.Enums.CustomerType;
import Customer_Service.Enums.KycStatus;
import Customer_Service.dto.CustomerRequest;
import Customer_Service.dto.CustomerResponse;
import Customer_Service.entity.Customer;
import Customer_Service.exceptions.CustomerNotFoundException;
import Customer_Service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
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
                    "Customer already exists, returning existing data",
                    customer.getCibilScore()
            );
        }

        String uniqueId = UUID.randomUUID().toString();
        String cibilScore = getCibilScore(request);
        request.setCibilScore(cibilScore);
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
                .cibilScore(request.getCibilScore())
                .uniqueCustomerId(uniqueId)
                .build();

        repository.save(customer);

        return new CustomerResponse(
                uniqueId,
                customer.getFirstName(),
                customer.getCustomerType(),
                "Customer registered successfully",
                customer.getCibilScore()
        );
    }

    private String getCibilScore(CustomerRequest request) {
        String incomeStr = request.getIncomeRange();
        int income = 0;
        try {
            income = Integer.parseInt(incomeStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid income format: must be a number");
        }

        String cibilScore = "";

        if (income <= 20000) {
            cibilScore = "LOW";
        } else if (income > 20000 && income < 50000) {
            cibilScore = "MEDIUM";
        } else if (income >= 50000) {
            cibilScore = "HIGH";
        }
        return cibilScore;
    }

    private int getRandomNumber(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }
    public Optional<Customer> findByUniqueCustomerId(String uniqueCustomerId)
    {
        return repository.findByUniqueCustomerId(uniqueCustomerId);
    }
    public Optional<Customer> findByMobileNumber(String mobileNumber)
    {
        return repository.findByMobileNumber(mobileNumber);
    }
    public Optional<Customer> findByEmail(String email)
    {
        return repository.findByEmail(email);
    }
    public List<Customer> findByKycStatus(String kycStatus) {
        KycStatus statusEnum = KycStatus.valueOf(kycStatus.toUpperCase());
        return repository.findByKycStatus(statusEnum);
    }
    public List<Customer> findByCustomerType(String customerType) {
        CustomerType customerTypeEnum = CustomerType.valueOf(customerType.toUpperCase());
        return repository.findByCustomerType(customerTypeEnum);
    }

    public Customer kycUpdate(Customer customer, String uniqueCustomerId) {
        Customer existingCustomer=findByUniqueCustomerId(uniqueCustomerId).orElseThrow(() -> new CustomerNotFoundException("This customer not Found"+uniqueCustomerId));
        existingCustomer.setKycStatus(customer.getKycStatus());
        return repository.save(existingCustomer);

    }
}