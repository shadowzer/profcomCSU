package com.profCom.repository;

import com.profCom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
