package proyecto.com.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import proyecto.com.repository.LogRepository;

@Service("LogService")
public class LogService {
	
	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;
	
	public void guardarRegistro(String accion) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		logRepository.save(new proyecto.com.entity.Log(new Date(), auth.getDetails().toString(), accion,  username));
	}
}
