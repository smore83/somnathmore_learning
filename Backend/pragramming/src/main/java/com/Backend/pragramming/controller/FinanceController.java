package com.Backend.pragramming.controller;

import com.Backend.pragramming.dto.FinanceDto;
import com.Backend.pragramming.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.Backend.pragramming.utils.Constants.API;


@RestController
@RequestMapping(API)
public class FinanceController {
    @Autowired
    private FinanceService financeService;

    @GetMapping
    public ResponseEntity<List<FinanceDto>> getAllFinanceIncome() {
        return new ResponseEntity<>(financeService.getAllFinanceIncome(), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public FinanceDto getFinanceById(@PathVariable Long id) {
        return financeService.getFinanceById(id)   ;
    }


    @PostMapping
    public String addIncome(@RequestBody FinanceDto financeDto) {
        return financeService.addYourIncome(financeDto);
    }

    @PutMapping("/{id}")
    public String updateFinanceDto(@PathVariable Long id,@RequestBody FinanceDto financeDto) {
        return financeService.updateYourIncomeSource(id,financeDto);
    }


    @DeleteMapping("/{id}")
    public String deleteFinanceById(@PathVariable Long id) {
        return financeService.deleteFinanceById(id);
    }
}
