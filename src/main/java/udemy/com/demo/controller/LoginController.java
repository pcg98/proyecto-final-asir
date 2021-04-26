package udemy.com.demo.controller;

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
import udemy.com.demo.repository.LogRepository;

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
		Logger.info("Return: login " );
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
		logRepository.save(new udemy.com.demo.entity.Log(new Date(), auth.getDetails().toString(), url,  username));
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
			logRepository.save(new udemy.com.demo.entity.Log(new Date(), auth.getDetails().toString(), url,  username));
			return "redirect:/logout";
		}
	
	/*
	//Metodo descargar
	@RequestMapping(value = "/download/{file_name}", method = RequestMethod.GET)
    public StreamingResponseBody getSteamingFile(@PathVariable("file_name")String archivo, HttpServletResponse response) throws IOException {

        response.setContentType("text/sql;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\""+archivo+"\"");
      //Saco usuario
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//Lo muestro
		Logger.info("Method: descargar --PARAMS user "+ user.getUsername() +" Archivo:" +archivo);
        InputStream inputStream = new FileInputStream(new File("C:\\Users\\Pablo\\Desktop\\Grado\\Cursillos\\Spring\\proyecto\\demo\\src\\main\\resources\\backups\\"+archivo));
        Logger.info("Method: Dowload"+archivo);
        //Consulta SQL
        /*JdbcTemplate.update(
        	    "INSERT INTO schema.tableName (column1, column2) VALUES (?, ?)",
        	    var1, var2
        	);*/
	/*
        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
        };
    }
	//El backup
	@RequestMapping(value = "/backup/{file_name}", method = RequestMethod.GET)
	private static void restore(@PathVariable("file_name")String archivo, HttpServletResponse response) {
		   try {
		      Process p = Runtime
		            .getRuntime()
		            .exec("C:/Aplicaciones/wamp/bin/mysql/mysql5.1.36/bin/mysql -u root -password database");

		      OutputStream os = p.getOutputStream();
		      FileInputStream fis = new FileInputStream(archivo);
		      byte[] buffer = new byte[1000];

		      int leido = fis.read(buffer);
		      while (leido > 0) {
		         os.write(buffer, 0, leido);
		         leido = fis.read(buffer);
		      }

		      os.flush();
		      os.close();
		      fis.close();

		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		}*/
}
