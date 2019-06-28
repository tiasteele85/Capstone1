import java.util.Scanner;


public class Capstone1 {

	public static void main(String[] args) {
		
		//Variables
		Scanner in = new Scanner(System.in);//collect input
		String userInput = "";//collect user input
		String working = ""; //variable to perform action
		char choice = 'Y';//user choice
		
		do {
			
			//Prompt user to input a word
			System.out.println("Enter word to be translated: ");
			userInput = in.next();
			
			//Calls to enter conversion process
			working = userInput;
			//Commented out for extended challenge
			//working = makeMeLowerCase(working);
			working = convertWordToPigLatin(working);
			System.out.println(working);
			
			//Prompt user for choice
			System.out.println("\nTranslate another word? (y/n)");
			choice = in.next().toUpperCase().charAt(0);
			
		}while(choice == 'Y');
		
		//inform user of end of program
		System.out.println("Goodbye!");
		//close Scanner object
		in.close();
	}

	//Method to lower entire string case
	public static String makeMeLowerCase(String words) {
		
		return words.toLowerCase();
		
	}
	
	//Method to handle Pig Latin Conversion
	public static String convertWordToPigLatin(String word) {
		
		String newWord = "";//variable to return the new word to main
		int start = 0;//locate the beginning of the word
		int end = word.length();//locate the end of the word
		
		if(testVowels(word.charAt(0)) ) 
		{
			//convert words starting with a vowel
			 newWord = startsWithVowel(word);
			
		}else {
			//convert words not starting with a vowel
			newWord = startsWithConstant(word, start, end);
		}
		
		//return converted word
		return newWord;
	}
	
	//Method to convert words that begin with vowels
	public static String startsWithVowel(String word) {
		
		//new variable to hold the word as it converts
		StringBuffer converted = new StringBuffer();
		int pos = word.length();
		char check = ' '; //check case of letter
		int punct = -1; //check for punctuation
		char punctuation = ' '; //store punctuation 
		
		//Checks for punctuation and stores value
		if(hasPunctuation(word.charAt(word.length()-1)))
		{
			punct = word.length()-1;
			//add the existing word to the buffer object
			converted.append(word.substring(0, punct));
			punctuation = word.charAt(punct);
		}else {
			//add the existing word to the buffer object
			converted.append(word);
		}
		
		//add way to the buffer objects per the rules
		check = word.charAt(pos - 2);
		if(testMyUpCase(check)) {
			
			//add correct casing
			converted.append("WAY");
		}else {
			
			//add correct casing
			converted.append("way");
		}
		
		if(punct > 0)
		{
			//add punctuation back
			converted.append(punctuation);
		}
		//return the newly created word
		return converted.toString();
	}
	
	
	//Method to convert constants to pig latin
	public static String startsWithConstant(String word, int start, int end) {
		
		//new variable to hold the word as it converts
		StringBuffer converted = new StringBuffer();
		StringBuffer beginning = new StringBuffer();
		int count = 0; //count the number of times a vowel is found
		int pos = word.length();//collect position
		char check;//check letter casing		
		int punct = -1; //check for punctuation
		char punctuation = ' '; //store punctuation 
		
		//test characters for position of start point	
		for(int i = 0; i < end; i++)
		{
			if(testVowels(word.charAt(i))) {
				//find first vowel
				count++;
				if(count == 1) {
					start = i;
				}
			}			
							
		}
		
		//Checks for punctuation and stores the value
		if(hasPunctuation(word.charAt(word.length()-1)))
		{
			punct = word.length()-1;						
			punctuation = word.charAt(punct);
		}
		
		if(testMyUpCase(word.charAt(0)) & !testMyUpCase(word.charAt(1)))
		{
			
			//test case for mixed cases
			//takes first letter and lowers it to be put in back
			beginning.append(word.substring(0, 1).toLowerCase());
			//collects rest of letters before first vowel
			beginning.append(word.substring(1, start));	
			
			//start creating string
			if(punct >= 0) {
				//capitalize first letter
				converted.append(word.substring(start, start + 1).toUpperCase());
				//collects rest of letters before punctuation
				converted.append(word.substring(start + 1, punct));
				//collects beginning letters
				converted.append(beginning);
			}else {
				
				//start creating string
				converted.append(word.substring(start, start + 1).toUpperCase());
				//collects rest of letter
				converted.append(word.substring(start + 1));
				//collects beginning letters
				converted.append(beginning);
			}
			
		}else {
			
			if(punct >= 0) {
			//add word for unmixed casing removing punctuation 	
				beginning.append(word.substring(0, start));
				//add substring until punctuation
				converted.append(word.substring(start, punct)); 
				//add beginning back to word
				converted.append(beginning);
			}else {
				//add word for unmixed casing	
				beginning.append(word.substring(0, start));
				//collects rest of letters
				converted.append(word.substring(start));
				//collects beginning letters
				converted.append(beginning);
			}
		}
		
		
		//check last char for added char casing
		check = word.charAt(pos - 2);
		if(testMyUpCase(check)) {
			converted.append("AY");
		}else {
			converted.append("ay");
		}
		
		//add punctuation back before returning
		if(punct >= 0)
		{
			converted.append(punctuation);
		}
		//return string
		return converted.toString();
	}
	
	//Method to test for vowels
	public static boolean testVowels(char letter) {
		
		if(letter == 'a' || letter == 'e' ||
				letter == 'i' || letter == 'o' || 
						letter == 'u'|| letter == 'A' || letter == 'E' ||
				letter == 'I' || letter == 'O' || 
						letter == 'U') {
			return true;
		}else {
			return false;
		}
		
	}
	
	//Method to test letter casing
	public static boolean testMyUpCase(char letter) {
		
		String temp = "";
		
		temp += letter; 
		
		if(temp.equals(temp.toUpperCase()))
		{
			return true;
		}else {
			return false;
		}
		
	}
	
	//Method to check for punctuation
	public static boolean hasPunctuation(char sym) {
		switch(sym) {
		case '.': 
		case '!':
		case '?':
		case ',':
			return true;
		default:
			return false;
		}
	}
}
