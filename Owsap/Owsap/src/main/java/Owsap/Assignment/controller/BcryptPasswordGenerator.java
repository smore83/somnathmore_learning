package Owsap.Assignment.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.RestController;

public class BcryptPasswordGenerator {
    public String BcryptGenrator(String password){
        String salt= BCrypt.gensalt(12);

        return BCrypt.hashpw(password,salt);
    }
    public static void main(String[] args) {
       BcryptPasswordGenerator bcryptPasswordGenerator=new BcryptPasswordGenerator();
       String hashPasswordResponse=bcryptPasswordGenerator.BcryptGenrator("Pass@123");
        System.out.println("BcryptPassword Response : "+hashPasswordResponse);
    }
}
