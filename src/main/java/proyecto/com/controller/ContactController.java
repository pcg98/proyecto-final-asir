package proyecto.com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import proyecto.com.model.ContactModel;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	private static final Log Logger = LogFactory.getLog(LoginController.class);
	@GetMapping("/cancel")
	public String cancelRedirect() {
		return "contacts";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping("/contactform")
	public String redirectForm(Model model) {
		model.addAttribute("contactModel", new ContactModel());
		return "contactform";
	}
	@GetMapping("/showcontacts")
	public ModelAndView showContacts() {
		ModelAndView mav = new ModelAndView("contacts");
		Logger.info("Method: showContacts ");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("username", user.getUsername());
		//Carpeta
		try {
			//Pasamos directorio carpeta
			File carpeta = new File("./src/main/resources/backups");
			//Creamos variable que tendra los archivos y se los asignamos
			String[] archivos;
			archivos = carpeta.list();
			mav.addObject("archivos", archivos);
		}
		//En caso de dar error
		catch (Exception e) {
			mav.addObject("archivos", "Error");
		}
		return mav;
	}
	
	/**
	 * Adds the contact.
	 *
	 * @param contactModel the contact model
	 * @return the string
	 */
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel) {
		Logger.info("Method: addContact --PARAMS userCredential " +contactModel.toString());
		return "contacts";
	}
	//Metodo Backup
	@GetMapping("/backup")
	public String backupContact() {
		Logger.info("Tarea programada ");
		try {
			//Saco usuario
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			//Lo muestro
			Logger.info("Method: addContact --PARAMS userCredential " + user.getUsername());
			Runtime.getRuntime().exec("cmd /c start C:\\Users\\Pablo\\Desktop\\Grado\\2º\\proyecto\\backup\\script_backup.bat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "contactform";
    }
	//Metodo listar dir
		@GetMapping("/listar_backups")
		public ModelAndView listBackup() {
			Logger.info("Tarea programada ");
			ModelAndView mav = new ModelAndView("contacts");
			try {
				//Pasamos directorio carpeta
				File carpeta = new File("C:\\Users\\Pablo\\Desktop\\Grado\\Cursillos\\Spring\\proyecto\\demo\\src\\main\\resources\\backups");
				//Creamos variable que tendra los archivos y se los asignamos
				String[] archivos;
				archivos = carpeta.list();
				mav.addObject("archivos", archivos);
			}
			//En caso de dar error
			catch (Exception e) {
				mav.addObject("archivos", "Error");
			}
			return mav;
		  }
		//Metodo descargar
		@RequestMapping(value = "/download/{file_name}", method = RequestMethod.GET)
	    public StreamingResponseBody getSteamingFile(@PathVariable("file_name")String archivo, HttpServletResponse response) throws IOException {

	        response.setContentType("text/sql;charset=UTF-8");
	        response.setHeader("Content-Disposition", "attachment; filename=\"webpage.sql\"");
	        Logger.info("Return: Download"+archivo );
	        InputStream inputStream = new FileInputStream(new File("C:\\Users\\Pablo\\Desktop\\Grado\\Cursillos\\Spring\\proyecto\\demo\\src\\main\\resources\\backups\\"+archivo));

	        return outputStream -> {
	            int nRead;
	            byte[] data = new byte[1024];
	            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
	                outputStream.write(data, 0, nRead);
	            }
	            inputStream.close();
	        };
	    }
	     
}
