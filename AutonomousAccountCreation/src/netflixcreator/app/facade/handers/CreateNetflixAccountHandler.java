package netflixcreator.app.facade.handers;

import java.awt.AWTException;
import java.awt.Robot;

import netflixcreator.utils.Navigation;
import netflixcreator.utils.Pair;
import netflixcreator.app.domain.CardCreator;
import netflixcreator.app.domain.CardInformation;
import netflixcreator.app.domain.Date;
import netflixcreator.app.domain.EmailCreator;
import netflixcreator.app.domain.FormInformationSapo;
import netflixcreator.app.domain.NetflixCreator;

public class CreateNetflixAccountHandler {

	private CardInformation card;
	private String email;
	private Robot robot = null;
    private FormInformationSapo formInfo;
    
    private static CreateNetflixAccountHandler INSTANCE = null;
    
	private CreateNetflixAccountHandler()  {
		this.formInfo = new FormInformationSapo();
		fillFormInfo();
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static CreateNetflixAccountHandler getInstance() {
		if(INSTANCE == null)
			INSTANCE = new CreateNetflixAccountHandler();
		return INSTANCE;
	}
	
	private void fillFormInfo() {
		
		this.formInfo.setName("Thjanskd");
		////this.formInfo.setPrefix(prefixEmail);
		this.formInfo.setPassword("123456"); // tem d conter 1 algarismo e letra maiuscula
		this.formInfo.setMan(true);
		this.formInfo.setBirthday(new Date(30,4,1978));
		this.formInfo.setRecoveryEmail("emotionfever@gmail.com");
	}
	/**
	 * Creates email for username of the Netflix account
	 * @param prefixEmail initial part of email to add numbers
	 * @return Image of captcha to solve
	 */
	public void createEmail(String prefixEmail)  {
		
		String completePrefix = EmailCreator.getCompletePrefix(prefixEmail);
		this.formInfo.setPrefix(completePrefix);
		this.email = EmailCreator.getNewEmail(completePrefix);
		Navigation.openWebpage("https://mail.sapo.pt/registo/");
		EmailCreator.fillForm(this.robot, this.formInfo);
	}
	
	/**
	 * Creates a card for Netflix
	 * @param name
	 */
	public void createCard(String name, String passMBWAY) {
		CardCreator.openApp();
		this.card = CardCreator.createCard(this.robot, name, passMBWAY);
	}
	
	/**
	 * Creates a Netflix account
	 * @param passwordPedro	Primas
	 * @return username and password of Netflix account
	 */
	public Pair<String, String> createNetflix(String password) {
		//this.card = new CardInformation("5309770043428981","10/19","113");
		Navigation.openWebpage("https://www.netflix.com/signup?locale=pt-PT");
		return NetflixCreator.fillRegistration(this.card, this.email, this.robot);
	}
}
