package DO;

public class User {

	private String name;
	private String adress;
	private String FacebookUrl;
	private String email;
	// private String city;
	private String cp;

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public User() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getFacebookUrl() {
		return FacebookUrl;
	}

	public void setFacebookUrl(String facebookUrl) {
		FacebookUrl = facebookUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
