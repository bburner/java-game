/*
 * Benjamin Burner and Nick Simmons
 * 6/15/17
 * Events.java
 * This abstract class contains the shared methods used by EventsLevelOne and EventsLevelTwo. 
 */

package game;

import java.util.Random;
import java.util.Scanner;

import character.CharacterAttributes;
import main.PopAttr;

/**
 * This class is abstract and contains methods for generating user events.
 * 
 * @author Benjamin Burner
 * @version 1.0
 */
public abstract class Events {
	private static Scanner console = new Scanner(System.in);
	private static CharacterAttributes player = PopAttr.getPlayer();

	private static double ASSIGNMENT_POINTS;
	private static double TOTAL_POINTS;

	private static Random random = new Random();

	// these numbers are used to randomly generate a weight for important
	// character traits, average traits and unimportant traits. Used to generate

	// number randomly generates a weight for character traits based on a user's
	// choice
	private static int TRAIT_MAX = 10;
	private static int TRAIT_MIN = 8;

	// the amount of time used in each event
	private static int LOW_FATIGUE = 1;
	private static int HIGH_FATIGUE = 2;

	/**
	 * This method calculates the total points earned on an event based on the
	 * event type, the user's base traits, and the user's selection.
	 * 
	 * @param basePoints
	 *            A number calculated from the character's traits.
	 * @param firstTrait
	 *            The trait used if the user selects the first option.
	 * @param secondTrait
	 *            The trait used if the user selects the second option.
	 * @return ASSIGNMENT_POINTS A double value that stores the points for this
	 *         event.
	 */
	public static double generateAssignmentPoints(double basePoints, double firstTrait, double secondTrait) {
		int choice = console.nextInt();

		int traitImportance = random.nextInt(TRAIT_MAX - TRAIT_MIN + 1) + TRAIT_MIN;

		if (choice == 1) {
			ASSIGNMENT_POINTS = basePoints + firstTrait * traitImportance;
			TOTAL_POINTS += ASSIGNMENT_POINTS;
			player.decreaseTime(LOW_FATIGUE);
			player.decreaseFatigue(LOW_FATIGUE);
			return ASSIGNMENT_POINTS;
		} else if (choice == 2) {
			ASSIGNMENT_POINTS = basePoints + secondTrait * traitImportance;
			TOTAL_POINTS += ASSIGNMENT_POINTS;
			player.decreaseTime(HIGH_FATIGUE);
			player.decreaseFatigue(HIGH_FATIGUE);
			return ASSIGNMENT_POINTS;
		} else {
			System.out.println("invalid input");
			return ASSIGNMENT_POINTS;
		}
	}

	/**
	 * This method allows the user to rest any number of days to recharge
	 * stamina. Each day rested uses 1 time and generates 2 stamina.
	 */
	public static void refreshStamina() {
		System.out.println("You're stamina is getting low. Would you like to rest? "
				+ "\nEach day you rest adds two stamina points.");
		System.out.println("1. Take a break");
		System.out.println("2. You can rest when you're dead");

		int choice = console.nextInt();

		if (choice == 1) {
			System.out.println("How many days would you like to rest for?");
			int days = console.nextInt();
			player.decreaseTime(days);
			player.increaseFatigue(days * 2);
		} else {
			System.out.println("Full speed ahead!");
		}
	}

	/**
	 * This method returns a letter grade for a given numerical score on an
	 * assignment.
	 * 
	 * @param points
	 *            A double representing the number of points earned on an
	 *            assignment.
	 * @return A String with the letter grade earned.
	 */
	protected static String generateAssignmentGrade(double points) {
		if (points < 70) {
			return "You failed";
		} else if (points < 80) {
			return "You earned a C";
		} else if (points < 90) {
			return "You earned a B";
		} else {
			return "you earned an A";
		}
	}

	/**
	 * This method returns a boolean for the events where a letter grade is not
	 * appropreate.
	 * 
	 * @param points
	 *            The points earned on a non-academic task.
	 * @return boolean true if the character passed the task or false if the
	 *         character failed.
	 */
	public static boolean isSuccessful(double points) {
		if (points < 70) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * This method generates part of a character's points based on the
	 * character's traits. It is not affected by user input but does change
	 * depending on the task or event.
	 * 
	 * @param intelligenceWeight A double representing the importance of intelligence for a particular event.
	 * @param personalityWeight A double representing the importance of personality for a particular event.
	 * @param techExperienceWeight A double representing the importance of tech experience for a particular event.
	 * @return basePoints A double representing the points earned based only on character traits. 
	 */
	protected static double generateBasePoints(double intelligenceWeight, double personalityWeight,
			double techExperienceWeight) {
		return player.getIntelligence() * intelligenceWeight + player.getPersonality() * personalityWeight
				+ player.getTechExperience() * techExperienceWeight;
	}

	/**
	 * This is a getter that returns the total points earned by a player throughout the game. 
	 * @return TOTAL_POINTS a double that represents the total points earned. 
	 */
	public static double getPoints() {
		return TOTAL_POINTS;
	}
}
