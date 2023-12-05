package Owsap.Assignment.service;

import Owsap.Assignment.dto.UserDto;

public interface SqlInjectionService {
   public String validatePassword(String userName,String password);
   UserDto findByuserName(String email);
   UserDto findBypassword(String password);
   UserDto save(UserDto user);
}
