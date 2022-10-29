package com.proyecto.abanca.service.account;

import com.proyecto.abanca.model.account.BasicAccount;
import com.proyecto.abanca.repositories.account.BasicAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicAccountService {

    private final BasicAccountRepository basicAccountRepository;

    public List<BasicAccount> findByPrimaryOwnerId(Long id) {
        return findByPrimaryOwnerId(id);
    }
}
