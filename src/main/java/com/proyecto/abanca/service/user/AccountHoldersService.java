package com.proyecto.abanca.service.user;

import com.proyecto.abanca.exceptions.BadRequestException;
import com.proyecto.abanca.model.user.AccountHolders;
import com.proyecto.abanca.repositories.user.AccountHoldersRepository;
import com.proyecto.abanca.repositories.user.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountHoldersService {

    private final AccountHoldersRepository accountHoldersRepository;

    public List<AccountHolders> findAll() {return accountHoldersRepository.findAll();}

    public AccountHolders findById(Long id) {
        if (accountHoldersRepository.findById(id).isEmpty()) {
            throw new BadRequestException("Entered account holder ID doesn't exist.");
        }
        return accountHoldersRepository.findById(id).get();
    }

    public AccountHolders findByTransferOrderedAccountId(Long id) {
        return accountHoldersRepository.findByTransfersOrdered_AccountOrigen_Id(id);
    }

}
