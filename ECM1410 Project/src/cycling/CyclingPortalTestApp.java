package cycling;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class CyclingPortalTestApp {
	public static void main (String[] args) throws IllegalNameException, InvalidNameException, IllegalArgumentException, IDNotRecognisedException, InvalidLengthException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException, DuplicatedResultException, InvalidCheckpointsException {
		CyclingPortal cyclingportal = new CyclingPortal();
		cyclingportal.createRace("race", "the small tour");
		
		cyclingportal.createTeam("team 1","just team 1");
		cyclingportal.createTeam("team 2", "just team 2");
		
		cyclingportal.createRider(0,"aob", 1900);
		cyclingportal.createRider(0,"bob", 2100);
		cyclingportal.createRider(0,"cob", 2002);
		cyclingportal.createRider(0,"dob", 2022);
		
		
		cyclingportal.createRider(1,"aoz", 1900);
		cyclingportal.createRider(1,"boz", 2100);
		cyclingportal.createRider(1,"coz", 2002);
		cyclingportal.createRider(1,"doz", 2022);
		
		
		//cyclingportal.removeRider(0);
		//cyclingportal.removeRider(2);
		//need to write check in case getTeamRiders catches  null value
		
		//cyclingportal.removeTeam(1);
		
		cyclingportal.addStageToRace(0,"stage 1", "just stage 1", 10 ,LocalDateTime.parse("2022-12-03T12:00:00"), StageType.HIGH_MOUNTAIN);
		cyclingportal.addStageToRace(0,"stage 2", "just stage 2", 10 ,LocalDateTime.parse("2022-12-03T14:00:00"), StageType.TT);
		cyclingportal.addStageToRace(0,"stage 3", "just stage 3", 10 ,LocalDateTime.parse("2022-12-03T16:00:00"), StageType.FLAT);
		
		
		cyclingportal.addCategorizedClimbToStage(0, 6.5, SegmentType.C2, 12.0, 1.5);
		cyclingportal.addCategorizedClimbToStage(0, 3.0, SegmentType.C3, 8.0, 2.0);
		cyclingportal.addIntermediateSprintToStage(0, 2.0);
	
		

		cyclingportal.addIntermediateSprintToStage(2, 2);
		cyclingportal.addCategorizedClimbToStage(2, 2.0, SegmentType.HC, 8.0, 2.0);
		cyclingportal.addIntermediateSprintToStage(2, 6.5);
		
		cyclingportal.concludeStagePreparation(0);
		cyclingportal.concludeStagePreparation(1);
		cyclingportal.concludeStagePreparation(2);
		System.out.println(cyclingportal.raceList.get(0).getStageList());
		//System.out.println(cyclingportal.viewRaceDetails(0));
		System.out.println(Arrays.toString(cyclingportal.getStageSegments(0)));
		//System.out.println(Arrays.toString(cyclingportal.getStageSegments(1)));
		//System.out.println(Arrays.toString(cyclingportal.getStageSegments(2)));
		
		// test score functions
		
		//stage 1 results
		LocalTime[] rider1results = {LocalTime.parse("12:10:00"),LocalTime.parse("12:20:00.2"),LocalTime.parse("12:30:01.2")};
		LocalTime[] rider2results = {LocalTime.parse("12:10:00"),LocalTime.parse("12:21:01.7"),LocalTime.parse("12:30:01.7")};
		LocalTime[] rider3results = {LocalTime.parse("12:10:00"),LocalTime.parse("12:22:00.5"),LocalTime.parse("12:30:01.3")};
		LocalTime[] rider4results = {LocalTime.parse("12:10:00"),LocalTime.parse("12:23:00"),LocalTime.parse("12:30:01.4")};
		LocalTime[] rider5results = {LocalTime.parse("12:10:00"),LocalTime.parse("12:24:00"),LocalTime.parse("12:30:01.5")};
		LocalTime[] rider6results = {LocalTime.parse("12:10:00"),LocalTime.parse("12:25:00"),LocalTime.parse("12:30:01.7")};
		LocalTime[] rider7results = {LocalTime.parse("12:10:00"),LocalTime.parse("12:26:00"),LocalTime.parse("12:30:01.7")};
		LocalTime[] rider8results = {LocalTime.parse("12:10:00"),LocalTime.parse("12:27:00"),LocalTime.parse("12:30:01.1")};

		
		cyclingportal.registerRiderResultsInStage(0, 5, rider6results);
		cyclingportal.registerRiderResultsInStage(0, 6, rider7results);
		cyclingportal.registerRiderResultsInStage(0, 7, rider8results);
		cyclingportal.registerRiderResultsInStage(0, 0, rider1results);
		cyclingportal.registerRiderResultsInStage(0, 1, rider2results);
		cyclingportal.registerRiderResultsInStage(0, 2, rider3results);
		cyclingportal.registerRiderResultsInStage(0, 3, rider4results);
		cyclingportal.registerRiderResultsInStage(0, 4, rider5results);
		
		
		
		
		LocalTime[] s2rider1results = {LocalTime.parse("12:10:00"),LocalTime.parse("12:20:00"),LocalTime.parse("12:30:00")};
		LocalTime[] s2rider2results = {LocalTime.parse("12:11:00"),LocalTime.parse("12:21:00"),LocalTime.parse("12:31:00")};
		LocalTime[] s2rider3results = {LocalTime.parse("12:12:00"),LocalTime.parse("12:22:00"),LocalTime.parse("12:32:00")};
		LocalTime[] s2rider4results = {LocalTime.parse("12:13:00"),LocalTime.parse("12:23:00"),LocalTime.parse("12:33:00")};
		LocalTime[] s2rider5results = {LocalTime.parse("12:14:00"),LocalTime.parse("12:24:00"),LocalTime.parse("12:34:00")};
		LocalTime[] s2rider6results = {LocalTime.parse("12:15:00"),LocalTime.parse("12:25:00"),LocalTime.parse("12:35:00")};
		LocalTime[] s2rider7results = {LocalTime.parse("12:16:00"),LocalTime.parse("12:26:00"),LocalTime.parse("12:36:00")};
		LocalTime[] s2rider8results = {LocalTime.parse("12:17:00"),LocalTime.parse("12:27:00"),LocalTime.parse("12:37:00")};
		
		
		cyclingportal.registerRiderResultsInStage(1, 0, s2rider1results);
		cyclingportal.registerRiderResultsInStage(1, 1, s2rider2results);
		cyclingportal.registerRiderResultsInStage(1, 2, s2rider3results);
		cyclingportal.registerRiderResultsInStage(1, 3, s2rider4results);
		cyclingportal.registerRiderResultsInStage(1, 4, s2rider5results);
		cyclingportal.registerRiderResultsInStage(1, 5, s2rider6results);
		cyclingportal.registerRiderResultsInStage(1, 6, s2rider7results);
		cyclingportal.registerRiderResultsInStage(1, 7, s2rider8results);
		
		
		
		LocalTime[] s3rider1results = {LocalTime.parse("12:00:00"),LocalTime.parse("12:20:00"),LocalTime.parse("12:30:00")};
		LocalTime[] s3rider2results = {LocalTime.parse("12:00:00"),LocalTime.parse("12:21:00"),LocalTime.parse("12:30:00.1")};
		LocalTime[] s3rider3results = {LocalTime.parse("12:00:00"),LocalTime.parse("12:22:00"),LocalTime.parse("12:30:00.2")};
		LocalTime[] s3rider4results = {LocalTime.parse("12:00:00"),LocalTime.parse("12:23:00"),LocalTime.parse("12:30:00.3")};
		LocalTime[] s3rider5results = {LocalTime.parse("12:00:00"),LocalTime.parse("12:24:00"),LocalTime.parse("12:30:00.6")};
		LocalTime[] s3rider6results = {LocalTime.parse("12:00:00"),LocalTime.parse("12:25:00"),LocalTime.parse("12:30:00")};
		LocalTime[] s3rider7results = {LocalTime.parse("12:00:00"),LocalTime.parse("12:26:00"),LocalTime.parse("12:50:00")};
		LocalTime[] s3rider8results = {LocalTime.parse("12:00:00"),LocalTime.parse("12:27:00"),LocalTime.parse("12:30:00")};
		
		cyclingportal.registerRiderResultsInStage(2, 0, s3rider1results);
		cyclingportal.registerRiderResultsInStage(2, 1, s3rider2results);
		cyclingportal.registerRiderResultsInStage(2, 2, s3rider3results);
		cyclingportal.registerRiderResultsInStage(2, 3, s3rider4results);
		cyclingportal.registerRiderResultsInStage(2, 4, s3rider5results);
		cyclingportal.registerRiderResultsInStage(2, 5, s3rider6results);
		cyclingportal.registerRiderResultsInStage(2, 6, s3rider7results);
		cyclingportal.registerRiderResultsInStage(2, 7, s3rider8results);
		
		System.out.println(Arrays.toString(cyclingportal.getRidersRankInStage(0)));
		System.out.println("c"+Arrays.toString(cyclingportal.getRiderResultsInStage(0,0)));
		//error is caused due to the .get() method inside of "stageList.get(stageId).getStageResults().get(0).add(0, checkpoints);"
		System.out.println("a"+Arrays.deepToString((LocalTime[])cyclingportal.stageList.get(0).getStageResults().get(0).get(0)));
		System.out.println((cyclingportal.getRiderAdjustedElapsedTimeInStage(0,0)));
		System.out.println((cyclingportal.getRiderAdjustedElapsedTimeInStage(0,7)));
		System.out.println(Arrays.deepToString(cyclingportal.getRankedAdjustedElapsedTimesInStage(0)));
		System.out.println(Arrays.toString(cyclingportal.getRidersPointsInStage(0)));
		System.out.println(Arrays.toString(cyclingportal.getRidersMountainPointsInStage(0)));
		
		//System.out.println(Arrays.toString(cyclingportal.getGeneralClassificationTimesInRace(0)));
		//System.out.println(Arrays.toString(cyclingportal.getRidersPointsInRace(0)));
		//System.out.println(Arrays.toString(cyclingportal.getRidersMountainPointsInRace(0)));
		//System.out.println(Arrays.toString(cyclingportal.getRidersGeneralClassificationRank(0)));
		//System.out.println(Arrays.toString(cyclingportal.getRidersPointClassificationRank(0)));
		//System.out.println(Arrays.toString(cyclingportal.getRidersMountainPointClassificationRank(0)));
		
		//need to rewrite all of these , go through see what returns and start from scratch
		System.out.println("end");
	}
}
	
