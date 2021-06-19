package proyecto.com.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import proyecto.com.constant.ViewConstant;
import proyecto.com.entity.User;
import proyecto.com.repository.LogRepository;
import proyecto.com.service.LogService;

@Controller
@RequestMapping("/logs")
public class LogController {

	private static final Log Logger = LogFactory.getLog(LogController.class);
	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;
	
	//Logger
	@Autowired
	@Qualifier("logService")
	private LogService logService;
		
	//Listar todos los logs
	@GetMapping("/list")
	public ModelAndView listUsers(@RequestParam(name="exito", required=false) String exito, Model model, 
			@RequestParam(name="error", required=false) String error) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_LOGS);
		model.addAttribute("exito", exito);
		model.addAttribute("error", error);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", user.getUsername());
		mav.addObject("logs", logRepository.findAllByOrderByDateDesc());
		return mav;
	}
	//Borrar todos
	@GetMapping("/delete_all")
	public String deleteAll(HttpServletResponse response, Model model) {
		Logger.info("Method: delete all logs ");
		/*userServiceImpl.removeUser(user);
		User usuario= userRepository.findById(user);*/
		logRepository.deleteLogs();
		logService.debug("borrar_todos");
		model.addAttribute("exito", 1);
		return "redirect:/logs/list";
	}
	//Borrar antiguos
	@GetMapping("/delete_older")
	public String delete_olders(HttpServletResponse response, Model model) {
		Logger.info("Method: delete older logs ");
		/*userServiceImpl.removeUser(user);
		User usuario= userRepository.findById(user);*/
		logRepository.deleteOlderLogs();
		logService.debug("borrar_antiguos");
		model.addAttribute("exito", 1);
		return "redirect:/logs/list";
	}
}
