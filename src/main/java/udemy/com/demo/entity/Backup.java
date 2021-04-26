package udemy.com.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="backup", uniqueConstraints = @UniqueConstraint(
		columnNames= { "archivo"}))
public class Backup {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="archivo", unique=true)
	private String archivo;
	
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="fecha")
	private Date fecha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Backup(String archivo, String usuario, Date fecha) {
		super();
		this.archivo = archivo;
		this.usuario = usuario;
		this.fecha = fecha;
	}
	public Backup() {
	}
	
}
