package com.ljw;

import com.ljw.Entity.UserEntity;
import com.ljw.Service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BikeApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void testAdd() {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername("qwer");
		userEntity.setPassword("1234");
		userService.save(userEntity);
	}

}
