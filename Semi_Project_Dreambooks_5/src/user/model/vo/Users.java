package user.model.vo;

import java.io.Serializable;

public class Users implements Serializable{

	private String userId;
	private String password;
	private String userName;
	private String userRole;
	private String email;
	private String phone;
	private String quitYN;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(String userId, String password, String userName, String userRole, String email, String phone,
			String quitYN) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.userRole = userRole;
		this.email = email;
		this.phone = phone;
		this.quitYN = quitYN;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQuitYN() {
		return quitYN;
	}
	public void setQuitYN(String quitYN) {
		this.quitYN = quitYN;
	}
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", password=" + password + ", userName=" + userName + ", userRole="
				+ userRole + ", email=" + email + ", phone=" + phone + ", quitYN=" + quitYN + "]";
	}
	
	
}
