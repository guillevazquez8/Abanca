package com.proyecto.abanca;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.abanca.dto.CheckingDto;
import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.model.user.Address;
import com.proyecto.abanca.model.user.User;
import com.proyecto.abanca.repositories.account.CheckingRepository;
import com.proyecto.abanca.repositories.user.AccountHoldersRepository;
import com.proyecto.abanca.repositories.user.AddressRepository;
import com.proyecto.abanca.repositories.user.UserRepository;
import com.proyecto.abanca.service.account.CheckingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class CheckingControllerTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AccountHoldersRepository accountHoldersRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private CheckingService checkingService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    protected ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Address juanAddress = new Address("Calle Guyana", 15, 03454L, "Cadiz", "España");
        addressRepository.save(juanAddress);

        AccountHolders juan = new AccountHolders();
        juan.setName("Juan");
        juan.setDateOfBirth(LocalDate.of(1998, 1, 15));
        juan.setPrimaryAddress(juanAddress);
        accountHoldersRepository.save(juan);
    }
    @AfterEach
    void tearDown() {
        checkingRepository.deleteAll();
        accountHoldersRepository.deleteAll();
        addressRepository.deleteAll();
    }
    @Test
    void testCreateChecking() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                    .post("/checking")
                    .content(objectMapper.writeValueAsString(new CheckingDto(2000L, "1", "9847")))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(
                        status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").exists());
        var checkings = checkingRepository.findAll();
        assertEquals(1, checkings.size());
        assertTrue(checkings.toString().contains("2000"));
    }
}
