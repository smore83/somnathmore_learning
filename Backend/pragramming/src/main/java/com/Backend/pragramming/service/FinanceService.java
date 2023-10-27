package com.Backend.pragramming.service;

import com.Backend.pragramming.dto.FinanceDto;

import java.util.List;

public interface FinanceService {

    public List<FinanceDto> getAllFinanceIncome();
    public FinanceDto getFinanceById(Long id);

    public String addYourIncome(FinanceDto financeDto);

    public  String updateYourIncomeSource(Long id,FinanceDto financeDto);

    public String deleteFinanceById(Long id);
}
