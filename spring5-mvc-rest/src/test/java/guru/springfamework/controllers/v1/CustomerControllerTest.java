package guru.springfamework.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void getAllCustomers() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname("Anna");
        customer1.setLastname("Johnson");
        customer1.setId(1L);

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstname("Monica");
        customer2.setLastname("Nightmare");
        customer2.setId(2L);

        List<CustomerDTO> customers = List.of(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerDTOList", hasSize(2)));
    }

    @Test
    void getCustomerById() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jason");
        customerDTO.setLastname("Brody");
        customerDTO.setId(1L);

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/" + customerDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.firstname", equalTo("Jason")))
                .andExpect(jsonPath("$.lastname", equalTo("Brody")));
    }

    @Test
    void putCustomerByDTO() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Mike");
        customerDTO.setLastname("Jason");
        customerDTO.setId(1L);

        CustomerDTO savedDTO = new CustomerDTO();
        savedDTO.setFirstname(customerDTO.getFirstname());
        savedDTO.setLastname(customerDTO.getLastname());
        savedDTO.setId(customerDTO.getId());

        when(customerService.putCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(savedDTO);

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(put("/api/v1/customers/" + customerDTO.getId())
                        .content(mapper.writeValueAsString(customerDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(customerDTO.getId().intValue())))
                .andExpect(jsonPath("$.firstname", equalTo(customerDTO.getFirstname())))
                .andExpect(jsonPath("$.lastname", equalTo(customerDTO.getLastname())));
    }

    @Test
    void patchCustomerByDTO() throws Exception {
        long id = 1L;
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(id);
        customerDTO.setFirstname("firstname");
        customerDTO.setLastname("lastname");

        when(customerService.patchCustomerByDTO(anyLong(), any(CustomerDTO.class)))
                .thenReturn(customerDTO);

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(patch("/api/v1/customers/" + id)
                .content(mapper.writeValueAsString(customerDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo((int) id)))
                .andExpect(jsonPath("$.firstname", equalTo("firstname")))
                .andExpect(jsonPath("$.lastname", equalTo("lastname")));
    }

    @Test
    void deleteCustomerById() throws Exception {
        long id = 1L;

        mockMvc.perform(delete("/api/v1/customers/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}