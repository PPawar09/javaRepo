package com.journaldev.spring.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.journaldev.spring.dao.UserDaoImpl;
import com.journaldev.spring.model.UserTaskRecord;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@Mock
	UserDaoImpl userDaoImplMock;

	@InjectMocks
	UserService userService;
	
	@Test
	public void testGetTaskByUserId() {
		
		List<UserTaskRecord> utrList = null;
		
		when(userDaoImplMock.getTaskById("123")).thenReturn(utrList);
		assertEquals(24, userService.getTaskByUserId("123"));
	}

}
