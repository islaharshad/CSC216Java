package edu.ncsu.csc216.pack_scheduler.user;

/**Abstract class used to define fields and behaviors for Student and Registrar
 * @author Wil Elias
 * @author Addison Garrigus
 */
public abstract class User {

	private String firstName;
	private String lastName;
	private String id;
	private String email;
	private String password;

	/** Basic constructor for the User Class
	 * @param fname - the first name
	 * @param lname - the last name
	 * @param id - the id 
	 * @param email - the email
	 * @param pw - the password
	 */
	public User(String fname, String lname, String id, String email, String pw) {
		setFirstName(fname);
		setLastName(lname);
		setId(id);
		setEmail(email);
		setPassword(pw);
	}

	/**
	 * getter for firstName
	 * @return the students first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * setter for First Name
	 * @param firstName the first name of the Student
	 * @throws IllegalArgumentException if the name is null or empty
	 */
	public void setFirstName(String firstName) throws IllegalArgumentException {
		if(firstName == null || firstName.equals("")){
			throw new IllegalArgumentException("Invalid first name");
		}
		
		this.firstName = firstName;
	}

	/**
	 * getter for lastName
	 * @return the students last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * setter for lastName
	 * @param lastName the last name of the student
	 * @throws IllegalArgumentException if the name is null or empty
	 */
	public void setLastName(String lastName) throws IllegalArgumentException {
		if(lastName == null || lastName.equals("")){
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * getter for id
	 * @return the student ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * setter for id
	 * @param id the student ID
	 * @throws IllegalArgumentException if the id is null or empty
	 */
	public void setId(String id) throws IllegalArgumentException {
		if(id == null || id.equals("")){
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * getter for email
	 * @return the student email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * setter for email
	 * @param email the student email
	 * @throws IllegalArgumentException if the email is not in valid email form: _____at___.___
	 */
	public void setEmail(String email) throws IllegalArgumentException {
		if(email == null || email.equals("")){
			throw new IllegalArgumentException("Invalid email");
		}
		if(!(email.contains("@") && email.contains("."))){
			throw new IllegalArgumentException("Invalid email");
		}
		if(email.lastIndexOf(".") < email.indexOf("@")){
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * getter for password
	 * @return the student's hashed password 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * setter for password
	 * @param password the hashed student password
	 * @throws IllegalArgumentException if the password is null or empty
	 */
	public void setPassword(String password) throws IllegalArgumentException {
		if(password == null || password.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
		result = prime * result + ((getFirstName() == null) ? 0 : getFirstName().hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getLastName() == null) ? 0 : getLastName().hashCode());
		result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (getEmail() == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!getEmail().equals(other.getEmail()))
			return false;
		if (getFirstName() == null) {
			if (other.getFirstName() != null)
				return false;
		} else if (!getFirstName().equals(other.getFirstName()))
			return false;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		if (getLastName() == null) {
			if (other.getLastName() != null)
				return false;
		} else if (!getLastName().equals(other.getLastName()))
			return false;
		if (getPassword() == null) {
			if (other.getPassword() != null)
				return false;
		} else if (!getPassword().equals(other.getPassword()))
			return false;
		return true;
	}
}