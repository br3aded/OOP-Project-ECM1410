package cycling;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Team {
	private ArrayList<Integer> riderIdList;
	private String teamName;
	
	public String getTeamName(){return teamName;}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	private String descriptor;
	
	public String getDescriptor(){return descriptor;}
	
	private int teamId = 0;
	
	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}
	
	public Team(String teamName, String descriptor) {
		this.teamName =  teamName;
		this.descriptor = descriptor;
		this.riderIdList = new ArrayList<Integer>();
	}
	
	public String toString() {
		return "Team[teamName = "+teamName+ ", Descriptor="+ descriptor + ", RidersIds =" + riderIdList+"]";
	}
	
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
	
	public ArrayList<Integer> getRiderList() {return riderIdList;}
}
