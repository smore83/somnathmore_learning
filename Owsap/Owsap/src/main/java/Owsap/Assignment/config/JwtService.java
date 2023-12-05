//package Owsap.Assignment.config;
//
//import Owsap.Assignment.modal.User;
//import Owsap.Assignment.repository.SqlRepository;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import java.security.Key;
//import java.util.Date;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class JwtService {
//
//
//    @Autowired
//    private SqlRepository userRepository;
//
//    public String generateToken(String userName, String password) {
//        try {
//            User user = userRepository.findByuserName(userName);
//            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            if (passwordEncoder.matches(password, user.getPassword())) {
//                return createToken(userName);
//            } else {
//                throw new RuntimeException("Password Doesn't match");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Email Doesn't exist");
//        }
//    }
//
//    private String createToken(String userName) {
//        return Jwts
//                .builder()
//                .setSubject(userName)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    private Key getSignKey() {
//        byte[] keyByte = Decoders.BASE64.decode("2b7e151628aed2a6abf7158809cf4f3c2b7e151628aed2a6abf7158809cf4f3c2");
//        return Keys.hmacShaKeyFor(keyByte);
//    }
//}