package com.example.felece_ticket.Services;

import com.example.felece_ticket.models.User;
import com.example.felece_ticket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public String add(User user){
        try{
        userRepository.save(user);
        return "success";
        }
        catch (Exception exception){
            return "HATA! telefon numarası veya kullanıcı adı sisteme kayıtlıdır.  "+exception;
        }
    }


    public User findbytel(String tel) {
       return userRepository.findbytel(tel);
    }

    public User findbyusername(String username) {
        return userRepository.findbyusername(username);
    }
    public User findbyid(String id) {
        return userRepository.findbyid(id);
    }

    public String Update(User user,String name,String surname,String tel,Boolean isAdmin,String username, String password) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("tel").is(user.getTel()));
            user.setName(name);
            user.setSurname(surname);
            user.setTel(tel);
            user.setIsAdmin(isAdmin);
            user.setUsername(username);
            user.setPassword(password);

            userRepository.save(user);
            return "Güncelleme Başarılı";
        }
        catch (Exception exception){
            return "Güncelleme Hatalı   "+exception;
        }
    }

    public Boolean passwordControl(String username,String password){
        User user1 =userRepository.findbyusername(username);
        if(password.equals(user1.getPassword()))
        {
            return true;
        }
        else {
            return false;
        }
    }
    public String delete(User user){
        try {
            userRepository.delete(user);
            return "Silme işlemi Başarılı";
        }
        catch (Exception e){
            return "Kişi silinemedi!    "+e;
        }
    }

    public String deletebytel(String tel){
        try{
            User user=userRepository.findbytel(tel);
            userRepository.delete(user);
            return tel + " numaralı üye silinmiştir! ";
        }
        catch (Exception e){
            return "Hata! kişi silinemedi    "+e;
        }
    }


}
