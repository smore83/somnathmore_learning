package Owsap.Assignment.repository;

import Owsap.Assignment.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlRepository  extends JpaRepository<User,Integer> {
    public User findByuserName(String userName);
    @Query("SELECT u FROM User u WHERE ( u.password = :password)")
    User findBypassword(@Param("password") String password);

//    public User findBypassword(String password);
}
