package com.Backend.pragramming.service;

import com.Backend.pragramming.dto.FinanceDto;
import com.Backend.pragramming.dto.IncomeDto;
import com.Backend.pragramming.entity.Finance;
import com.Backend.pragramming.entity.Income;
import com.Backend.pragramming.exception.ResourceNotFoundException;
import com.Backend.pragramming.repository.FinanceRepository;
import com.Backend.pragramming.utils.Converters;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.Backend.pragramming.utils.Constants.*;

@Service
public class FinanceServiceImpl implements FinanceService{

    @Autowired
    private FinanceRepository financeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Converters converters;
    @Override
    public List<FinanceDto> getAllFinanceIncome() {
       List<Finance> financeList=financeRepository.findAll();

        return financeList.stream().map(finance -> converters.entityToDto(finance)).collect(Collectors.toList());
    }

    @Override
    public FinanceDto getFinanceById(Long id) {

        Optional<Finance> financeOptional=financeRepository.findById(id);
        if(financeOptional.isEmpty()){
            throw  new ResourceNotFoundException("Data Not Found By this ID");
        }
        return converters.entityToDto(financeOptional.get());
    }



    @Override
    public String addYourIncome(FinanceDto financeDto) {

        Finance finance=this.modelMapper.map(financeDto,Finance.class);

          for (Income income:finance.getSource()){
              income.setFinance(finance);
          }
        financeRepository.save(finance);

        return DATA_SAVED;
    }

    @Override
    public String updateYourIncomeSource(Long id, FinanceDto financeDto) {
        Optional<Finance> financeOptional=financeRepository.findById(id);
        if(financeOptional.isPresent()){

            Finance existingFinance = financeOptional.get();
            existingFinance.setName(financeDto.getName());
            existingFinance.setDescription(financeDto.getDescription());
            existingFinance.setTotalAmount(financeDto.getTotalAmount());
            existingFinance.setRecurringAmount(financeDto.getRecurringAmount());
            List<Income> updatedProjects = new ArrayList<>();

            for (IncomeDto incomeDto : financeDto.getSource()) {
                Income existingProject = existingFinance.getSource()
                        .stream()
                        .filter(p -> p.getIncomeId().equals(incomeDto.getIncomeId()))
                        .findFirst()
                        .orElse(new Income()); // Create a new Project if not found

                existingProject.setIncomeSource(incomeDto.getIncomeSource());
                existingProject.setIncomeId(incomeDto.getIncomeId());
                existingProject.setIncomeAmount(incomeDto.getIncomeAmount());
                existingProject.setDate(incomeDto.getDate());
                //sab ayega idha
                existingProject.setFinance(existingFinance); // Set the Finance association

                updatedProjects.add(existingProject);
            }

            existingFinance.setSource(updatedProjects);
            financeRepository.save(existingFinance);

            return DATA_UPDATE;

        }
        throw new ResourceNotFoundException( DATA_NOT_FOUND);
    }

    @Override
    public String deleteFinanceById(Long id) {
        Optional<Finance> financeOptional=financeRepository.findById(id);
        if(financeOptional.isEmpty()){
            throw  new ResourceNotFoundException("Data Not Found By this ID");
        }
        financeRepository.deleteById(id);
        return DATA_DELETED;
    }
}
