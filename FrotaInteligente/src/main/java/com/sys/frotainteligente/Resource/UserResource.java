package com.sys.frotainteligente.Resource;

import com.google.gson.Gson;
import com.sys.frotainteligente.Entity.User;
import com.sys.frotainteligente.Mapper.UserMapper;
import com.sys.frotainteligente.Repository.UserRepository;
import com.sys.frotainteligente.Util.ConstantesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://"+ConstantesUtil.IP_ADDRESS+":4200")
@RequestMapping("/user")
public class UserResource {

    Gson gson = new Gson();

    @Autowired
    UserRepository userRepository;

    @GetMapping("/get-user-id/{username}/{password}")
    public ResponseEntity<String> getUser(@PathVariable String username, @PathVariable String password) {
        if(username.equals("lucas") && password.equals("12345")){
            return ResponseEntity.ok("1");
        }else{
            return ResponseEntity.badRequest().body(null);
        }
//        Long result =  userRepository.getUserId(username,password);
//        return gson.toJson(result);
    }

    @GetMapping("get-user-by-id/{id}")
    public String getUserById(@PathVariable Long id){

        User result = userRepository.getOne(id);
        return gson.toJson(UserMapper.EntityToDTO(result));
    }
}
