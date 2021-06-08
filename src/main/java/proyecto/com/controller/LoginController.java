package proyecto.com.controller;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import proyecto.com.repository.LogRepository;

@Controller
public class LoginController {
	
	private static final Log Logger = LogFactory.getLog(LoginController.class);
	
	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;
	
	//Aqui nos pide user y pass
	@GetMapping("/login")
	public String showLoginForm(@RequestParam(name="error", required=false) String error, Model model, 
			@RequestParam(name="logout", required=false) String logout) {
		Logger.info("Method: showLoginForm --PARAMS error: " +error+ " Model: " +model);
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		Logger.info("Return: login ");
		return "login";
	}
	//Aqui vamos cuando hacemos loggin pero no esta indicado aqui
	@GetMapping({"/loginsuceess", "/"})
	public String loginCheck() {
		Logger.info("Method: loginCheck");
		Logger.info("Return: contacts view" );
		//Esta es la parte que controla el login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		String url = "login";
		if(null != auth && auth.isAuthenticated()) {
			username = auth.getName();
		}
		logRepository.save(new proyecto.com.entity.Log(new Date(), auth.getDetails().toString(), url,  username));
		return "redirect:/backup/listar_backups";
	}
	//Aqui vamos cuando hacemos logout
	@GetMapping("/disconect")
	public String logout(){
		Logger.info("Method: logout");
		Logger.info("Return: login view" );
		//Esta es la parte que controla el login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		String url = "logout";
		if(null != auth && auth.isAuthenticated()) {
			username = auth.getName();
		}
		logRepository.save(new proyecto.com.entity.Log(new Date(), auth.getDetails().toString(), url,  username));
		return "redirect:/logout";
	}
}
