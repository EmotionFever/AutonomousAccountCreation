package netflixcreator.app.domain;

public class FormInformationSapo {

	private String name;
	private String prefixEmail;
	private String password;
	private boolean isMan;
	private Date birthday;
	private String recoveryEmail;
	
	public FormInformationSapo() {
		
	}

	public String getName() {
		return name;
	}
	public String getPrefix() {
		return prefixEmail;
	}
	public String getPassword() {
		return password;
	}
	public boolean isMan() {
		return isMan;
	}
	public Date getBirthday() {
		return birthday;
	}
	public String getRecoveryEmail() {
		return recoveryEmail;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setPrefix(String prefixEmail) {
		this.prefixEmail = prefixEmail;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setMan(boolean isMan) {
		this.isMan = isMan;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setRecoveryEmail(String recoveryEmail) {
		this.recoveryEmail = recoveryEmail;
	}
}
