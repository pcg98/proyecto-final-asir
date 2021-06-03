package proyecto.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import proyecto.com.entity.Backup;
import proyecto.com.repository.BackupRepository;

@Service("fileService")
public class FileService {

	@Autowired
	@Qualifier("backupRepository")
	private BackupRepository backupRepository;
	
	public void mantenceFiles(Backup archivo) {
		if (!archivo.getDrive() && !archivo.getLocal()) {
			backupRepository.delete(archivo);
		}
	}
}
