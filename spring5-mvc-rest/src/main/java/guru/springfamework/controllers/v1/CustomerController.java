package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public CustomerListDTO getAllCustomers() {
        return new CustomerListDTO(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createNewCustomer(customerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public CustomerDTO putCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.putCustomerByDTO(id, customerDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.patchCustomerByDTO(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomerById(id);
    }
}
