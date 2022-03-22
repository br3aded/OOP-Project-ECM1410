package cycling;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Functions related to riders
 * 
 * 
 * @author 700027589 & 710019499
 * @version 1.0
 *
 */

public class Rider implements Serializable {

	private int teamID;
	private String riderName;
	private int yearOfBirth;

	/**
	 * returns the teamID of the riders associated team
	 */
	public int getTeamID() {
		return teamID;
	}

	/**
	 * returns the name of the rider
	 */
	public String getRiderName() {
		return riderName;
	}

	/**
	 * used to set the name of a rider
	 */
	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}

	/**
	 * returns the year of birth of the rider
	 */
	public int getyearOfBirth() {
		return yearOfBirth;
	}

	public Rider(int teamID, String riderName, int yearOfBirth) {
		this.teamID = teamID;
		this.riderName = riderName;
		this.yearOfBirth = yearOfBirth;
	}

	/**
	 *  returns all data associated with a single rider
	 */
	public String toString() {
		return "Rider[teamID = " + teamID + ", riderName=" + riderName + ", yearOfBirth=" + yearOfBirth + "]";
	}
}
