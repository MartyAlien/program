package com.program.daoimp;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.program.dao.AdapterDAO;
import com.program.druidJDBCutil.DruidJDBC;
import com.program.payment.Payment;

public class PaymentDAOImp extends AdapterDAO{
	JdbcTemplate jt=new JdbcTemplate(DruidJDBC.getDataSource());
	String sql;
	@Override
	public List<?> selectAll() {
		// TODO Auto-generated method stub
		sql="select * from pay";
		List<Payment> list = jt.query(sql, new BeanPropertyRowMapper<Payment>(Payment.class));
		return list;
	}
	@Override
	public void delete(List<?> list) {
		// TODO Auto-generated method stub
		if (list.size()<=0) {
			return;
		}
		sql="delete from pay where ID=?";
		List<Integer> l=(List<Integer>)list;
		for(int id:l) {
			jt.update(sql, id);
		}
	}
	
}
