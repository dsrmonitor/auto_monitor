package com.sys.frotainteligente.Repository;

import com.sys.frotainteligente.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u.id FROM  User u WHERE u.nome =:username AND u.password =:password")
    Long getUserId(@Param("username") String username, @Param("password") String password);

}
