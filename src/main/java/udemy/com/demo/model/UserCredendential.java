package udemy.com.demo.model;

public class UserCredendential {
	private String username;
	private String password;

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

	public UserCredendential(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public UserCredendential() {
	}

	@Override
	public String toString() {
		return "UserCredendential [username=" + username + ", password=" + password + "]";
	}

	

}
