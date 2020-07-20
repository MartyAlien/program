package com.program.daoimp;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.program.dao.DAO;
import com.program.druidJDBCutil.DruidJDBC;
import com.program.master.Master;

public class MasterDAOImp implements DAO{
	DataSource dataSource=DruidJDBC.getDataSource();
	JdbcTemplate jt=new JdbcTemplate(dataSource);
	String sql;
	@Override
	public List<Master> getRightPost(String userName, String password){
		// TODO Auto-generated method stub
		sql="select * from master where masterName = ? and password = ?";
		List<Master> masters=null;
		masters = jt.query(sql, new BeanPropertyRowMapper<Master>(Master.class),userName,password);
		return masters;
	}
}
