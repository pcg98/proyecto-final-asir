package proyecto.com.service;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import proyecto.com.entity.User;
import proyecto.com.controller.LoginController;
import proyecto.com.entity.Backup;
import proyecto.com.repository.LogRepository;
//Esta clase nos sirve para guardar cosas en el log de la base de datos y mostrarlas en la consola too
@Service("logService")
public class LogService {
	
	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;
	
	private static final Log Logger = LogFactory.getLog(LoginController.class);
	
	public void debug(String action, String archivo) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Logger.info("Method: "+action+" --PARAMS user "+ user.getUsername() +" Archivo:" +archivo);
		logRepository.save(new proyecto.com.entity.Log(new Date(), archivo, action,  username));
	}
	public void debug(String action) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Logger.info("Method: "+action+" --PARAMS user "+ user.getUsername());
		logRepository.save(new proyecto.com.entity.Log(new Date(), auth.getDetails().toString(), action,  username));
	}
}
