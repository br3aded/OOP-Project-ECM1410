package cycling;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *  Functions related to teams
 * 
 * 
 * @author 700027589 and 710019499
 * @version 1.0
 *
 */

public class Team implements Serializable{
	private ArrayList<Integer> riderIdList;
	private String teamName;
	
	/**
	 *  returns the team name for a single team
	 *  @return the team name
	 */
	public String getTeamName(){return teamName;}
	
	/**
	 *  The description variable
	 */
	private String descriptor;
	
	
	/**
	 *  returns the description of a single team
	 *  @return The descriptor of the team
	 */
	public String getDescriptor(){return descriptor;}
	
	/**
	 *  The constructor the Teams
	 *  @param teamName: The name of the team
	 *  @param descriptor: The description of the team
	 * riderIdList: A list of riderIDs associated with the team
	 */
	public Team(String teamName, String descriptor) {
		this.teamName =  teamName;
		this.descriptor = descriptor;
		this.riderIdList = new ArrayList<Integer>();
	}
	
	/**
	 *  returns all data associated with a single team
	 *  @return A string of the containing the team name,descriptor and riderIdList
	 */
	public String toString() {
		return "Team[teamName = "+teamName+ ", Descriptor="+ descriptor + ", RidersIds =" + riderIdList+"]";
	}
	
	/**
	 *  adds a rider to a single team
	 *  @param riderId: The ID of the rider to be added
	 */
	public void addRider(int riderId) {
		riderIdList.add(riderId);
	}
	
	/**
	 *  Removes a rider from the riderIdList
	 *  @param riderId: The riderId of the rider to be removed
	 */
	public void setRiderList(int riderId) {
		for(int i = 0; i< riderIdList.size(); i++) {
			if(riderIdList.get(i) == riderId) {
				riderIdList.remove(i);
				break;
			}
		}
	}
	
	/**
	 *  returns a ArrayList of rider IDs associated with the team
	 *  @return A list of riderIDs
	 */
	public ArrayList<Integer> getRiderList() {return riderIdList;}
}
