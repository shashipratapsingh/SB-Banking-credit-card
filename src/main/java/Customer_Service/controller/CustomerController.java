package Customer_Service.controller;
import Customer_Service.dto.CustomerRequest;
import Customer_Service.dto.CustomerResponse;
import Customer_Service.entity.Customer;
import Customer_Service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
}