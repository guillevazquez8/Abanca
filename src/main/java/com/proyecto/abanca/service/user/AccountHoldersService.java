package com.proyecto.abanca.service.user;

import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.repositories.user.AccountHoldersRepository;
import com.proyecto.abanca.repositories.user.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountHoldersService {

    private final AccountHoldersRepository accountHoldersRepository;

    public List<AccountHolders> findAll() {return accountHoldersRepository.findAll();}
}
