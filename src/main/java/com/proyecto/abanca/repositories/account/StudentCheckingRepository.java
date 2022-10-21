package com.proyecto.abanca.repositories.account;

import com.proyecto.abanca.model.account.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Long> {
}
