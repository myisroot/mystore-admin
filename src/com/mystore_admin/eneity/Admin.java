package  com.mystore_admin.eneity;

import java.io.Serializable;
public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String password;

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return username;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}
}

