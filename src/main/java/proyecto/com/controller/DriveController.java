package proyecto.com.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import proyecto.com.entity.Backup;
import proyecto.com.repository.BackupRepository;
import proyecto.com.service.FileService;
import proyecto.com.service.LogService;
@Controller
@RequestMapping("/drive")
public class DriveController {

	private static final Log Logger = LogFactory.getLog(LoginController.class);
	
	@Autowired
	@Qualifier("backupRepository")
	private BackupRepository backupRepository;
	//Verificamos integridad del archivo y si no se borra
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	//Logger
	@Autowired
	@Qualifier("logService")
	private LogService logService;
	
	
	//Upload
	@RequestMapping(value = "/upload/{id}", method = RequestMethod.GET)
	private String upload(@PathVariable("id")int id, HttpServletResponse response) throws IOException {
		Logger.info("Tarea programada ");
		//Saco usuario
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Backup archivo = backupRepository.getOne(id);
		archivo.setDrive(true);
		ProcessBuilder pb = new ProcessBuilder("src/main/resources/scripts/script_drive_upload.sh", archivo.getArchivo());
		pb.start();
		
		String action = "upload_drive";
		logService.debug(action, archivo.getArchivo());
		
		return "redirect:/backup/listar_backups";
	}
	
	//Delete
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	private String delete(@PathVariable("id")int id, HttpServletResponse response) throws IOException {
		Logger.info("Tarea programada ");
		//Saco usuario
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Backup archivo = backupRepository.getOne(id);
		
		ProcessBuilder pb = new ProcessBuilder("src/main/resources/backups/script_drive_delete.sh", archivo.getArchivo());
		pb.start();
		archivo.setDrive(false);
		
		String action = "delete_drive";
		logService.debug(action, archivo.getArchivo());
		fileService.mantenceFiles(archivo);
		
		return "redirect:/backup/listar_backups";
	}
	
	//Download
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	private String download(@PathVariable("id")int id, HttpServletResponse response) throws IOException {
		Logger.info("Tarea programada ");
		//Saco usuario
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Backup archivo = backupRepository.getOne(id);
		ProcessBuilder pb = new ProcessBuilder("src/main/resources/backups/script_drive_download.sh", archivo.getArchivo());
		pb.start();
		
		archivo.setLocal(true);
		String action = "download_drive";
		logService.debug(action, archivo.getArchivo());
		
		return "redirect:/backup/listar_backups";
	}
	//Restore from drive
	@RequestMapping(value = "/restore/{id}", method = RequestMethod.GET)
	private String restore(@PathVariable("id")int id, HttpServletResponse response) throws IOException {
		//Saco usuario
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Backup archivo = backupRepository.getOne(id);
		//Lo traigo de la nube
		ProcessBuilder pdrive = new ProcessBuilder("src/main/resources/backups/script_drive_download.sh", archivo.getArchivo());
		pdrive.start();
		archivo.setLocal(true);
		
		String action = "restore_drive";
		logService.debug(action, archivo.getArchivo());
		
		/*Lo restauro
		ProcessBuilder prestore = new ProcessBuilder("src/main/resources/scripts/script_restauracion.sh", archivo.getArchivo());
		prestore.start();
		*/
		//Envio a su correspondiente controlador
		return "redirect:/backup/restore/"+archivo.getArchivo();
	}
}
