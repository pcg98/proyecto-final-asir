package udemy.com.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Controller
@RequestMapping("/backup")
public class BackupController {
	
	private static final Log Logger = LogFactory.getLog(LoginController.class);
	
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
	        return outputStream -> {
	            int nRead;
	            byte[] data = new byte[1024];
	            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
	                outputStream.write(data, 0, nRead);
	            }
	            inputStream.close();
	        };
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
			}
}
