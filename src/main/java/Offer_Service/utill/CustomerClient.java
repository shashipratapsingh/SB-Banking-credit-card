package Offer_Service.utill;

import Offer_Service.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerClient {

    @GetMapping("/api/customers/cibilScore/{cibilScore}")
    List<CustomerDTO> getCustomersByCibilScore(@PathVariable("cibilScore") String cibilScore);
}