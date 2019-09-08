package netflixcreator.app.domain;

import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import netflixcreator.utils.Navigation;
import netflixcreator.utils.Pair;

public class CardCreator {
	
	public static final int INICIALIZATION_TIME = 10000; // normal 11seconds
	public static final int WAIT_FOR_LOADING = 1500;
	public static final int WAIT_A_BIT = 150;
	public static final int WAIT_A_BIT_MORE = 500;
	public static final int ONE_SECOND = 1000;

	public static void openApp() {
		Navigation.executeCommand("explorer.exe shell:appsFolder\\SIBSFPS.MBWAY_fn0s81njkh37g!App");
	}

	public static CardInformation createCard(Robot robot, String name, String passMBWAY) {
		
		CardInformation res = null;
		try {
			initiateCard(robot);
			typeValue(robot);
			typeName(name, robot);
			confirm(passMBWAY, robot);
			res = getInformation(robot);
			closeApp(robot);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	private static void initiateCard(Robot robot) throws InterruptedException {
		Thread.sleep(INICIALIZATION_TIME);
		Navigation.clickMouseAt(792, 431, robot);
	}

	private static void typeValue(Robot robot) throws InterruptedException {
		Thread.sleep(WAIT_FOR_LOADING);
		Navigation.clickMouseAt(648, 529, robot);
		Navigation.writeText("15", robot);
		Navigation.nextField(robot);
	}

	private static void typeName(String name, Robot robot) throws InterruptedException {
		Thread.sleep(WAIT_A_BIT);
		Navigation.writeText(name, robot);
		Thread.sleep(WAIT_A_BIT);
	    robot.mouseWheel(2);
	    Thread.sleep(ONE_SECOND);
	}

	private static void confirm(String passMBWAY, Robot robot) throws InterruptedException {
		Navigation.clickMouseAt(919, 662, robot);
		Thread.sleep(WAIT_A_BIT_MORE);
		Navigation.writeText(passMBWAY, robot);
		Navigation.clickMouseAt(835, 444, robot);
	}

	private static CardInformation getInformation(Robot robot) throws InterruptedException, HeadlessException, UnsupportedFlavorException, IOException {
		Thread.sleep(ONE_SECOND);
		Pair<String,String> validityCode = TextRecognition.readValidityCode(robot);
		String validity = validityCode.first;
		String code = validityCode.second;
		Navigation.clickMouseAt(888, 364, robot);
		Thread.sleep(WAIT_A_BIT_MORE);
		String number = Navigation.getClipBoard();
		CardInformation card = new CardInformation(number, validity, code);
		return card;
	}
	
	private static void closeApp(Robot robot) {
		Navigation.clickMouseAt(1365, 0, robot);
	}
}
