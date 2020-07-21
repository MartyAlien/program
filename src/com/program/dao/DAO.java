package com.program.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO {
	List<?> getRightPost(String userName,String password);
	int insertObj(List<?> list) throws SQLException;
}
