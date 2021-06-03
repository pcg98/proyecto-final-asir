package proyecto.com.converter;

import org.springframework.stereotype.Component;

import proyecto.com.entity.User;
import proyecto.com.model.UserModel;

@Component("ConvertUserModel")
public class ConvertUserModel {
	public User convertUserModel2User(UserModel userModel) {
		User user = new User();
		//user.setEnabled(userModel.isEnabled());
		user.setPassword(userModel.getPassword());
		user.setUsername(userModel.getUsername());
		return user;
	}
}
