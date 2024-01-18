package com.example.demo.repos;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
    User findUserByEmail(String email);
    @Transactional
    User deleteUserByIduser(int id);
    User deleteById(int id);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);


//    @Query(value = "SELECT * FROM User u WHERE u.gender like %:keyword% or u.country like %:keyword%", nativeQuery = true)
//    List<User> findByKeyword(@Param("keyword") String keyword);

    @Query("SELECT p FROM User p WHERE CONCAT(p.gender, ' ', p.country, ' ', p.age, ' ', p.city) LIKE %?1%")
    public List<User> search(String keyword);

}
