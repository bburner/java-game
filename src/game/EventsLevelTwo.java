/**
 * Benjamin Burner and Nick Simmons
 * 6/15/17
 * EventsLevelTwo.java
 * This class has the event methods for the second level of the game. 
 */

package game;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import character.CharacterAttributes;
import generator.GeneratorMain;
import main.PopAttr;

/**
 * This class holds methods that are used in the second level of the game.
 * 
 * @author Benjamin Burner
 * @author Nick Simmons
 * @version 1.0
 *
 */
public class EventsLevelTwo extends Events {
	
	// fields
	static Scanner console = new Scanner(System.in);
	private static CharacterAttributes player = PopAttr.getPlayer();

	private static Random random = new Random();

	// these numbers are used to randomly generate a weight for important
	// character traits, average traits and unimportant traits. Used to generate
	// BASE POINTS
	private static int IMPORTANT_MAX = 6;
	private static int IMPORTANT_MIN = 5;
	private static int AVERAGE_MAX = 4;
	private static int AVERAGE_MIN = 3;
	private static int UNIMPORTANT_MAX = 2;
	private static int UNIMPORTANT_MIN = 1;
	
	private static int LOW_FATIGUE = 1;

	/**
	 * This method generates a personal problem as a user event. It prints out questions and
	 * saves a score based on the character's attributes and choices.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void generatePersonalProblem() throws FileNotFoundException {
		GeneratorMain openingSentence = new GeneratorMain("personalproblem.txt", "<s>");
		System.out.println();
		System.out.println("What do you want to do?");
		System.out.println("1. Complete another assignment to show you can do the work.");
		System.out.println("2. Take her out to coffee and convince her you're a good person.");

		int personalityFactor = random.nextInt(IMPORTANT_MAX - IMPORTANT_MIN + 1) + IMPORTANT_MIN;
		int techExperienceFactor = random.nextInt(UNIMPORTANT_MAX - UNIMPORTANT_MIN + 1) + UNIMPORTANT_MIN;
		int intelligenceFactor = random.nextInt(AVERAGE_MAX - AVERAGE_MIN + 1) + AVERAGE_MIN;

		double basePoints = generateBasePoints(intelligenceFactor, personalityFactor, techExperienceFactor);

		double assignmentPoints = generateAssignmentPoints(basePoints, player.getIntelligence(), player.getPersonality());

		if (isSuccessful(assignmentPoints)) {
			System.out.println("You convinced her!");
		}
		else {
			System.out.println("That didn't work. People are starting to think you're really shady");
			player.decreaseTime(LOW_FATIGUE);
		}
		System.out.println();
	}
	
	/**
	 * This method generates an interview as a user event. It prints out questions and
	 * saves a score based on the character's attributes and choices.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void generateInterview() throws FileNotFoundException {
		GeneratorMain openingSentence = new GeneratorMain("interview.txt", "<s>");
		System.out.println();
		System.out.println("What do you want to do?");
		System.out.println("1. Hang out with your friends that work for the company.");
		System.out.println("2. Study and prepare for the interview.");

		int personalityFactor = random.nextInt(IMPORTANT_MAX - IMPORTANT_MIN + 1) + IMPORTANT_MIN;
		int techExperienceFactor = random.nextInt(UNIMPORTANT_MAX - UNIMPORTANT_MIN + 1) + UNIMPORTANT_MIN;
		int intelligenceFactor = random.nextInt(AVERAGE_MAX - AVERAGE_MIN + 1) + AVERAGE_MIN;

		double basePoints = generateBasePoints(intelligenceFactor, personalityFactor, techExperienceFactor);

		double assignmentPoints = generateAssignmentPoints(basePoints, player.getPersonality(), player.getIntelligence());

		if (isSuccessful(assignmentPoints)) {
			System.out.println("You impressed HR!");
		}
		else {
			System.out.println("They never called you back.");
			player.decreaseTime(LOW_FATIGUE);
		}
		System.out.println();
	}
	
	/**
	 * This method generates a internship problem as a user event. It prints out questions and
	 * saves a score based on the character's attributes and choices.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void generateInternshipProblem() throws FileNotFoundException {
		GeneratorMain openingSentence = new GeneratorMain("internshipproblem.txt", "<s>");
		System.out.println();
		System.out.println("What do you want to do?");
		System.out.println("1. Fix it.");
		System.out.println("2. Plead with your trainer for help.");

		int personalityFactor = random.nextInt(AVERAGE_MAX - AVERAGE_MIN + 1) + AVERAGE_MIN;
		int techExperienceFactor = random.nextInt(IMPORTANT_MAX - IMPORTANT_MIN + 1) + IMPORTANT_MIN;
		int intelligenceFactor = random.nextInt(UNIMPORTANT_MAX - UNIMPORTANT_MIN + 1) + UNIMPORTANT_MIN;

		double basePoints = generateBasePoints(intelligenceFactor, personalityFactor, techExperienceFactor);

		double assignmentPoints = generateAssignmentPoints(basePoints, player.getTechExperience(), player.getPersonality());

		if (isSuccessful(assignmentPoints)) {
			System.out.println("You fixed it!");
		}
		else {
			System.out.println("It took a long time to figure out, and you are really tired!");
			player.decreaseTime(LOW_FATIGUE);
		}
		System.out.println();
	}
}
