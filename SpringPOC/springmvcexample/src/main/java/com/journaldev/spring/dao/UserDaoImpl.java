package com.journaldev.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.journaldev.common.DateUtil;
import com.journaldev.spring.model.Login;
import com.journaldev.spring.model.User;
import com.journaldev.spring.model.UserTaskRecord;

public class UserDaoImpl implements UserDao {
	@Autowired
	DataSource datasource;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private String inserLoginUsrQry = "INSERT INTO LOGIN_USER_DATA (FIRST_NAME,LAST_NAME,EMAIl_ID,LOGIN_ID,PASSWORD,REC_DATE) VALUES (?,?,?,?,?,?)";

	public void register(User user) {
		String sql = "insert into users values(?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { user.getUserName(), user.getPassword(), user.getFirstname(),
				user.getLastname(), user.getEmail(), user.getAddress(), user.getPhone() });
	}
	
	public Login validateUser(Login login) {
		String sql = "SELECT * FROM LOGIN_USER_DATA where LOGIN_ID='" + login.getLoginId() + "' and PASSWORD='" + login.getPassword()+ "'";
		List<Login> loginUsr = null;
		try{
			loginUsr = jdbcTemplate.query(sql, new ResultSetExtractor<List<Login>>() {
				List<Login> listUser = new ArrayList<Login>();
				public List<Login> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					while (rs.next()) {
						Login user = new Login();
						user.setId(rs.getString("ID"));

						listUser.add(user);
					}
					return listUser;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return loginUsr.size() > 0 ? loginUsr.get(0) : null;
	}

	/*public User validateUser(Login login) {
		String sql = "SELECT * FROM LOGIN_USER_DATA where LOGIN_ID='" + login.getLoginId() + "' and PASSWORD='" + login.getPassword()+ "'";
		List<User> users = null;
		try{
			users = jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {
				List<User> listUser = new ArrayList<User>();
				public List<User> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					while (rs.next()) {
						User user = new User();
						user.setFirstname(rs.getString("firstname"));
						user.setLastname(rs.getString("lastname"));
						user.setEmail(rs.getString("email"));
						user.setAddress(rs.getString("address"));
						user.setPhone(Integer.toString(rs.getInt("phone")));

						listUser.add(user);
					}
					return listUser;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return users.size() > 0 ? users.get(0) : null;
	}*/
	
	public boolean registerUser(Login login) {
		int dbRetCode;
		dbRetCode = jdbcTemplate.update(inserLoginUsrQry, new Object[] { login.getFirstName(), login.getLastName(),login.getEmailId(),
				login.getLoginId(), login.getPassword(), DateUtil.getCurrentSdfDate("yyyy-MM-dd") });
		System.out.println("***********"+ dbRetCode);
		if (dbRetCode == 1)
			return true;
		return false;
	}
	
	public List<UserTaskRecord> getTaskById(String id) {
		
		String selectTSRecQry = "SELECT * FROM TIME_SHEET_REC where USER_ID='" + id + "'";
		
		List<UserTaskRecord> utrList = null;
		try{
			utrList = jdbcTemplate.query(selectTSRecQry, new ResultSetExtractor<List<UserTaskRecord>>() {
				List<UserTaskRecord> listUser = new ArrayList<UserTaskRecord>();
				public List<UserTaskRecord> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					while (rs.next()) {
						UserTaskRecord utr = new UserTaskRecord();
						utr.setUserId(rs.getString("USER_ID"));
						utr.setBillingStatus(rs.getString("BILLING_STATUS"));
						utr.setClient(rs.getString("CLIENT"));
						utr.setStartDate(rs.getString("START_DATE"));
						utr.setTaskDesc(rs.getString("TASK_DESC"));
						utr.setUserName(rs.getString("USER_NAME"));

						listUser.add(utr);
					}
					return listUser;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return utrList;
	}
}




