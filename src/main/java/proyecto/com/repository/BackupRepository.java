package proyecto.com.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.com.entity.Backup;

@Repository("backupRepository")
public interface BackupRepository extends JpaRepository<Backup, Serializable>{
	
}
