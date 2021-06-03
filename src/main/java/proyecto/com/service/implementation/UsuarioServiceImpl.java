package proyecto.com.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import proyecto.com.entity.User;
import proyecto.com.repository.UserRepository;
import proyecto.com.service.UsuariosService;

@Service("userServiceImpl")
public class UsuarioServiceImpl implements UsuariosService{
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Override
	public User findUserById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public void removeUser(int id) {
		User user = findUserById(id);
		if(null != user) {
			userRepository.delete(findUserById(id));	
		}
	}

}
