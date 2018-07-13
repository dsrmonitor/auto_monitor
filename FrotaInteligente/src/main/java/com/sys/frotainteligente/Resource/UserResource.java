package com.sys.frotainteligente.Resource;

import com.google.gson.Gson;
import com.sys.frotainteligente.Entity.User;
import com.sys.frotainteligente.Mapper.UserMapper;
import com.sys.frotainteligente.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://192.168.1.110:4200")
@RequestMapping("/user")
public class UserResource {

    Gson gson = new Gson();

    @Autowired
    UserRepository userRepository;

    @GetMapping("/get-user-id/{username}/{password}")
    public String getUser(@PathVariable String username, @PathVariable String password) {
        System.out.println("TEM AQUI");
        Long result =  userRepository.getUserId(username,password);
        return gson.toJson(result);
    }

    @GetMapping("get-user-by-id/{id}")
    public String getUserById(@PathVariable Long id){

        User result = userRepository.getOne(id);
        return gson.toJson(UserMapper.EntityToDTO(result));
    }
}
