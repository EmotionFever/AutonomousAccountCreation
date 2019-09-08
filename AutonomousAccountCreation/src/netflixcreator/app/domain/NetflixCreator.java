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
import netflixcreator.utils.Pair;

public class NetflixCreator {
	
	public static final int FIVE_SECONDS = 5000;
    public static final int WAIT_A_BIT = 150;
    public static final int ONE_SECOND = 1000;
    public static final int WAIT_A_BIT_MORE = 450;
    public static final int WAIT_BETWEEN_PAGES = 1100;

	public static Pair<String, String> fillRegistration(CardInformation cardInfo, String email, Robot robot) {
		
		Pair<String, String> logInInfo = null;
		
		try {
			Thread.sleep(FIVE_SECONDS);
			
			acceptCookies(robot);
			startRegistration(robot);
			choosePlan(robot);
			continueRegistration(robot);
			logInInfo = fillLogInformation(email, robot);
			confirmEmail(robot);
			selectPaymentMethod(robot);
			fillCardDetails(cardInfo, robot);
			fillRecoverMethod(robot);
			selectDevicestoWatch(robot);
			addProfiles(robot);
			selectKidsAccounts(robot);
			selectFavContent(robot);
			//cancel Netflix account
			selectAnAccount(robot);
			goToAccountInfo(robot);
			pressCancelAccountButton(robot);
			confirmCancel(robot);
			selectReasonForCancel(robot);
			goBackToHome(robot);
			
			Thread.sleep(WAIT_A_BIT);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
		return logInInfo;
	}	

	private static void acceptCookies(Robot robot) throws InterruptedException {
		Navigation.clickMouseAt(1308, 700, robot);
		Thread.sleep(WAIT_A_BIT);
	}

	private static void startRegistration(Robot robot) throws InterruptedException {
		Navigation.clickMouseAt(680, 722, robot);
		Thread.sleep(WAIT_BETWEEN_PAGES); //
	}

	private static void choosePlan(Robot robot) throws InterruptedException {
		Navigation.goBottom(robot);
		Navigation.clickMouseAt(673, 319, robot); //
		Thread.sleep(WAIT_BETWEEN_PAGES);
	}
	
	private static void continueRegistration(Robot robot) throws InterruptedException {
		Navigation.clickMouseAt(675, 647, robot);
		Thread.sleep(WAIT_BETWEEN_PAGES);
	}
	
	private static Pair<String, String> fillLogInformation(String email, Robot robot) throws InterruptedException {
		
		String password = "123456";
		Navigation.goTop(robot);
		Navigation.clickMouseAt(656, 485, robot);
		Thread.sleep(WAIT_A_BIT);
		Navigation.writeText(email, robot);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT);
		Navigation.writeText(password, robot);
		Thread.sleep(WAIT_A_BIT_MORE);
		Navigation.clickMouseAt(468, 611, robot);
		Navigation.clickMouseAt(673, 690, robot);
		Thread.sleep(ONE_SECOND * 2 + WAIT_BETWEEN_PAGES);
		return new Pair<>(email, password);
	}
	
	private static void confirmEmail(Robot robot) throws InterruptedException {
		Navigation.goTop(robot);
			Navigation.clickMouseAt(683, 520, robot);
		Thread.sleep(WAIT_BETWEEN_PAGES);
	}
	
	private static void selectPaymentMethod(Robot robot) throws InterruptedException {
		Navigation.clickMouseAt(672, 688, robot);
		Thread.sleep(WAIT_BETWEEN_PAGES);
	}
	
	private static void fillCardDetails(CardInformation cardInfo, Robot robot) throws InterruptedException {
		Navigation.goTop(robot);
		Navigation.clickMouseAt(654, 423, robot);
		Navigation.writeText("Felipe", robot);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT);
		Navigation.writeText("Teixeira", robot);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT);
		Navigation.writeText(cardInfo.getNumber(), robot);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT_MORE);
		Navigation.writeText(cardInfo.getValidity(), robot);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT_MORE);
		Navigation.writeText(cardInfo.getCode(), robot);
		Navigation.goBottom(robot);
		Navigation.clickMouseAt(671, 313, robot);
		Thread.sleep(FIVE_SECONDS);
	}
	
	private static void fillRecoverMethod(Robot robot) throws InterruptedException {
		Navigation.goTop(robot);
		Navigation.clickMouseAt(662, 690, robot);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(WAIT_BETWEEN_PAGES);
	}
	
	private static void selectDevicestoWatch(Robot robot) throws InterruptedException {
		Navigation.goTop(robot);
		Navigation.clickMouseAt(661, 440, robot);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(WAIT_BETWEEN_PAGES);
	}

	private static void addProfiles(Robot robot) throws InterruptedException {
		Navigation.goTop(robot);
		// select text so that it disappear when typing
		Navigation.clickMouseAt(670, 361, robot);
		Navigation.clickMouseAt(670, 361, robot);
		Navigation.writeText("Pedro", robot);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT);
		Navigation.writeText("Primas", robot);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(WAIT_BETWEEN_PAGES);
	}

	private static void selectKidsAccounts(Robot robot) throws InterruptedException {
		Navigation.clickMouseAt(670, 361, robot);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(WAIT_BETWEEN_PAGES);
	}

	private static void selectFavContent(Robot robot) throws InterruptedException {
		Navigation.clickMouseAt(681, 330, robot);
		Thread.sleep(WAIT_BETWEEN_PAGES);
		Thread.sleep(FIVE_SECONDS);
	}

	private static void selectAnAccount(Robot robot) throws InterruptedException {
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	private static void goToAccountInfo(Robot robot) throws InterruptedException {
		Navigation.scroll(4, robot);
		Thread.sleep(WAIT_A_BIT_MORE);
		Navigation.clickMouseAt(1261, 139, robot);
		Thread.sleep(WAIT_A_BIT);
		Navigation.clickMouseAt(1156, 343, robot);
		Thread.sleep(WAIT_A_BIT);
		Navigation.clickMouseAt(1156, 495, robot);
		Thread.sleep(WAIT_BETWEEN_PAGES + WAIT_A_BIT_MORE * 2);
	}
	
	private static void pressCancelAccountButton(Robot robot) throws InterruptedException {
		Navigation.goTop(robot);
		Navigation.clickMouseAt(253, 606, robot);
		Thread.sleep(WAIT_BETWEEN_PAGES + WAIT_A_BIT_MORE);
	}
	
	private static void confirmCancel(Robot robot) throws InterruptedException {
		Navigation.goBottom(robot);
		Thread.sleep(WAIT_A_BIT_MORE);
		Navigation.clickMouseAt(260, 257, robot);
		Thread.sleep(WAIT_BETWEEN_PAGES);
	}
	
	private static void selectReasonForCancel(Robot robot) throws InterruptedException {
		Navigation.goBottom(robot);
		Thread.sleep(WAIT_A_BIT_MORE);
		Navigation.clickMouseAt(204, 310, robot);
		Thread.sleep(WAIT_BETWEEN_PAGES);
	}

	private static void goBackToHome(Robot robot) throws InterruptedException {
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT);
		Navigation.nextField(robot);
		Thread.sleep(WAIT_A_BIT);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(WAIT_BETWEEN_PAGES);
	}
}
