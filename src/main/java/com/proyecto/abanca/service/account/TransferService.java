package com.proyecto.abanca.service.account;

import com.proyecto.abanca.dto.TransferDto;
import com.proyecto.abanca.dto.TransferThirdPartyDto;
import com.proyecto.abanca.exceptions.NoFundsException;
import com.proyecto.abanca.model.account.Money;
import com.proyecto.abanca.model.account.Transfer;
import com.proyecto.abanca.model.user.ThirdParty;
import com.proyecto.abanca.repositories.account.TransferRepository;
import com.proyecto.abanca.repositories.user.ThirdPartyRepository;
import com.proyecto.abanca.service.user.AccountHoldersService;
import com.proyecto.abanca.service.user.ThirdPartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final BasicAccountService basicAccountService;
    private final AccountService accountService;
    private final ThirdPartyService thirdPartyService;
    private final AccountHoldersService accountHoldersService;

    public Transfer transfer(TransferDto transferDto) throws NoFundsException {
        Long origenId = Long.valueOf(transferDto.getAccountOrigenId());
        Long destinoId = Long.valueOf(transferDto.getAccountDestinoId());
        nameCorrespondsId(destinoId, transferDto.getAccountDestinoName());

        basicAccountService.deductAmountTransfer(transferDto.getAmount(), origenId);
        basicAccountService.depositAmountTransfer(transferDto.getAmount(), destinoId);

        Transfer transfer = save(transferDto);
        transfer.setOrderedBy(accountHoldersService.findByTransferOrderedAccountId(origenId));
        transferRepository.save(transfer);
        return transfer;
    }

    public Transfer transferThirdParty(TransferThirdPartyDto transferThirdPartyDto) throws NoFundsException {
        accountService.secretKeyCorrespondsId(Long.valueOf(transferThirdPartyDto.getAccountOrigenId()), transferThirdPartyDto.getAccountDestinoSecretKey());
        accountService.secretKeyCorrespondsId(Long.valueOf(transferThirdPartyDto.getAccountDestinoId()), transferThirdPartyDto.getAccountDestinoSecretKey());
        Long origenId = Long.valueOf(transferThirdPartyDto.getAccountOrigenId());
        Long destinoId = Long.valueOf(transferThirdPartyDto.getAccountDestinoId());
        ThirdParty thirdParty = thirdPartyService.findByHashedKey(transferThirdPartyDto.getHashedKey());

        basicAccountService.deductAmountTransfer(transferThirdPartyDto.getAmount(), origenId);
        basicAccountService.depositAmountTransfer(transferThirdPartyDto.getAmount(), destinoId);

        TransferDto transferDto = new TransferDto(transferThirdPartyDto.getAmount(), transferThirdPartyDto.getAccountOrigenId(), transferThirdPartyDto.getAccountDestinoId());
        Transfer transfer = save(transferDto);
        transfer.setOrderedBy(thirdPartyService.findByHashedKey(transferThirdPartyDto.getHashedKey()));
        transferRepository.save(transfer);
        return transfer;
    }

    public void nameCorrespondsId(Long id, String name) {
        basicAccountService.nameCorrespondsId(id, name);
    }

    public Transfer save(TransferDto transferDto) {
        Long origenId = Long.valueOf(transferDto.getAccountOrigenId());
        Long destinoId = Long.valueOf(transferDto.getAccountDestinoId());

        Transfer transfer = new Transfer();
        transfer.setAccountOrigen(basicAccountService.findById(origenId));
        transfer.setAccountDestino(basicAccountService.findById(destinoId));
        transfer.setAmount(new Money(BigDecimal.valueOf(transferDto.getAmount())));
        transfer.setDate(LocalDateTime.now());
        return transfer;
    }

}
