package proyecto.com.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.annotation.Persistent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;


import proyecto.com.entity.User;
import proyecto.com.entity.UserRole;
import proyecto.com.model.UserModel;

@Repository("DSLUserRepo")
public class DSLUserRepo{
	/*
	private QUser qUser = QUser.user;
	private QUserRole qUser_role = QUserRole.userRole;
	@PersistenceContext
	private EntityManager em;
	
	BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
	
	public void insertUser(UserModel user) {
		JPAQuery<User> userQuery = new JPAQuery<User>(em);
		JPAQuery<UserRole> rolQuery = new JPAQuery<UserRole>(em);
		String username = user.getUsername();
		String password = pe.encode(user.getPassword());
		boolean enabled = user.isEnabled();
		String rol = user.getUserRole();
		userQuery.insert(qUser.username, qUser.username)
	}*/
}
