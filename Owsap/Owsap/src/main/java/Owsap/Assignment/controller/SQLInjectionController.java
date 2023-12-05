package Owsap.Assignment.controller;

//import Owsap.Assignment.config.JwtService;
import Owsap.Assignment.dto.AuthDto;
import Owsap.Assignment.dto.UserDto;
import Owsap.Assignment.service.SqlInjectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owsap")
@CrossOrigin
@Slf4j
public class SQLInjectionController {

    @Autowired
    private SqlInjectionService sqlInjectionService;

//@Autowired
//private JwtService jwtService;

     @PostMapping("/auth")
     public String validatePassword(@RequestParam String userName,@RequestParam String password){
         log.info("=======================================");
         log.info(userName);
         log.info(password);
         return sqlInjectionService.validatePassword(userName, password);
     }

     //register
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto sqlDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(sqlInjectionService.save(sqlDTO));
    }

//    @PostMapping("/login")
//    public String getToken(@RequestBody AuthDto auth){
//
//        return jwtService.generateToken(auth.getUserName(), auth.getPassword());
//    }
}
