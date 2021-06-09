package proyecto.com.repository;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proyecto.com.entity.Backup;
import proyecto.com.entity.Log;

@Repository("logRepository")
public interface LogRepository extends JpaRepository<Log, Serializable>{
	
	@Modifying
	@Query("delete from Log l where l.date between ?1 and ?2")
	void deleteLog(Date fecha_a, Date fecha_b);
	
	public List<Log> findAllByOrderByDateDesc();
}
