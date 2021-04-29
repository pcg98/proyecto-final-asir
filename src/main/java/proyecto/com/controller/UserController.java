package proyecto.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import proyecto.com.repository.UserRepository;
import proyecto.com.constant.ViewConstant;
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	//Listar
	@GetMapping("/list")
	public ModelAndView listUsers() {
		ModelAndView mav = new ModelAndView(ViewConstant.LIST_USER);
		mav.addObject("users", userRepository.findAll());
		return mav;
	}
	//Actualizar
	@GetMapping("/update")
	public ModelAndView updateUser() {
		ModelAndView mav = new ModelAndView(ViewConstant.USER_FORM);
		
		return mav;
	}
	//Crear
	@GetMapping("/add-user")
	public ModelAndView createUser() {
		ModelAndView mav = new ModelAndView(ViewConstant.USER_FORM);
		
		return mav;
	}
	//Borrar
	
}
