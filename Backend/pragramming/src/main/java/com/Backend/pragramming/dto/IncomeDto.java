package com.Backend.pragramming.dto;

import com.Backend.pragramming.entity.Finance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class IncomeDto {
    private Long incomeId;

    private String date;
    private String incomeSource;
    private Long incomeAmount;

}
