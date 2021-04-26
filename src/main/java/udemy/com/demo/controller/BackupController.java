package udemy.com.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import udemy.com.demo.entity.Backup;
import udemy.com.demo.repository.BackupRepository;
import udemy.com.demo.repository.LogRepository;

@Controller
@RequestMapping("/backup")
public class BackupController {
	
	private static final Log Logger = LogFactory.getLog(LoginController.class);
	
	@Autowired
	@Qualifier("backupRepository")
	private BackupRepository backupRepository;
	//Metodo descargar
	@RequestMapping(value = "/download/{file_name}", method = RequestMethod.GET)
    public StreamingResponseBody getSteamingFile(@PathVariable("file_name")String archivo, HttpServletResponse response) throws IOException {

        response.setContentType("text/sql;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\""+archivo+"\"");
      //Saco usuario
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//Lo muestro
		Logger.info("Method: descargar --PARAMS user "+ user.getUsername() +" Archivo:" +archivo);
        InputStream inputStream = new FileInputStream(new File("src/main/resources/backups/"+archivo));
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
		//Metodo para hacer Backup
		@GetMapping("/backup")
		public void backupContact() {
			Logger.info("Tarea programada ");
			try {
				//Saco usuario
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String username ="";
				if(null != auth && auth.isAuthenticated()) {
					username = auth.getName();
				}
				//Lo muestro
				Logger.info("Method: addContact --PARAMS userCredential " + auth.getName());
				//Nombre archivo
				String fichero = new SimpleDateFormat("'copia_seguridad_'yyyy-MM-dd_hh-mm-ss'.sql'").format(new Date());
				//Runtime.getRuntime().exec("cmd /c start C:\\Users\\Pablo\\Desktop\\Grado\\Cursillos\\Spring\\proyecto\\demo\\src\\main\\resources\\scripts\\script_backup.bat");
				Runtime.getRuntime().exec("cmd /c start C:\\Users\\Pablo\\Desktop\\Grado\\Cursillos\\Spring\\proyecto\\demo\\src\\main\\resources\\scripts\\script_backup.bat "+fichero);
				backupRepository.save(new udemy.com.demo.entity.Backup(fichero, username, new Date()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*Metodo listar dir
			@GetMapping("/listar_backups")
			public ModelAndView listBackup() {
				Logger.info("Tarea programada ");
				ModelAndView mav = new ModelAndView("contacts");
				try {
					//Pasamos directorio carpeta
					File carpeta = new File("src/main/resources/backups");
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
			  }*/
		//Metodo listar dir
		@GetMapping("/listar_backups")
		public ModelAndView listBackup() {
			Logger.info("Tarea programada ");
			ModelAndView mav = new ModelAndView("contacts");
			try {
				mav.addObject("archivos", backupRepository.findAll());
			}
			//En caso de dar error
			catch (Exception e) {
				mav.addObject("archivos", "No se ha encontrado ningun archivo");
			}
			return mav;
		  }
		//Restaurar backup
		@RequestMapping(value = "/restore/{file_name}", method = RequestMethod.GET)
		private void restore(@PathVariable("file_name")String archivo, HttpServletResponse response) {
			Logger.info("Tarea programada ");
			try {
				//Saco usuario
				User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				String ruta_backup="C:\\Users\\Pablo\\Desktop\\Grado\\Cursillos\\Spring\\proyecto\\demo\\src\\main\\resources\\backups\\"+archivo;
				//Lo muestro
				Logger.info("Method: addContact --PARAMS userCredential " + user.getUsername());
				Runtime.getRuntime().exec("cmd /c start C:\\Users\\Pablo\\Desktop\\Grado\\Cursillos\\Spring\\proyecto\\demo\\src\\main\\resources\\scripts\\script_restauracion.bat "+ruta_backup);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Eliminar backup
		@RequestMapping(value = "/delete/{file_id}", method = RequestMethod.GET)
		private void delete(@PathVariable("file_id")int identificador, HttpServletResponse response) {
			Logger.info("Tarea programada ");
			//Saco usuario
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Backup archivo = backupRepository.getOne(identificador);
			Logger.info("Method: delect --PARAMS archivo " + archivo.getArchivo() + " id " +identificador);
			String ruta_backup="./src/main/resources/backups/"+archivo.getArchivo();
			File fichero = new File(ruta_backup);
			if (fichero.delete()) {
			   System.out.println("El fichero ha sido borrado satisfactoriamente");
			   backupRepository.deleteById(identificador);
			   //backupRepository.deleteByArchivo(archivo);
			}else {
			   System.out.println("El fichero no puede ser borrado");
			}
		}
}
