package com.wk.service;

import com.wk.entity.User;
import com.wk.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Override
	public int insertOne(User user) {
		int count = 0;
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				userMapper.insertOne(user);
				int i = 1/0;
			}
		});
		return count++;
	}

	@Override
	public List<User> selectAll() {
		List<User> users = userMapper.selectAll();
		System.out.println(users.toString());
		return users;
	}
}
