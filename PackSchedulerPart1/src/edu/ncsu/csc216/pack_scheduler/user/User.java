package edu.ncsu.csc216.pack_scheduler.user;

/**
 * This is an abstract (super) class of Student and Registrar
 * @author premsubedi
 */
public abstract class User {

	/** The student's first name */
	private String firstName;
	/** The student's last name */
	private String lastName;
	/** The student's id */
	private String id;
	/** The student's email */
	private String email;
	/** The student's password in hash */
	private String hashPW;

	/**
	 * User constructor which initializes all the User fields.
	 * @param firstName first name of the user
	 * @param lastName last name of the user
	 * @param id unity id of the user
	 * @param email email address of the user
	 * @param hashPW password of the user.
	 */
	public User(String firstName, String lastName, String id, String email, String hashPW) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setId(id);
		this.setPassword(hashPW);
	}

	/**
	 * Returns the student's email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the student's email
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		if(email == null || email.equals("")){
			throw new IllegalArgumentException("Invalid email");
		}
		if(!email.contains("@") || !email.contains(".")){
			throw new IllegalArgumentException("Invalid email");
		}
		if(email.lastIndexOf('@') > email.lastIndexOf('.')){
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Returns the student's hashed password
	 * @return the hashPW
	 */
	public String getPassword() {
		return hashPW;
	}

	/**
	 * Sets the student's hash password
	 * @param hashPW the hashPW to set
	 */
	public void setPassword(String hashPW) {
		try
		{
			int x = Integer.parseInt(hashPW);
			if(x >= 0 || x < 0)
				throw new IllegalArgumentException("Invalid password");
		}
		catch(NumberFormatException e)
		{
			if(hashPW == null || hashPW.equals("")){
				throw new IllegalArgumentException("Invalid password");
			}
			this.hashPW = hashPW;
		}
	}

	/**
	 * Sets the student's first name
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		if(firstName == null || firstName.equals("")){
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Sets the student's last name
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		if(lastName == null || lastName.equals("")){
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Sets the student's id
	 * @param id the id to set
	 */
	protected void setId(String id) {
		if(id == null || id.equals("")){
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns the student's first name
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns the student's last name
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Returns the student's id
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((hashPW == null) ? 0 : hashPW.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (hashPW == null) {
			if (other.hashPW != null)
				return false;
		} else if (!hashPW.equals(other.hashPW))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

}