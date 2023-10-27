package com.Backend.pragramming.dto;

import lombok.Data;

import java.util.List;

@Data
public class FinanceDto {
    private Long id;
    private String name;
    private String description;
    private Long totalAmount;
    private Long recurringAmount;
    private List<IncomeDto> source;

}
