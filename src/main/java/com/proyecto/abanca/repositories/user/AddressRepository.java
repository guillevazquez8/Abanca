package com.proyecto.abanca.repositories.user;


import com.proyecto.abanca.model.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
