package com.example.demo.Service;


import com.example.demo.entities.AccountType;
import com.example.demo.entities.User;
import com.example.demo.repos.AccountTypeRepository;
import com.example.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    @Autowired
    private AccountTypeRepository roleAccountType;

    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    public User save(User user) {
        return repo.save(user);
    }

    public List<AccountType> listAccountType() {
        return (List<AccountType>) roleAccountType.findAll();
    }

    public User userLogin(String email, String password) {
        User user = repo.findByEmailAndPassword(email, password);
        if (user != null) {

            return user;
        } else {
            return null;
        }

    }
    public User userEmail(String email) {
        User user = repo.findUserByEmail(email);
        return user;

    }



    public Optional<User> findById(int id) {
        Optional<User> user = repo.findById(id);
        return user;
    }
    @Transactional
    public void deleteById(int id) {
        User user = repo.deleteById(id);

    }
    public List<User> listAllS(String keyword) {
    if (keyword != null) {
    return repo.search(keyword);
}
        return (List<User>) repo.findAll();
}

//    public List<User> getByKeyword(String keyword){
//        return repo.findByKeyword(keyword);
//    }

//    public String checkEmailUnique(String email) {
//
//        User useremail = repo.findUserByEmail(email);
//
//        if (useremail != null) {
//            return "Doublon";
//        } else {
//            return "OK";
//        }
//
//    }
}