package com.proyecto.abanca.service;

import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.model.user.Address;
import com.proyecto.abanca.model.user.Admins;
import com.proyecto.abanca.model.user.ThirdParty;
import com.proyecto.abanca.repositories.user.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InitMasterData {

    private AccountHoldersRepository accountHoldersRepository;
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private ThirdPartyRepository thirdPartyRepository;
    private AdminsRepository adminsRepository;

    public InitMasterData(AccountHoldersRepository accountHoldersRepository, AddressRepository addressRepository, UserRepository userRepository, ThirdPartyRepository thirdPartyRepository, AdminsRepository adminsRepository) {
        this.accountHoldersRepository = accountHoldersRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.thirdPartyRepository = thirdPartyRepository;
        this.adminsRepository = adminsRepository;
    }

    public void initData() { //spring data - jpa -hibernate -jdbc - mysql

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


        AccountHolders user1 = new AccountHolders("Maria Martinez", LocalDate.of(1891,01,01), addressUser1);
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

    }
}
