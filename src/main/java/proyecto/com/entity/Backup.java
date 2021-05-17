package proyecto.com.entity;

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
	public String archivo;
	
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="local")
	private Boolean local;
	
	@Column(name="drive")
	private Boolean drive;
	

	
	public Backup(String archivo, String usuario, Date fecha, boolean local, boolean drive) {
		super();
		this.archivo = archivo;
		this.usuario = usuario;
		this.fecha = fecha;
		this.local = local;
		this.drive = drive;
	}


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




	public Boolean getLocal() {
		return local;
	}


	public void setLocal(Boolean local) {
		this.local = local;
	}


	public Boolean getDrive() {
		return drive;
	}


	public void setDrive(Boolean drive) {
		this.drive = drive;
	}


	public Backup() {
	}
	
}
