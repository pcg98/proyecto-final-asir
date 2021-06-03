package proyecto.com.controller;


import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import proyecto.com.repository.UserRepository;
import proyecto.com.repository.UserRoleRepository;
import proyecto.com.constant.ViewConstant;
import proyecto.com.entity.User;
import proyecto.com.entity.UserRole;
import proyecto.com.model.UserModel;
@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	
	@Autowired
	@Qualifier("userRoleRepository")
	private UserRoleRepository userRoleRepository;

	
	private static final Log Logger = LogFactory.getLog(LoginController.class);
	
	BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
	//Listar
	@GetMapping("/list")
	public ModelAndView listUsers(@RequestParam(name="exito", required=false) String exito, Model model, 
			@RequestParam(name="error", required=false) String error) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_USER);
		model.addAttribute("exito", exito);
		model.addAttribute("error", error);
		mav.addObject("users", userRepository.findAll());
		return mav;
	}
	//Mostrar formulario
	@GetMapping("/userform")
	public String userForm(Model model) {
		model.addAttribute("UserModel", new UserModel());
		return ViewConstant.USER_FORM;
	}
	//Crear
	@PostMapping("/add-user")
	public String createUser(@ModelAttribute("UserModel") UserModel userModel,
			Model model) {
		Logger.info("Method: addUser --PARAMS user: " +userModel.toString()+ " Model: " +model);
		userModel.setPassword(pe.encode(userModel.getPassword()));
		//Creo objeto usuario
		User usuario = new User(userModel.getUsername(), userModel.getPassword(), userModel.isEnabled());
		//Creo objeto userRole
		UserRole userRole = new UserRole(usuario, userModel.getUserRole());
		if(null != userRepository.save(usuario) && null != userRoleRepository.save(userRole)) {
			model.addAttribute("exito", 1);
		}else {
			model.addAttribute("error", 1);
		}
		return "redirect:/user/list";
	}
	//Borrar
	@RequestMapping(value = "/delete/{username}", method = RequestMethod.GET)
	private String delete(@PathVariable("username")String user, HttpServletResponse response,
			Model model) {
		Logger.info("Method: delete --PARAMS user: " +user);
		//userRepository.deleteById(user);
		model.addAttribute("exito", 1);
		return "redirect:/user/list";
	} 
}
