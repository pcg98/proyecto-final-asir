package proyecto.com.model;



public class UserModel {
	
	private String username;
	
	private String password;
	
	private boolean enabled;

	private String userRole;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UserModel [username=" + username + ", password=" + password + ", enabled=" + enabled + ", userRole="
				+ userRole + "]";
	}

	public UserModel(String username, String password, boolean enabled, String userRole) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}
	public UserModel() {
	}
	
}
