package com.proyecto.abanca;

import com.proyecto.abanca.exceptions.NoFundsException;
import com.proyecto.abanca.model.account.BasicAccount;
import com.proyecto.abanca.model.account.Checking;
import com.proyecto.abanca.model.account.Money;
import com.proyecto.abanca.model.account.Status;
import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.model.user.Address;
import com.proyecto.abanca.model.user.ERole;
import com.proyecto.abanca.model.user.Role;
import com.proyecto.abanca.repositories.account.BasicAccountRepository;
import com.proyecto.abanca.repositories.account.CheckingRepository;
import com.proyecto.abanca.repositories.user.AccountHoldersRepository;
import com.proyecto.abanca.repositories.user.AddressRepository;
import com.proyecto.abanca.repositories.user.RoleRepository;
import com.proyecto.abanca.service.account.BasicAccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BasicAccountServiceTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountHoldersRepository accountHoldersRepository;
    @Autowired
    private BasicAccountService basicAccountService;
    @Autowired
    private CheckingRepository checkingRepository;

    @BeforeEach
    void setUp() {

        Address pacoAddress = new Address("Calle Guyana", 15, 03454L, "Cadiz", "España");
        addressRepository.save(pacoAddress);

        Role accHolder = new Role(ERole.ROLE_ACCOUNTHOLDER);
        Role admin = new Role(ERole.ROLE_ADMIN);
        Role thirdParty = new Role(ERole.ROLE_THIRDPARTY);
        roleRepository.save(accHolder);
        roleRepository.save(admin);
        roleRepository.save(thirdParty);
        Set<Role> roles = new HashSet<>();

        roles.add(accHolder);
        AccountHolders paco = new AccountHolders("Paco", "soyPaco", "1234", roles, LocalDate.of(1999, 5, 5), pacoAddress);
        accountHoldersRepository.save(paco);

        Checking account1 = new Checking(paco, LocalDate.of(2015, 1, 23), "1234", Status.ACTIVE);
        account1.setBalance(new Money(BigDecimal.valueOf(2000L)));
        checkingRepository.save(account1);

    }

    @AfterEach
    void tearDown() {
        checkingRepository.deleteAll();
        accountHoldersRepository.deleteAll();
        roleRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Test
    void testPenaltyFee() throws NoFundsException {
        basicAccountService.deductAmountTransfer(1800L, 1L);
        assertEquals(basicAccountService.findById(1L).getBalance().getAmount().longValue(), 160L);
    }

    @Test
    void testNoFundsExcepion() {
        Assertions.assertThrows(NoFundsException.class, () ->
        {basicAccountService.deductAmountTransfer(2500L, 1L);});
    }

}
