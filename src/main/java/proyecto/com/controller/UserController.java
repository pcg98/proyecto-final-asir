package proyecto.com.controller;


import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import proyecto.com.repository.UserRepository;
import proyecto.com.service.implementation.UsuarioServiceImpl;
import proyecto.com.constant.ViewConstant;
import proyecto.com.entity.User;
import proyecto.com.model.ContactModel;
import proyecto.com.model.UserModel;
@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UsuarioServiceImpl userServiceImpl;
	
	private static final Log Logger = LogFactory.getLog(LoginController.class);
	
	BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
	
	
	//Listar
	@GetMapping("/list")
	public ModelAndView listUsers(@RequestParam(name="exito", required=false) String exito, Model model, 
			@RequestParam(name="error", required=false) String error) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_USER);
		model.addAttribute("exito", exito);
		model.addAttribute("error", error);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", user.getUsername());
		mav.addObject("users", userRepository.findAll());
		return mav;
	}
	//Mostrar formulario
	@GetMapping("/userform")
	public String userForm(Model model) {
		model.addAttribute("User", new User());
		return ViewConstant.USER_FORM;
	}
	//Crear
	@PostMapping("/add-user")
	public String createUser(@Valid @ModelAttribute("User") User user,
			BindingResult bindingResult, RedirectAttributes redirectAttrs) {
		Logger.info("Method: add user " +user.toString());
		//Con el Bindingresult hacemos las comprobacciones del form
		if(bindingResult.hasErrors()) {
			return "userform";
		}else {
			Logger.info("Method: addUser --PARAMS user: " +user.toString());
			//Encriptamos password
			user.setPassword(pe.encode(user.getPassword()));
			//COmprobamos que no haya nadie con ese nombre de usuario
			if (null == userRepository.findByUsername(user.getUsername())) {
				//Guardamos y comprobamos
				if(null != userRepository.save(user)) {
					 redirectAttrs
		                .addFlashAttribute("mensaje", "Agregado correctamente")
		                .addFlashAttribute("clase", "success");
				}
			}else {
				redirectAttrs
                .addFlashAttribute("mensaje", "Username ya usado")
                .addFlashAttribute("clase", "danger");
			}
		}
		return "redirect:/user/list";
	}
	//Delete
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id")long user_id, HttpServletResponse response,
			Model model, RedirectAttributes redirectAttrs) {
		Logger.info("Method: delete user " +user_id);
		/*userServiceImpl.removeUser(user);
		User usuario= userRepository.findById(user);*/
		userRepository.deleteById(user_id);
		redirectAttrs
        .addFlashAttribute("mensaje", "Eliminado correctamente")
        .addFlashAttribute("clase", "success");
		return "redirect:/user/list";
	} 
	//Update
	@RequestMapping(value = "/form_update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id")long user_id, Model model) {
		model.addAttribute("update_user", userRepository.getOne(user_id));
		return "userform_update";
	}
	//Actualizar
	@PostMapping("/update")
	public String update_user(@ModelAttribute("update_user") User u_user, Model model, RedirectAttributes redirectAttrs) 
	{
		Logger.info("Method: delete user " +u_user.toString());
		User user = userRepository.findByUsername(u_user.getUsername());
		if(!u_user.getPassword().isEmpty()) {
			u_user.setPassword(pe.encode(u_user.getPassword()));
			user.setPassword(u_user.getPassword());
			user.setRol(u_user.getRol());
			userRepository.flush();
		}else {
			user.setRol(u_user.getRol());
			userRepository.flush();
		}
		redirectAttrs
        .addFlashAttribute("mensaje", "Modificado correctamente")
        .addFlashAttribute("clase", "success");
		return "redirect:/user/list";
	}
	
}
