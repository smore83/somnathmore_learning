package com.Backend.pragramming.repository;

import com.Backend.pragramming.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income,Long> {
}
