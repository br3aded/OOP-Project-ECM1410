package cycling;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Functions related to riders
 * 
 * 
 * @author 700027589 and 710019499
 * @version 1.0
 *
 */

public class Rider implements Serializable {

	private int teamID;
	private String riderName;
	private int yearOfBirth;

	/**
	 * returns the teamID of the riders associated team
	 * @return the teamID
	 */
	public int getTeamID() {
		return teamID;
	}

	/**
	 * returns the name of the rider
	 * @return the riders name
	 */
	public String getRiderName() {
		return riderName;
	}

	/**
	 * used to set the name of a rider
	 * @param riderName:The name of the rider
	 */
	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}

	/**
	 * returns the year of birth of the rider
	 * @return the yearOfBirth
	 */
	public int getyearOfBirth() {
		return yearOfBirth;
	}

	/**
	 *  Constructor for the sprint segment
	 *  @param teamID: The ID of the team the rider is associated with
	 *  @param riderName: The name of the rider
	 *  @param yearOfBirth: The year of the riders birth
	 */
	public Rider(int teamID, String riderName, int yearOfBirth) {
		this.teamID = teamID;
		this.riderName = riderName;
		this.yearOfBirth = yearOfBirth;
	}

	/**
	 *  returns all data associated with a single rider
	 *  @return A string containing the teamIS,riderName, and yearOfBirth
	 */
	public String toString() {
		return "Rider[teamID = " + teamID + ", riderName=" + riderName + ", yearOfBirth=" + yearOfBirth + "]";
	}
}
