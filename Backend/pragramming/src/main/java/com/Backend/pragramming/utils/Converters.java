package com.Backend.pragramming.utils;

import com.Backend.pragramming.dto.FinanceDto;
import com.Backend.pragramming.entity.Finance;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Converters {

    @Autowired
    private ModelMapper modelMapper;

    public FinanceDto entityToDto(Finance finance){
        return modelMapper.map(finance,FinanceDto.class);
    }
    public Finance dtoToEntity(FinanceDto financeDto){
        return modelMapper.map(financeDto,Finance.class);
    }
}
