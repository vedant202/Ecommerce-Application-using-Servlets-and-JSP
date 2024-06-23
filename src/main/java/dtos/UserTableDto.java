package dtos;

public class UserTableDto {
	private long id;
	private String fname;
	private String lname;
	private String role;
	private String email;


	public UserTableDto(String fname, String lname, String role, String email) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.role = role;
		this.email = email;
	}


	public UserTableDto(long id, String fname, String lname, String role, String email) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.role = role;
		this.email = email;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "UserTableDto [id=" + id + ", fname=" + fname + ", lname=" + lname + ", role=" + role + ", email="
				+ email + "]";
	}




}
