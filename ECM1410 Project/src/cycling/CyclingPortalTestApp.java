package cycling;

import java.util.Arrays;

public class CyclingPortalTestApp {
	public static void main (String[] args) throws IllegalNameException, InvalidNameException, IllegalArgumentException, IDNotRecognisedException {
		CyclingPortal cyclingportal = new CyclingPortal();
		cyclingportal.createTeam("team1", "just a team");
		cyclingportal.createTeam(null, "just a team");
		cyclingportal.createTeam("team 2", "just a team part 2 electric boogaloo");
		cyclingportal.createRider(0, "Aob", 2002);
		cyclingportal.createRider(0, "Bob", 2002);
		cyclingportal.createRider(1, "Cob", 2002);
		cyclingportal.createRider(0, "Dob", 2002);
		System.out.println(cyclingportal.riderList);
		System.out.println("number of teams" + Arrays.toString(cyclingportal.getTeams()));
		System.out.println("riderId in team 0" + Arrays.toString(cyclingportal.getTeamRiders(0)));
		System.out.println("riderId in team 1" + Arrays.toString(cyclingportal.getTeamRiders(1)));
		System.out.println(cyclingportal.teamList.get(0).getTeamName());
		
	}
	
}
