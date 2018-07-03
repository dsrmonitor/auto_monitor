package com.sys.frotainteligente.Mapper;

import com.sys.frotainteligente.DTO.UserDTO;
import com.sys.frotainteligente.Entity.User;

public class UserMapper {

    public static UserDTO EntityToDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId_user(user.getId());
        userDTO.setTx_nome_user(user.getNome());
        userDTO.setPw_user(user.getPassword());

        return userDTO;
    }
}
