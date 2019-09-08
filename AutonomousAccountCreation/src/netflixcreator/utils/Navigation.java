package netflixcreator.utils;

import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Navigation {

	public static void pressDownNTimes(int timesDown, Robot robot) {
		
	    for(int i = 0; i < timesDown; i++) {
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			
			try{ Thread.sleep(10); }catch(InterruptedException e){}
	    }
	}
	
	public static void writeText(String text, Robot robot) {
		
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
	}

	public static void nextField(Robot robot) {
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}
	
	public static void clickMouseAt(int x, int y, Robot robot) {
		
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
	}
	
	public static void goTop(Robot robot) {
		Navigation.scroll(-4, robot);
	}
	
	public static void goBottom(Robot robot) {
		Navigation.scroll(9, robot);
	}
	
	public static void scroll(int times, Robot robot) {
		int val = times > 0 ? 1 : -1;
		times = times > 0 ? times : -times;
		
        for(int i = 0; i < times; i++){
            //scroll and wait a bit to give the impression of smooth scrolling
            robot.mouseWheel(val);
            try{ Thread.sleep(400); }catch(InterruptedException e){}
        }
        try{ Thread.sleep(400); }catch(InterruptedException e){}
	}
	
	public static String getClipBoard(){
	    try {
	        return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
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
	    return "";
	}
	
	public static void executeCommand(String cmd) {
		ProcessBuilder builder = new ProcessBuilder(
	        "cmd.exe", "/c", cmd);
	    builder.redirectErrorStream(true);
	    try {
			Process p = builder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void openWebpage(String url) {
		executeCommand("start chrome /incognito /start-maximized " + url);
	}
}		