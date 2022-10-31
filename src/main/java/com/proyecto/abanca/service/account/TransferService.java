package com.proyecto.abanca.service.account;

import com.proyecto.abanca.dto.TransferDto;
import com.proyecto.abanca.exceptions.NoFundsException;
import com.proyecto.abanca.model.account.Money;
import com.proyecto.abanca.model.account.Transfer;
import com.proyecto.abanca.repositories.account.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;

    private final BasicAccountService basicAccountService;

    public Transfer transfer(TransferDto transferDto) throws NoFundsException {
        Long origenId = Long.valueOf(transferDto.getAccountOrigenId());
        Long destinoId = Long.valueOf(transferDto.getAccountDestinoId());
        nameCorrespondsId(destinoId, transferDto.getAccountDestinoName());

        basicAccountService.deductAmountTransfer(transferDto.getAmount(), origenId);
        basicAccountService.depositAmountTransfer(transferDto.getAmount(), destinoId);

        Transfer transfer = new Transfer();
        transfer.setAccountOrigen(basicAccountService.findById(origenId));
        transfer.setAccountDestino(basicAccountService.findById(destinoId));
        transfer.setAmount(new Money(BigDecimal.valueOf(transferDto.getAmount())));
        transfer.setDate(LocalDateTime.now());
        //transfer.setOrderedBy();
        transferRepository.save(transfer);
        return transfer;
    }

    public void nameCorrespondsId(Long id, String name) {
        basicAccountService.nameCorrespondsId(id, name);
    }

}
