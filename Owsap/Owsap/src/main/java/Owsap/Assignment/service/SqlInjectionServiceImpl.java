package Owsap.Assignment.service;

import Owsap.Assignment.Exception.UserException;
import Owsap.Assignment.utils.Converters;
import Owsap.Assignment.dto.UserDto;
import Owsap.Assignment.modal.User;
import Owsap.Assignment.repository.SqlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class SqlInjectionServiceImpl implements SqlInjectionService{

    @Autowired
    private SqlRepository sqlRepository;
    @Autowired
    private  Converters converters;
    @Override
    public String validatePassword(String userName, String password) {
        Optional<User> userOptional = Optional.ofNullable(sqlRepository.findByuserName(userName));
        Optional<User> passwordOptional = Optional.ofNullable(sqlRepository.findBypassword(password));
log.info(String.valueOf(passwordOptional));
        if (!userOptional.isPresent() || !passwordOptional.isPresent()) {
            return "UserName and/or Password Not valid";
        }

        User user = userOptional.get();
        User passwordUser = passwordOptional.get();

        if (!user.getPassword().equals(passwordUser.getPassword())) {
            return "UserName and Password Not valid";
        }

        return "Successfully Logged In!";
    }
    @Override
    public UserDto findByuserName(String userName) {


        User userEntity = sqlRepository.findByuserName(userName);

        if (userEntity != null) {
            return converters.entityToDTO(userEntity);
        } else {
            throw new UserException("User with email not found: " + userName);
        }
    }

    @Override
    public UserDto findBypassword(String password) {
        User userEntity = sqlRepository.findBypassword(password);

        if (userEntity != null) {
            return converters.entityToDTO(userEntity);
        } else {
            throw new UserException("User with email not found: " + password);
        }
    }

    @Override
    public UserDto save(UserDto userDTO) {
        try {
 //           Optional<User> userOptional = Optional.ofNullable(sqlRepository.findByuserName(userDTO.getUserName()));
//            if (userOptional.isPresent()) {
//                throw new UserException("UserName is already exists!");
//            }
//            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            User user =converters.dtoToEntity(userDTO);
            log.info("user Details"+user);
            User savedUser = sqlRepository.save(user);
            return converters.entityToDTO(savedUser);
        } catch(Exception e) {
            throw new RuntimeException("Error creating user");
        }
    }
}
