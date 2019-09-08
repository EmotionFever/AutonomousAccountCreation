package netflixcreator.app.domain;

public class CardInformation {

	private String number;
	private String validity;
	private String code;
	
	public CardInformation(String number, String validity, String code) {
		super();
		this.number = number;
		this.validity = validity;
		this.code = code;
	}
	
	public String getNumber() {
		return number;
	}
	public String getValidity() {
		return validity;
	}
	public String getCode() {
		return code;
	}
	
	public String toString() {
		return "Number: " + number + "\n" +
				"Validity: " + validity + "\n" +
				"Code: " + code;
		
	}
}
