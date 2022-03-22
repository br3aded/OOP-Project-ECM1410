package cycling;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *  Functions related to teams
 * 
 * 
 * @author 700027589 & 710019499
 * @version 1.0
 *
 */

public class Team implements Serializable{
	private ArrayList<Integer> riderIdList;
	private String teamName;
	
	/**
	 *  returns the team name
	 */
	public String getTeamName(){return teamName;}
	
	private String descriptor;
	
	
	/**
	 *  returns the description of a single team
	 */
	public String getDescriptor(){return descriptor;}
	
	public Team(String teamName, String descriptor) {
		this.teamName =  teamName;
		this.descriptor = descriptor;
		this.riderIdList = new ArrayList<Integer>();
	}
	
	/**
	 *  returns all data associated with a single team
	 */
	public String toString() {
		return "Team[teamName = "+teamName+ ", Descriptor="+ descriptor + ", RidersIds =" + riderIdList+"]";
	}
	
	/**
	 *  adds a rider to a single team
	 */
	public void addRider(int riderId) {
		riderIdList.add(riderId);
	}
	
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
	 */
	public ArrayList<Integer> getRiderList() {return riderIdList;}
}
