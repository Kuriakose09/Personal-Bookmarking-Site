package com.example.Bookmarking.DTO;

public class UserDto {
	   
    private String email;
    private String password;
    private String fullname;
   
    public UserDto(String email, String password, String fullname) {
        
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public UserDto() {
		// TODO Auto-generated constructor stub
    	super();
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
   
}
