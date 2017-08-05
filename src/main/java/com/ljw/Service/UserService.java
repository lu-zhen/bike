package com.ljw.Service;

import com.ljw.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liujiawang on 2017/8/3.
 */
public interface UserService extends JpaRepository<UserEntity,Integer> {
    UserEntity findByUsername(String username);
}
