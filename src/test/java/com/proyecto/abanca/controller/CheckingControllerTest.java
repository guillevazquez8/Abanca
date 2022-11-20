package com.proyecto.abanca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.abanca.dto.AccountDto;
import com.proyecto.abanca.model.user.*;
import com.proyecto.abanca.repositories.account.CheckingRepository;
import com.proyecto.abanca.repositories.account.StudentCheckingRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class CheckingControllerTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountHoldersRepository accountHoldersRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
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
        AccountHolders juan = new AccountHolders("Juan", "juanuser", "soyjuan", roles, LocalDate.of(1996, 1, 15), juanAddress);
        AccountHolders pablo = new AccountHolders("Pablo", "pablouser", "soypablo", roles, LocalDate.of(2001, 1, 15), juanAddress);
        accountHoldersRepository.save(juan);
    }
    @AfterEach
    void tearDown() {
        checkingRepository.deleteAll();
        studentCheckingRepository.deleteAll();
        accountHoldersRepository.deleteAll();
        addressRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "fakeUser", roles = {"ADMIN"})
    void testCreateChecking() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                    .post("/abanca/checking")
                    .content(objectMapper.writeValueAsString(new AccountDto("1", "1234")))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(
                        status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").exists());
        var checkings = checkingRepository.findAll();
        assertEquals(1, checkings.size());
        assertTrue(checkings.toString().contains("minimumBalance"));
    }

}
