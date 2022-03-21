package cycling;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  Functions related to riders
 * 
 * 
 * @author 700027589 & 710019499
 * @version 1.0
 *
 */

public class Rider implements Serializable{

		private int teamID;
		private String riderName;
		private int yearOfBirth;
		
		public int getTeamID(){return teamID;}

		public String getRiderName(){return riderName;}

		public void setRiderName(String riderName) {
			this.riderName = riderName;
		}
		
		public int getyearOfBirth(){return yearOfBirth;}
		
		public Rider(int teamID,String riderName,int yearOfBirth ) {
			this.teamID = teamID;
			this.riderName =  riderName;
			this.yearOfBirth = yearOfBirth;
		}
		
		public String toString() {
			return "Rider[teamID = "+teamID+ ", riderName="+ riderName+", yearOfBirth="+yearOfBirth+"]";
		}
	}

