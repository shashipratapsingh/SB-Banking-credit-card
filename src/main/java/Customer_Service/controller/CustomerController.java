package Customer_Service.controller;
import Customer_Service.Enums.KycStatus;
import Customer_Service.dto.CustomerRequest;
import Customer_Service.dto.CustomerResponse;
import Customer_Service.entity.Customer;
import Customer_Service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public CustomerResponse register(@RequestBody CustomerRequest request) {
        return customerService.registerCustomer(request);
    }
    @GetMapping("/{uniqueCustomerId}")
    public Optional<Customer> findByUniqueCustomerId(@PathVariable String uniqueCustomerId)
    {
        return customerService.findByUniqueCustomerId(uniqueCustomerId);
    }
    @GetMapping("/phone/{mobileNumber}")
    public Optional<Customer> findByMobileNumber(@PathVariable String mobileNumber)
    {
        return customerService.findByMobileNumber(mobileNumber);
    }
    @GetMapping("/email/{email}")
    public Optional<Customer> findByEmail(@PathVariable String email)
    {
        return customerService.findByEmail(email);
    }
    @GetMapping("/kyc/{kycStatus}")
    public ResponseEntity<List<Customer>> findByKycStatus(@PathVariable String kycStatus) {
        try {
            List<Customer> customers = customerService.findByKycStatus(kycStatus);
            return ResponseEntity.ok(customers);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }
}