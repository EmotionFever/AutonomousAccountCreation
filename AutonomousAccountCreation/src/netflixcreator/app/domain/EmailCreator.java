package netflixcreator.app.domain;

import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;
import netflixcreator.utils.Configuration;
import netflixcreator.utils.Navigation;

public class EmailCreator {
	
    public static final int INICIALIZATION_TIME = 10000;
    public static final int ONE_SECOND = 1000;
    public static final int WAIT_A_BIT = 200;
    public static final int WAIT_A_BIT_MORE = 500;

	public static void fillForm(Robot robot, FormInformationSapo formInfo) {
		
		
		try {
			
			Thread.sleep(INICIALIZATION_TIME);
			acceptCookies(robot);
			Thread.sleep(WAIT_A_BIT);
			fillName(formInfo.getName(), robot);
			Thread.sleep(WAIT_A_BIT);
			fillPrefixEmail(formInfo.getPrefix(), robot);
			Thread.sleep(WAIT_A_BIT);
			fillPassword(formInfo.getPassword(), robot);
			Thread.sleep(WAIT_A_BIT);
			fillGender(formInfo.isMan(), robot);
			Thread.sleep(WAIT_A_BIT);
			fillBirthday(formInfo.getBirthday(), robot);
			Thread.sleep(WAIT_A_BIT);
			fillRecoveryEmail(formInfo.getRecoveryEmail(), robot);
			Thread.sleep(WAIT_A_BIT);
			fillAuthorization(robot);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void acceptCookies(Robot robot) throws InterruptedException {
		Navigation.clickMouseAt(1110, 527, robot);
		Thread.sleep(ONE_SECOND);
		
		//select name field
		Navigation.clickMouseAt(645, 428, robot);
		Thread.sleep(WAIT_A_BIT);
	}

	private static void fillName(String name, Robot robot) throws InterruptedException {
		Navigation.writeText(name,robot);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT);
	}
	
	private static void fillPrefixEmail(String prefix, Robot robot) throws InterruptedException {
		
		Navigation.writeText(prefix,robot);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT_MORE);
	}
	
	private static void fillPassword(String password, Robot robot) throws InterruptedException {
		
		Navigation.writeText(password,robot);
		Thread.sleep(WAIT_A_BIT);
		Navigation.nextField(robot);
		Navigation.writeText(password,robot);
		Navigation.nextField(robot);
	}

	private static void fillGender(boolean man, Robot robot) throws InterruptedException {
		Navigation.clickMouseAt(562, 416, robot);
		Thread.sleep(WAIT_A_BIT_MORE);
	}

	private static void fillBirthday(Date birthday, Robot robot) throws InterruptedException {
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		Navigation.clickMouseAt(714, 383, robot);
		Thread.sleep(WAIT_A_BIT); 
		Navigation.pressDownNTimes(birthday.getDay(), robot);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT); 
		Navigation.pressDownNTimes(birthday.getMonth(), robot);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT); 
		Navigation.pressDownNTimes(currentYear - birthday.getYear() + 1, robot);
		
		Thread.sleep(WAIT_A_BIT);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT);
	}
	
	private static void fillRecoveryEmail(String recoveryEmail, Robot robot) throws InterruptedException {
		
		Navigation.writeText(recoveryEmail,robot);
		Thread.sleep(WAIT_A_BIT); 
		
		Navigation.nextField(robot);
		Navigation.nextField(robot);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT); 
	}
	
	private static void fillAuthorization(Robot robot) {
		Navigation.clickMouseAt(421, 485, robot);
		
		//update captcha
		Navigation.clickMouseAt(717, 321, robot);
		// click in captcha for easy typing
		Navigation.clickMouseAt(541, 432, robot);
	}
	
	public static String getNewEmail(String completePrefix) {
		
		return completePrefix + "@sapo.pt";
	}

	public static String getCompletePrefix(String prefixEmail) {
		
		Configuration config = new Configuration();
		int number = config.getInt("numberOfEmail");
		String numberWithZero = number > 9 ? number + "" : "0" + number;
		config.updateInt("numberOfEmail");
		return prefixEmail + numberWithZero;
	}
}
