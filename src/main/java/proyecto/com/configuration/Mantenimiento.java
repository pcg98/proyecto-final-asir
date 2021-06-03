package proyecto.com.configuration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import proyecto.com.controller.LoginController;
import proyecto.com.repository.BackupRepository;
import proyecto.com.service.FileService;
import proyecto.com.service.LogService;

@Configuration
@EnableScheduling
public class Mantenimiento {
	private static final Log Logger = LogFactory.getLog(Mantenimiento.class);
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
	
	//Hace la copia todas las noches a las 12
	@Scheduled(cron = "0 0 0 * * *")
	public void copiaProgramada() throws IOException {
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
		backupRepository.save(new proyecto.com.entity.Backup(fichero, "SYSTEM", new Date(),true,true));
		String action = "new_backup";
		logService.debug(action, fichero);
	}
	public void integridadBase() {
		
	}
	
}
