package com.proyecto.abanca.service;

import com.proyecto.abanca.model.account.*;
import com.proyecto.abanca.model.user.*;
import com.proyecto.abanca.repositories.account.CheckingRepository;
import com.proyecto.abanca.repositories.account.CreditCardRepository;
import com.proyecto.abanca.repositories.account.SavingsRepository;
import com.proyecto.abanca.repositories.account.StudentCheckingRepository;
import com.proyecto.abanca.repositories.user.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class InitMasterData {

    private RoleRepository roleRepository;
    private AccountHoldersRepository accountHoldersRepository;
    private AddressRepository addressRepository;
    private ThirdPartyRepository thirdPartyRepository;
    private AdminsRepository adminsRepository;
    private CheckingRepository checkingRepository;
    private StudentCheckingRepository studentCheckingRepository;
    private SavingsRepository savingsRepository;
    private CreditCardRepository creditCardRepository;
    private PasswordEncoder encoder;

    public InitMasterData(AccountHoldersRepository accountHoldersRepository, RoleRepository roleRepository,
                          AddressRepository addressRepository, ThirdPartyRepository thirdPartyRepository,
                          AdminsRepository adminsRepository, CheckingRepository checkingRepository,
                          StudentCheckingRepository studentCheckingRepository, SavingsRepository savingsRepository,
                          CreditCardRepository creditCardRepository, PasswordEncoder encoder) {
        this.accountHoldersRepository = accountHoldersRepository;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
        this.thirdPartyRepository = thirdPartyRepository;
        this.adminsRepository = adminsRepository;
        this.checkingRepository = checkingRepository;
        this.studentCheckingRepository = studentCheckingRepository;
        this.savingsRepository = savingsRepository;
        this.creditCardRepository = creditCardRepository;
        this.encoder = encoder;
    }

    public void initData() {

        studentCheckingRepository.deleteAll();
        savingsRepository.deleteAll();
        creditCardRepository.deleteAll();
        checkingRepository.deleteAll();
        accountHoldersRepository.deleteAll();
        thirdPartyRepository.deleteAll();
        adminsRepository.deleteAll();
        roleRepository.deleteAll();
        addressRepository.deleteAll();

        Address address1 = new Address("Rua Canido", 76, 36390L, "Vigo", "España");
        Address address2 = new Address("Taquigraf Garriga", 95, 18029L, "Barcelona", "España");
        Address address3 = new Address("Rua Iago Aspas", 10, 38465L, "Moaña", "España");
        addressRepository.save(address1);
        addressRepository.save(address2);
        addressRepository.save(address3);

        Role accountHolder = new Role(ERole.ROLE_ACCOUNTHOLDER);
        Role thirdParty = new Role(ERole.ROLE_THIRDPARTY);
        Role admin = new Role(ERole.ROLE_ADMIN);
        roleRepository.save(accountHolder);
        roleRepository.save(thirdParty);
        roleRepository.save(admin);
        Set<Role> roles = new HashSet<>();

        roles.add(accountHolder);
        AccountHolders accHolder1 = new AccountHolders("Valeri Karpin", "valeri", encoder.encode("soyvaleri"), roles, LocalDate.of(1985,9,28), address1);
        AccountHolders accHolder2 = new AccountHolders("Gustavo Lopez", "gustavo", encoder.encode("soygustavo"), roles, LocalDate.of(1982,2,12), address2);
        AccountHolders accHolder3 = new AccountHolders("Benni McCarthy", "benni", encoder.encode("soybenni"), roles, LocalDate.of(1993,03,03), address3);
        AccountHolders accHolder4 = new AccountHolders("Michel Salgado", "michel", encoder.encode("soymichel"), roles, LocalDate.of(1999, 11, 6), address3);
        AccountHolders accHolder5 = new AccountHolders("Mazinho Alcantara", "mazinho", encoder.encode("soymazinho"), roles, LocalDate.of(2001, 3, 3), address1);
        accountHoldersRepository.save(accHolder1);
        accountHoldersRepository.save(accHolder2);
        accountHoldersRepository.save(accHolder3);
        accountHoldersRepository.save(accHolder4);
        accountHoldersRepository.save(accHolder5);
        roles.clear();

        roles.add(thirdParty);
        ThirdParty thirdParty1 = new ThirdParty("Leo Messi", "leo", encoder.encode("soyleo"), roles, "1234");
        ThirdParty thirdParty2 = new ThirdParty("Andres Iniesta", "andres", encoder.encode("soyandres"), roles, "1234");
        ThirdParty thirdParty3 = new ThirdParty("Samuel Etoo", "samuel", encoder.encode("soysamuel"), roles, "1234");
        thirdPartyRepository.save(thirdParty1);
        thirdPartyRepository.save(thirdParty2);
        thirdPartyRepository.save(thirdParty3);
        roles.clear();

        roles.add(admin);
        Admins admin1 = new Admins("Paolo Maldini", "paolo", encoder.encode("soypaolo"), roles);
        Admins admin2 = new Admins("Andrei Shevchenko", "andrei", encoder.encode("soyandrei"), roles);
        Admins admin3 = new Admins("Andrea Pirlo", "andrea", encoder.encode("soyandrea"), roles);
        adminsRepository.save(admin1);
        adminsRepository.save(admin2);
        adminsRepository.save(admin3);
        roles.clear();

        Checking checking1 = new Checking(accHolder1, LocalDate.of(2015, 5, 25), "1234", Status.ACTIVE);
        Checking checking2 = new Checking(accHolder2, LocalDate.of(2012, 1, 12), "1234", Status.ACTIVE);
        checkingRepository.save(checking1);
        checkingRepository.save(checking2);

        StudentChecking studentChecking1 = new StudentChecking(accHolder4, LocalDate.of(2021, 1,2), "1234", Status.ACTIVE);
        StudentChecking studentChecking2 = new StudentChecking(accHolder5, LocalDate.of(2022, 8, 19), "1234", Status.FROZEN);
        studentCheckingRepository.save(studentChecking1);
        studentCheckingRepository.save(studentChecking2);

        Savings savings1 = new Savings(accHolder3, LocalDate.of(2010, 1, 1));
        Savings savings2 = new Savings(accHolder2, LocalDate.of(2012, 1, 6), new Money(BigDecimal.valueOf(5000)));
        savingsRepository.save(savings1);
        savingsRepository.save(savings2);

        CreditCard creditCard1 = new CreditCard(accHolder1, LocalDate.of(2015, 3, 15), "1234", Status.ACTIVE);
        CreditCard creditCard2 = new CreditCard(accHolder5, LocalDate.of(2012, 6, 6), "1234", Status.FROZEN, new Money(BigDecimal.valueOf(50000)), BigDecimal.valueOf(0.1));
        creditCardRepository.save(creditCard1);
        creditCardRepository.save(creditCard2);

    }
}
