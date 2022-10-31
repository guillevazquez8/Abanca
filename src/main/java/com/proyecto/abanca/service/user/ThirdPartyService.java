package com.proyecto.abanca.service.user;

import com.proyecto.abanca.exceptions.BadRequestException;
import com.proyecto.abanca.model.user.ThirdParty;
import com.proyecto.abanca.repositories.user.ThirdPartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThirdPartyService {

    private final ThirdPartyRepository thirdPartyRepository;

    public ThirdParty findByHashedKey(String hashedKey) {
        Optional<ThirdParty> thirdParty = thirdPartyRepository.findByHashedKey(hashedKey);
        if (thirdParty.isEmpty()) {
            throw new BadRequestException("Entered hashed key doesn't belong to any user.");
        }
        return thirdParty.get();
    }

}
