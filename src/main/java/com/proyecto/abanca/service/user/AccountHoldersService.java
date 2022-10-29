package com.proyecto.abanca.service.user;

import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.repositories.user.AccountHoldersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountHoldersService {

    private final AccountHoldersRepository accountHoldersRepository;

    public List<AccountHolders> findAll() {return accountHoldersRepository.findAll();}

    public Optional<AccountHolders> findById(Long id) {return accountHoldersRepository.findById(id);}

}
