package netflixcreator.app.domain;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import netflixcreator.utils.Pair;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TextRecognition {

	public static Pair<String, String> readValidityCode(Robot robot) {
		
		Pair<File,File> validityCodeFile = readFromScreenToImageFile(robot);
		
		return recognizeTextInImages(validityCodeFile.first, validityCodeFile.second);
	}

	private static Pair<String, String> recognizeTextInImages(File validityFile, File codeFile) {
		
		ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setDatapath("Tess4J\\tessdata"); // path to tessdata directory
        String validity = null;
        String code = null;
        try {
        	validity = instance.doOCR(validityFile);
        	code = instance.doOCR(codeFile);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
		
		return new Pair<>(validity.trim(), code.trim());
	}

	private static Pair<File, File> readFromScreenToImageFile(Robot robot) {
		
		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage capture = null;
		try {
			capture = (new Robot()).createScreenCapture(screenRect);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedImage validityBuff = capture.getSubimage(691, 409, 46, 19);
		BufferedImage codeBuff = capture.getSubimage(767, 410, 31, 17);
		
		File validityFile = new File("tmpVal.bmp");
		File codeFile = new File("tmpCod.bmp");
		try {
			ImageIO.write(validityBuff, "bmp", validityFile);
			ImageIO.write(codeBuff, "bmp", codeFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Pair<>(validityFile, codeFile);
	}
}
