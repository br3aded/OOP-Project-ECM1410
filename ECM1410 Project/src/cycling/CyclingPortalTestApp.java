package cycling;

import java.util.Arrays;

public class CyclingPortalTestApp {
	public static void main (String[] args) throws IllegalNameException, InvalidNameException, IllegalArgumentException, IDNotRecognisedException {
		CyclingPortal cyclingportal = new CyclingPortal();
		cyclingportal.createTeam("team1", "just a team");
		cyclingportal.createTeam("team1", "just a team");
		cyclingportal.createTeam("team2", "just a team part 2");
		cyclingportal.createRider(0, "Aob", 2002);
		cyclingportal.createRider(0, "Bob", 2002);
		cyclingportal.createRider(0, "Cob", 2002);
		cyclingportal.createRider(0, "Dob", 2002);
		cyclingportal.createRider(0, "Dob", 2002);
		System.out.println(cyclingportal.teamList);
		System.out.println(Arrays.toString(cyclingportal.getTeams()));
		System.out.println(Arrays.toString(cyclingportal.getTeamRiders(0)));
		
	}
	
}
