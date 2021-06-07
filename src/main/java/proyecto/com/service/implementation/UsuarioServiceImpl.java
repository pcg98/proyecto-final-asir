package proyecto.com.service.implementation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import proyecto.com.controller.LoginController;
import proyecto.com.entity.User;
import proyecto.com.repository.UserRepository;
import proyecto.com.service.UsuariosService;

@Service("userServiceImpl")
public class UsuarioServiceImpl implements UsuariosService{
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	
	private static final Log Logger = LogFactory.getLog(UsuarioServiceImpl.class);
	
	/*@Override
	public User findUserById(int id) {
		Logger.info("Entra al findUserById" +id);
		return userRepository.findById(id);
	}

	@Override
	public void removeUser(int id) {
		Logger.info("Entra al RemoveUser" +id);
		User user = findUserById(id);
		Logger.info("Encuentra?? al User" +user.toString());
		if(user != null) {
			Logger.info("Borra al User" +user.toString());
			userRepository.delete(user);	
		}
	}*/

}
