package com.proyecto.abanca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.abanca.dto.CreditCardDto;
import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.model.user.Address;
import com.proyecto.abanca.model.user.ERole;
import com.proyecto.abanca.model.user.Role;
import com.proyecto.abanca.repositories.account.CreditCardRepository;
import com.proyecto.abanca.repositories.user.AccountHoldersRepository;
import com.proyecto.abanca.repositories.user.AddressRepository;
import com.proyecto.abanca.repositories.user.RoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CreditCardControllerTest {


    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountHoldersRepository accountHoldersRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    protected ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

        Address juanAddress = new Address("Calle Guyana", 15, 03454L, "Cadiz", "España");
        addressRepository.save(juanAddress);

        Role accHolder = new Role(ERole.ROLE_ACCOUNTHOLDER);
        Role admin = new Role(ERole.ROLE_ADMIN);
        Role thirdParty = new Role(ERole.ROLE_THIRDPARTY);
        roleRepository.save(accHolder);
        roleRepository.save(admin);
        roleRepository.save(thirdParty);
        Set<Role> roles = new HashSet<>();

        roles.add(accHolder);
        AccountHolders juan = new AccountHolders("Juan", "juanuser", "soyjuan", roles, LocalDate.of(1998, 1, 15), juanAddress);
        accountHoldersRepository.save(juan);
    }

    @AfterEach
    void tearDown() {
        creditCardRepository.deleteAll();
        accountHoldersRepository.deleteAll();
        addressRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "fakeUser", roles = {"ADMIN"})
    void testCreateCreditCard() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/abanca/creditcard")
                        .content(objectMapper.writeValueAsString(new CreditCardDto("4", "1234", 52000L, 0.2)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(
                        status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").exists());
        var creditCards = creditCardRepository.findAll();
        assertEquals(1, creditCards.size());
        assertTrue(creditCards.toString().contains("52000"));
    }

}
