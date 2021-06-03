package proyecto.com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import proyecto.com.constant.ViewConstant;
import proyecto.com.entity.Backup;
import proyecto.com.entity.User;
import proyecto.com.repository.BackupRepository;
import proyecto.com.service.FileService;
import proyecto.com.service.LogService;

@Controller
@RequestMapping("/backup")
public class BackupController {
	
	private static final Log Logger = LogFactory.getLog(LoginController.class);
	
	@Autowired
	@Qualifier("backupRepository")
	private BackupRepository backupRepository;
	//Logger
	@Autowired
	@Qualifier("logService")
	private LogService logService;
	//Integridad archivos
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	//Metodo descargar
	@RequestMapping(value = "/download/{file_name}", method = RequestMethod.GET)
    public StreamingResponseBody getSteamingFile(@PathVariable("file_name")String archivo, HttpServletResponse response) throws IOException {
		response.setContentType("text/sql;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\""+archivo+"\"");
        
        String action = "download_local";
		logService.debug(action, archivo);
		
	    InputStream inputStream = new FileInputStream(new File("src/main/resources/backups/"+archivo));
	    Logger.info("Method: Dowload"+archivo);
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
		public String backupContact() throws IOException {
			//Nombre archivo
			String fichero = new SimpleDateFormat("'copia_seguridad_'yyyy-MM-dd_hh-mm-ss'.sql'").format(new Date());
			/*Windows
			Runtime.getRuntime().exec("cmd /c start "+ViewConstant.PROYECTO+"src/main/resources/scripts/script_backup.bat "+fichero);*/
			//Linux BACKUP_SCL Local
			ProcessBuilder plocal = new ProcessBuilder("src/main/resources/scripts/script_backup.sh", fichero);
			plocal.start();
			//La nube
			ProcessBuilder pdrive = new ProcessBuilder("src/main/resources/scripts/script_drive_upload.sh", fichero);
			pdrive.start();
			//Saco usuario
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			backupRepository.save(new proyecto.com.entity.Backup(fichero, username, new Date(),true,true));
			
			String action = "new_backup";
			logService.debug(action, fichero);
			
			return "redirect:/backup/listar_backups";
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
			ModelAndView mav = new ModelAndView(ViewConstant.VIEW_BACKUP);
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			mav.addObject("username", user.getUsername());
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
		private String restore(@PathVariable("file_name")String archivo, HttpServletResponse response) throws IOException {
			/*Ejecuccion en windows
			Runtime.getRuntime().exec("cmd /c start "+ViewConstant.PROYECTO+"src/main/resources/scripts/script_restauracion.bat "+ruta_backup);
			*/
			//Linux RESTAURAR
			ProcessBuilder pb = new ProcessBuilder("src/main/resources/scripts/script_restauracion.sh", archivo);
			pb.start();
			
			String action = "upload_drive";
			logService.debug(action, archivo);
			
			return "redirect:/backup/listar_backups";
		}
		//Eliminar backup
		@RequestMapping(value = "/delete/{file_id}", method = RequestMethod.GET)
		private String delete(@PathVariable("file_id")int identificador, HttpServletResponse response) {
			Backup archivo = backupRepository.getOne(identificador);
			String ruta_backup="./src/main/resources/backups/"+archivo.getArchivo();
			//debug
			String action = "delete_local_backup";
			File fichero = new File(ruta_backup);
			if (fichero.delete()) {
			   System.out.println("El fichero ha sido borrado satisfactoriamente");
			   //backupRepository.deleteByArchivo(archivo);
			   archivo.setLocal(false);
			   logService.debug(action, archivo.getArchivo());
			   fileService.mantenceFiles(archivo);
			}else {
			   System.out.println("El fichero no puede ser borrado");
			}
			return "redirect:/backup/listar_backups";
		}
}
