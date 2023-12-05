package Owsap.Assignment.utils;

import Owsap.Assignment.dto.UserDto;
import Owsap.Assignment.modal.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Converters {
    @Autowired
    private ModelMapper modelMapper;
    public User dtoToEntity(UserDto userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    public UserDto entityToDTO(User userEntity){
        return modelMapper.map(userEntity, UserDto.class);
    }
}
