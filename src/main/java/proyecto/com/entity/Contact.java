package proyecto.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class Contact {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="SecondName")
	private String secondName;
	
	@Column(name="Telephone")
	private int telephone;
	
	@Column(name="City")
	private String city;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public Contact(int id, String firstName, String secondName, int telephone, String city) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.telephone = telephone;
		this.city = city;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Contact() {
	}
	
	
}
