package cycling;

import java.io.Serializable;
import java.util.ArrayList;

public class Rider implements Serializable{

		private int teamID;
		private String riderName;
		private int yearOfBirth;
		
		
		public int getTeamID(){return teamID;}

		public void setTeamID(int teamID) {
			this.teamID = teamID;
		}
		
		public String getRiderName(){return riderName;}

		public void setRiderName(String riderName) {
			this.riderName = riderName;
		}
		
		public int getyearOfBirth(){return yearOfBirth;}
		
		public void setyearOfBirth(int yearOfBirth) {
			this.yearOfBirth = yearOfBirth;
		}
		
		public Rider(int teamID,String riderName,int yearOfBirth ) {
			this.teamID = teamID;
			this.riderName =  riderName;
			this.yearOfBirth = yearOfBirth;
		}
		

		public String toString() {
			return "Rider[teamID = "+teamID+ ", riderName="+ riderName+", yearOfBirth="+yearOfBirth+"]";
		}
	}

