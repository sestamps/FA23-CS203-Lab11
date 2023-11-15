import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.Scanner;

public class Encrypter {

	private int shift;
	private String encrypted;

	/**
	 * Default Constructor
	 */
	public Encrypter() {
		this.shift = 1;
		this.encrypted = "";
	}

	/**
	 * Non-default Constructor
	 * 
	 * @param s - custom shift amount
	 */
	public Encrypter(int s) {
		this.shift = s;
		this.encrypted = "";
	}

	private char shiftedLetter(char letter) {
		int val = (int) letter;

		if (Character.isUpperCase(letter)) { // uppercase
		char shifted = (char) (letter + shift);
		if (shifted < 'A') {
		return (char) ('Z' - ('A' - shifted) + 1);
		} else if (shifted > 'Z') {
		return (char) ('A' + (shifted - 'Z') - 1);
		} else {
		return shifted;
		}
		} else if (Character.isLowerCase(letter)) { // lowercase
		char shifted = (char) (letter + shift);
		if (shifted < 'a') {
		return (char) ('z' - ('a' - shifted) + 1);
		} else if (shifted > 'z') {
		return (char) ('a' + (shifted - 'z') - 1);
		} else {
		return shifted;
		}
		} else { // not a letter
		return letter;
		}
	}
	
	
	
	/**
	 * Encrypts the content of a file and writes the result to another file.
	 *
	 * @param inputFilePath      the path to the file containing the text to be encrypted
	 * @param encryptedFilePath the path to the file where the encrypted text will be written
	 * @throws Exception if an error occurs while reading or writing the files
	 */
	public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
		//TODO: Call the read method, encrypt the file contents, and then write to new file
		try(Scanner fileScanner = new Scanner(Paths.get(inputFilePath))){
			try (PrintWriter output = new PrintWriter(encryptedFilePath)) {
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();   
				char[] ch = line.toCharArray();
				for(int i=0; i<line.length();i++){
					ch[i] = shiftedLetter(ch[i]);
				}
				output.println(ch);
			}
			output.close();
			fileScanner.close();
		}catch(Exception e){
			System.out.println("ERROR: "+e.toString());
		}
		}
	}

	/**
	 * Decrypts the content of an encrypted file and writes the result to another
	 * file.
	 *
	 * @param messageFilePath   the path to the file containing the encrypted text
	 * @param decryptedFilePath the path to the file where the decrypted text will
	 *                          be written
	 * @throws Exception if an error occurs while reading or writing the files
	 */
	public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
			shift = -shift;
			encrypt(messageFilePath, decryptedFilePath);
			
	}

	/**
	 * Reads the content of a file and returns it as a string.
	 *
	 * @param filePath the path to the file to be read
	 * @return the content of the file as a string
	 * @throws Exception if an error occurs while reading the file
	 */
	private static String readFile(String filePath) throws Exception {
		String message = "";
		try (Scanner fileScanner = new Scanner(Paths.get(filePath))) {
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				while (line != null) {
					message = message + line;
				}
			}
		}
		return message;
	}

	/**
	 * Writes data to a file.
	 *
	 * @param data     the data to be written to the file
	 * @param filePath the path to the file where the data will be written
	 */
	private static void writeFile(String data, String filePath) throws Exception {
		// TODO: Write to filePath
		try (PrintWriter output = new PrintWriter("output.txt")) {
			output.println(data);
			output.close();
		}catch(Exception e) {
			System.out.println("ERROR: " + e);
		}
	}

	/**
	 * Returns a string representation of the encrypted text.
	 *
	 * @return the encrypted text
	 */
	@Override
	public String toString() {
		return encrypted;
	}
}
