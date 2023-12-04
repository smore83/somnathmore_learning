package Owsap.Assignment.controller;

import org.mindrot.jbcrypt.BCrypt;

public class HashCodeSaltGenerator{
    private static final int LOG_ROUNDS = 12;

    public String hashPassword(String password) {
        String salt = BCrypt.gensalt(LOG_ROUNDS);
        return BCrypt.hashpw(password, salt);
    }

    public boolean verifyPassword(String candidatePassword, String storedHash) {
        return BCrypt.checkpw(candidatePassword, storedHash);
    }

    public static void main(String[] args) {
        HashCodeSaltGenerator hashCodeSaltGenerator = new HashCodeSaltGenerator();

        String originalPassword = "Rabba123";
        String hashedPassword = hashCodeSaltGenerator.hashPassword(originalPassword);

        String candidatePassword = "Rabba123";
        if (hashCodeSaltGenerator.verifyPassword(candidatePassword, hashedPassword)) {
//            System.out.println(“Correct Password");
            System.out.println("correct");
        } else {
            System.out.println("Incorrect Password");
        }
    }
}
