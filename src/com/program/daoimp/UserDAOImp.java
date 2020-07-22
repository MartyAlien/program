package com.program.daoimp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.program.dao.AdapterDAO;
import com.program.dao.DAO;
import com.program.druidJDBCutil.DruidJDBC;
import com.program.user.User;

public class UserDAOImp extends AdapterDAO{
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
	@Override
	public int insertObj(List<?> list) throws SQLException {
		// TODO Auto-generated method stub
		int count=0;
		if(list.size()==0) {
			return count;
		}
		sql="insert into user(userName,userPassword,name,phone,userType,advDeposit,place,available) values(?,?,?,?,?,?,?,?)";
		try {
			for(Object one:list) {
				User user=(User) one;
				PreparedStatement pS = DruidJDBC.getConnection().prepareStatement("select * from user where userName=? or phone=?");
				pS.setString(1, user.getUserName());
				pS.setString(2, user.getPhone());
				ResultSet rs = pS.executeQuery();
				if(rs.next()==true) {
					return -1;
				}else {
					count+=jt.update(sql, user.getUserName(),user.getUserPassword(),user.getName(),user.getPhone(),
							user.getUserType(),user.getAdvDeposit(),user.getPalce(),user.getAvailable());
				}
			}
		}catch (DuplicateKeyException e) {
			return -1;// 表示表中已存在字段唯一属性的记录 -> 标志用户已存在
		}
		return count;
	}
	
}
