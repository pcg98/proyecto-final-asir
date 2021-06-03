package proyecto.com.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import proyecto.com.entity.User;
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
	
	public abstract User findByUsername(String username);	

	public abstract void deleteByUsername(String username); 
	
	User findById(int id);

}
