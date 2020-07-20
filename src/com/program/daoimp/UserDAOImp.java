package com.program.daoimp;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.program.dao.DAO;
import com.program.druidJDBCutil.DruidJDBC;
import com.program.user.User;

public class UserDAOImp implements DAO{
	DataSource dataSource=DruidJDBC.getDataSource();
	JdbcTemplate jt=new JdbcTemplate(dataSource);
	String sql;
	@Override
	public List<User> getRightPost(String userName, String password){
		// TODO Auto-generated method stub
		sql="select * from user where userName = ? and userPassword = ?";
		List<User> user=null;
		user = jt.query(sql, new BeanPropertyRowMapper<User>(User.class),userName,password);
		return user;
	}

}
