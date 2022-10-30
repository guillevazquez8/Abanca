package com.proyecto.abanca.service.account;

import com.proyecto.abanca.model.account.BasicAccount;
import com.proyecto.abanca.model.account.Money;
import com.proyecto.abanca.repositories.account.BasicAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicAccountService {

    private final BasicAccountRepository basicAccountRepository;

    public List<BasicAccount> accessMyAccount(String username, String password) {
        return basicAccountRepository.findByPrimaryOwner_UsernameAndPrimaryOwner_Password(username, password);
    }

    //public List<Money> accessMyAccountBalance(String username, String password) {
        //List<Money> myBalance = basicAccountRepository.findByPrimaryOwner_UsernameAndPrimaryOwner_Password(username, password).getBalance();
    //}

}
