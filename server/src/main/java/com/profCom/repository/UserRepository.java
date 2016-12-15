package com.profCom.repository;

import com.profCom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findByLogin(String login);
}
