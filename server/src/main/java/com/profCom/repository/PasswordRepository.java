package com.profCom.repository;

import com.profCom.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface PasswordRepository extends JpaRepository<Password,String> {
}
