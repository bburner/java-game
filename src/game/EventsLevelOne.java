/**
 * Benjamin Burner and Nick Simmons
 * 6/15/17
 * EventsLevelOne.java
 * This class contains methods used for the first level of the game. It inherits methods from Events.java 
 */

package game;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import character.CharacterAttributes;
import main.PopAttr;
import generator.GeneratorMain;

/**
 * This class holds methods that are used in the first level of the game.
 * 
 * @author Benjamin Burner
 * @author Nick Simmons
 * @version 1.0
 *
 */
public class EventsLevelOne extends Events {

	// fields
	private static Scanner console = new Scanner(System.in);
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

	public EventsLevelOne() {
		// nothing here
	}

	/**
	 * This method generates a test as a user event. It prints out questions and
	 * saves a score based on the character's attributes and choices.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void generateTest() throws FileNotFoundException {
		GeneratorMain openingSentence = new GeneratorMain("test.txt", "<s>");
		System.out.println();
		System.out.println("What do you want to do?");
		System.out.println("1. Stay home and study. \n2. Meet friends and try to get help.");

		int intelligenceFactor = random.nextInt(IMPORTANT_MAX - IMPORTANT_MIN + 1) + IMPORTANT_MIN;
		int personalityFactor = random.nextInt(UNIMPORTANT_MAX - UNIMPORTANT_MIN + 1) + UNIMPORTANT_MIN;
		int techExperienceFactor = random.nextInt(AVERAGE_MAX - AVERAGE_MIN + 1) + AVERAGE_MIN;

		double basePoints = generateBasePoints(intelligenceFactor, personalityFactor, techExperienceFactor);

		double assignmentPoints = generateAssignmentPoints(basePoints, player.getIntelligence(),
				player.getPersonality());
		System.out.println(generateAssignmentGrade(assignmentPoints));

		System.out.println();
	}

	/**
	 * This is an event that is randomly called by the main game program. It
	 * generates a group project object. Each time the method is called. the
	 * weights for scoring are randomly adjusted within a range.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void generateGroupProject() throws FileNotFoundException {
		GeneratorMain openingSentence = new GeneratorMain("group.txt", "<s>");
		System.out.println();
		System.out.println("What do you want to do?");
		System.out.println(
				"1. I'm going to work great with others! \n2. My classmates are dumb. I'm going to finish it all myself.");

		int personalityFactor = random.nextInt(IMPORTANT_MAX - IMPORTANT_MIN + 1) + IMPORTANT_MIN;
		int techExperienceFactor = random.nextInt(UNIMPORTANT_MAX - UNIMPORTANT_MIN + 1) + UNIMPORTANT_MIN;
		int intelligenceFactor = random.nextInt(AVERAGE_MAX - AVERAGE_MIN + 1) + AVERAGE_MIN;

		double basePoints = generateBasePoints(intelligenceFactor, personalityFactor, techExperienceFactor);

		double assignmentPoints = generateAssignmentPoints(basePoints, player.getPersonality(),
				player.getIntelligence());

		System.out.println(generateAssignmentGrade(assignmentPoints));

		System.out.println();
	}

	/**
	 * This is an event that is randomly called by the main game program. It
	 * generates a tech problem object. Each time the method is called. the
	 * weights for scoring are randomly adjusted within a range.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void generateTechProblem() throws FileNotFoundException {
		GeneratorMain openingSentence = new GeneratorMain("problem.txt", "<s>");
		System.out.println();
		System.out.println("What do you want to do?");
		System.out.println("1. Fix it myself! \n2. Take it to a store and let someone else deal with it.");

		int personalityFactor = random.nextInt(UNIMPORTANT_MAX - UNIMPORTANT_MIN + 1) + UNIMPORTANT_MIN;
		int techExperienceFactor = random.nextInt(IMPORTANT_MAX - IMPORTANT_MIN + 1) + IMPORTANT_MIN;
		int intelligenceFactor = random.nextInt(UNIMPORTANT_MAX - UNIMPORTANT_MIN + 1) + UNIMPORTANT_MIN;

		double basePoints = generateBasePoints(intelligenceFactor, personalityFactor, techExperienceFactor);

		double grade = generateAssignmentPoints(basePoints, player.getTechExperience(), player.getIntelligence());

		if (isSuccessful(grade)) {
			System.out.println("The problem is all fixed!");
		} else {
			System.out.println(
					"Since you weren't able to fix the problem, you wasted a \nbunch of time, and took it to a repair shop.");
			player.decreaseTime(LOW_FATIGUE);
		}
		System.out.println();
	}

	/**
	 * This is an event that is randomly called by the main game program. It
	 * generates a homework object. Each time the method is called. the
	 * weights for scoring are randomly adjusted within a range.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void generateHomework() throws FileNotFoundException {
		GeneratorMain openingSentence = new GeneratorMain("test.txt", "<s>");
		System.out.println();
		System.out.println("What do you want to do?");
		System.out.println("1. Work it out by yourself. \n2. Cheat and copy from the internet.");

		int intelligenceFactor = random.nextInt(IMPORTANT_MAX - IMPORTANT_MIN + 1) + IMPORTANT_MIN;
		int techExperienceFactor = random.nextInt(AVERAGE_MAX - AVERAGE_MIN + 1) + AVERAGE_MIN;
		int personalityFactor = random.nextInt(AVERAGE_MAX - AVERAGE_MIN + 1) + AVERAGE_MIN;

		double basePoints = generateBasePoints(intelligenceFactor, personalityFactor, techExperienceFactor);

		double assignmentPoints = generateAssignmentPoints(basePoints, player.getIntelligence(),
				player.getTechExperience());
		System.out.println(generateAssignmentGrade(assignmentPoints));

		System.out.println();
	}
}