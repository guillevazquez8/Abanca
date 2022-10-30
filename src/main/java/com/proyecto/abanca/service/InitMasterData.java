package com.proyecto.abanca.service;

import com.proyecto.abanca.model.account.Checking;
import com.proyecto.abanca.model.account.Money;
import com.proyecto.abanca.model.account.Status;
import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.model.user.Address;
import com.proyecto.abanca.model.user.Admins;
import com.proyecto.abanca.model.user.ThirdParty;
import com.proyecto.abanca.repositories.account.CheckingRepository;
import com.proyecto.abanca.repositories.user.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class InitMasterData {

    private AccountHoldersRepository accountHoldersRepository;
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private ThirdPartyRepository thirdPartyRepository;
    private AdminsRepository adminsRepository;
    private CheckingRepository checkingRepository;

    public InitMasterData(AccountHoldersRepository accountHoldersRepository, AddressRepository addressRepository, UserRepository userRepository, ThirdPartyRepository thirdPartyRepository, AdminsRepository adminsRepository, CheckingRepository checkingRepository) {
        this.accountHoldersRepository = accountHoldersRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.thirdPartyRepository = thirdPartyRepository;
        this.adminsRepository = adminsRepository;
        this.checkingRepository = checkingRepository;
    }

    public void initData() { //spring data - jpa -hibernate -jdbc - mysql

        checkingRepository.deleteAll();
        accountHoldersRepository.deleteAll();
        thirdPartyRepository.deleteAll();
        adminsRepository.deleteAll();
        addressRepository.deleteAll();

        Address addressUser1 = new Address("Plaza España", 2, 01234L, "Madrid", "Spain");
        Address addressUser2 = new Address("Plaza España", 2, 01234L, "Madrid", "Spain");
        Address addressUser3 = new Address("Calle Andalucia", 7, 13678L, "Valencia", "France");

        addressRepository.save(addressUser1);
        addressRepository.save(addressUser2);
        addressRepository.save(addressUser3);

        AccountHolders user1 = new AccountHolders("Maria Martinez", LocalDate.of(2000,01,01), addressUser1);
        AccountHolders user2 = new AccountHolders("Pablo Martinez", LocalDate.of(1892,02,02), addressUser2);
        AccountHolders user3 = new AccountHolders("Lola Perez", LocalDate.of(1893,03,03), addressUser3);

        accountHoldersRepository.save(user1);
        accountHoldersRepository.save(user2);
        accountHoldersRepository.save(user3);

        ThirdParty userTP1 = new ThirdParty("Tadeo Jones", "1234");
        ThirdParty userTP2 = new ThirdParty("Tamara Rodriguez", "1234");
        ThirdParty userTP3 = new ThirdParty("Toledo Park", "1234");

        thirdPartyRepository.save(userTP1);
        thirdPartyRepository.save(userTP2);
        thirdPartyRepository.save(userTP3);

        Admins userA1 = new Admins("Andrea Ardon");
        Admins userA2 = new Admins("Alejandro Carmona");
        Admins userA3 = new Admins("Adrian Castillo");

        adminsRepository.save(userA1);
        adminsRepository.save(userA2);
        adminsRepository.save(userA3);

        Checking checking1 = new Checking(new Money(BigDecimal.valueOf(10000)), user1, LocalDate.of(2015, 5, 25), "58934", Status.ACTIVE);
        Checking checking2 = new Checking(new Money(BigDecimal.valueOf(15000)), user2, LocalDate.of(2012, 1, 12), "34721", Status.ACTIVE);

        checkingRepository.save(checking1);
        checkingRepository.save(checking2);
    }
}
