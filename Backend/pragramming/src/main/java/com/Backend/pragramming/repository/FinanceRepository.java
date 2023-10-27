package com.Backend.pragramming.repository;

import com.Backend.pragramming.entity.Finance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepository extends JpaRepository<Finance,Long> {
}
