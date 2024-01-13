package in.ashokit;

public class JwtResponse {

	private String token;
	private String uname;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", uname=" + uname + "]";
	}
	
	
}
