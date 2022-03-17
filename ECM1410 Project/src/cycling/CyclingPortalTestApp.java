package cycling;

import java.time.LocalDateTime;
import java.util.Arrays;

public class CyclingPortalTestApp {
	public static void main (String[] args) throws IllegalNameException, InvalidNameException, IllegalArgumentException, IDNotRecognisedException, InvalidLengthException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		CyclingPortal cyclingportal = new CyclingPortal();
		cyclingportal.createRace("race", "the small tour");
		
		cyclingportal.createTeam("team 1","just team 1");
		cyclingportal.createTeam("team 2", "just team 2");
		
		cyclingportal.createRider(0,"aob", 1900);
		cyclingportal.createRider(1,"bob", 2100);
		cyclingportal.createRider(0,"cob", 2002);
		cyclingportal.createRider(1,"dob", 2022);
		
		System.out.println(Arrays.toString(cyclingportal.getTeamRiders(0)));
		System.out.println(Arrays.toString(cyclingportal.getTeamRiders(1)));
		
		cyclingportal.removeRider(0);
		System.out.println(Arrays.toString(cyclingportal.getTeamRiders(0)));
		cyclingportal.removeRider(2);
		System.out.println(Arrays.toString(cyclingportal.getTeamRiders(0))); 
		//need to write check in case getTeamRiders catches  null value
		
		cyclingportal.removeTeam(1);
		cyclingportal.addStageToRace(0,"stage 3", "just stage 3", 10 ,LocalDateTime.parse("2022-12-03T16:00:00"), StageType.HIGH_MOUNTAIN);
		cyclingportal.addStageToRace(0,"stage 2", "just stage 2", 10 ,LocalDateTime.parse("2022-12-03T14:00:00"), StageType.TT);
		cyclingportal.addStageToRace(0,"stage 1", "just stage 1", 10 ,LocalDateTime.parse("2022-12-03T12:00:00"), StageType.FLAT);
		cyclingportal.addCategorizedClimbToStage(0, 6.5, SegmentType.C2, 12.0, 1.5);
		//System.out.println(cyclingportal.raceList.get(0).getStageList());
		cyclingportal.addCategorizedClimbToStage(0, 3.0, SegmentType.C3, 8.0, 2.0);
		//System.out.println(Arrays.toString(cyclingportal.getStageSegments(0)));
		cyclingportal.addIntermediateSprintToStage(0, 2.0);
		//System.out.println(Arrays.toString(cyclingportal.getStageSegments(0)));
		cyclingportal.concludeStagePreparation(0);
		cyclingportal.concludeStagePreparation(1);
		cyclingportal.addIntermediateSprintToStage(2, 2);
		cyclingportal.addCategorizedClimbToStage(2, 2.0, SegmentType.HC, 8.0, 2.0);
		cyclingportal.addIntermediateSprintToStage(2, 6.5);
		cyclingportal.concludeStagePreparation(2);
		System.out.println(cyclingportal.raceList.get(0).getStageList());
		//System.out.println(cyclingportal.viewRaceDetails(0));
		System.out.println(Arrays.toString(cyclingportal.getStageSegments(0)));
		//System.out.println(Arrays.toString(cyclingportal.getStageSegments(1)));
		//System.out.println(Arrays.toString(cyclingportal.getStageSegments(2)));
		
		// test score functions
		
		
		
		
		
		
		
		
		System.out.println("end");
	}
}
	
