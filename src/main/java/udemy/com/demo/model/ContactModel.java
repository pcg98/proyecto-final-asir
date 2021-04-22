package udemy.com.demo.model;

import javax.persistence.Column;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactModel.
 */
public class ContactModel {
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ContactModel [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", telephone="
				+ telephone + ", city=" + city + "]";
	}
	
	/** The id. */
	private int id;
	
	/** The first name. */
	private String firstName;
	
	/** The second name. */
	private String secondName;
	
	/** The telephone. */
	private int telephone;
	
	/** The city. */
	private String city;
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the second name.
	 *
	 * @return the second name
	 */
	public String getSecondName() {
		return secondName;
	}
	
	/**
	 * Sets the second name.
	 *
	 * @param secondName the new second name
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	/**
	 * Gets the telephone.
	 *
	 * @return the telephone
	 */
	public int getTelephone() {
		return telephone;
	}
	
	/**
	 * Sets the telephone.
	 *
	 * @param telephone the new telephone
	 */
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Instantiates a new contact model.
	 *
	 * @param id the id
	 * @param firstName the first name
	 * @param secondName the second name
	 * @param telephone the telephone
	 * @param city the city
	 */
	public ContactModel(int id, String firstName, String secondName, int telephone, String city) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.telephone = telephone;
		this.city = city;
	}
	
	/**
	 * Instantiates a new contact model.
	 */
	public ContactModel() {
		
	}
	
}
