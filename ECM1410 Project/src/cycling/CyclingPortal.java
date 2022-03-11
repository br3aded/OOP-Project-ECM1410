package cycling;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CyclingPortal implements CyclingPortalInterface {
	ArrayList<Team> teamList = new ArrayList<Team>();
	ArrayList<Rider> riderList = new ArrayList<Rider>();
	ArrayList<Race> raceList = new ArrayList<Race>();
	ArrayList<Stage> stageList = new ArrayList<Stage>();
	ArrayList<Segment> segmentList = new ArrayList<>();
	
	final int[] FLATSTAGESCORE = {50,30,20,18,16,14,12,10,8,7,6,5,4,3,2,0};
	final int[] MEDIUMSTAGESCORE = {30,25,22,19,17,15,13,11,9,7,6,5,4,3,2,0};
	final int[] HIGHSTAGESCORE = {20,17,15,13,11,10,9,8,7,6,5,4,3,2,1,0};
	final int[] TTSTAGESCORE = {20,17,15,13,11,10,9,8,7,6,5,4,3,2,1,0};
	final int[] SPRINTSEGMENTSCORE = {20,17,15,13,11,10,9,8,7,6,5,4,3,2,1,0};
	final int[] FOURCSCORE = {1,0};
	final int[] THREECSCORE = {2,1,0};
	final int[] TWOCSCORE = {5,3,2,1,0};
	final int[] ONECSCORE = {10,8,6,4,2,1};
	final int[] HCSCORE = {20,15,12,10,8,6,4,2};
	

	@Override
	public int[] getRaceIds() {
		ArrayList<Integer> tempRaceIdList = new ArrayList<Integer>();
		for(int i = 0; i< raceList.size(); i++) {
			if(raceList.get(i) != null) {
				tempRaceIdList.add(i);
			}
		}
		int[] raceIdList = tempRaceIdList.stream().mapToInt(i -> i).toArray();
		return raceIdList;
	}

	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
		if((name==null) || (name.trim()=="")) {
			throw new InvalidNameException("A races name cannot be either null,empty, or whitespace");
			}
			if(name.trim().length()>30)
			{
			throw new InvalidNameException("A races name must be less then 30 characters");
			}
			if(name.contains(" "))
			{
			throw new InvalidNameException("A races name cannot contain spaces");
			}
			for(int i=0;i<raceList.size();i=i+1)
			{
			if(name==raceList.get(i).getRaceName())
			{
			throw new IllegalNameException("This name is used for another race");
			}
			}
		raceList.add(new Race(name,description));// add functionality for filling in the gaps
		return raceList.size() -1; // change functionality when filling in gaps
	}

	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		if((raceId>raceList.size()) || (raceList.get(raceId)==null))
		{
		throw new IDNotRecognisedException("This raceID does not exist");
		}
		int numberOfStage = raceList.get(raceId).getStageList().size();
		int totalLength = 0;
		for(int i=0; i<raceList.get(raceId).getStageList().size(); i++) {
			totalLength += stageList.get(raceList.get(raceId).getStageList().get(i)).getStageLength();
		}
		return "[RaceId= "+ raceId + " ,Descripiton= " + raceList.get(raceId).getRaceDescription() + " ,number of stages= " +numberOfStage + " ,total length= " + totalLength + "]";
		
	}

	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
		for(int i=0; i<raceList.get(raceId).getStageList().size();i++) {
			for(int j=0; j<stageList.get(raceList.get(raceId).getStageList().get(i)).getSegmentList().size(); j++) {
				segmentList.set(stageList.get(raceList.get(raceId).getStageList().get(i)).getSegmentList().get(j), null);
			}
		}
		for(int i=0 ; i< raceList.get(raceId).getStageList().size(); i++) {
			stageList.set(raceList.get(raceId).getStageList().get(i),null);
		}
		
		raceList.set(raceId, null);
	}

	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		return raceList.get(raceId).getStageList().size();
	}

	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
		stageList.add(new Stage(raceId,stageName,description,length,startTime,type));
		if(raceList.get(raceId).getStageList().size() !=0) {
			for(int i= 0; i<raceList.get(raceId).getStageList().size(); i++) {
				if(stageList.get(raceList.get(raceId).getStageList().get(i)).getStageStartTime().isAfter(stageList.get(raceList.get(raceId).getStageList().get(stageList.size()-1)).getStageStartTime()) != true) {
					raceList.get(raceId).getStageList().add(i,stageList.size()-1);
				}
			} 
		} else {raceList.get(raceId).getStageList().add(0,stageList.size()-1);}
		raceList.get(raceId).addStage(stageList.size() -1);
		
		
		return stageList.size() -1;
	}

	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		return raceList.get(raceId).getStageList().stream().mapToInt(i -> i).toArray();
	}

	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		return stageList.get(stageId).getStageLength();
	}

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
			for(int i=0 ; i< stageList.get(stageId).getSegmentList().size(); i++) {
				segmentList.set(stageList.get(stageId).getSegmentList().get(i),null);
			}
			raceList.get(stageList.get(stageId).getRaceId()).removeStage(stageId);
			stageList.set(stageId, null);
		}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
		segmentList.add(new Segment(stageId, location , averageGradient, length, type));
		if(stageList.get(stageId).getSegmentList().size() != 0) {
			for(int i=0; i<stageList.get(stageId).getSegmentList().size(); i++) {
				if (segmentList.get(stageList.get(stageId).getSegmentList().get(i)).getLocation()  > segmentList.get(segmentList.size()-1).getLocation()) {
					stageList.get(stageId).getSegmentList().add(i,(segmentList.size()-1));
					}
				}
			}	else {stageList.get(stageId).getSegmentList().add(0,(segmentList.size()-1));}
		return segmentList.size()-1;
		}


	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		if(stageList.get(stageId).getStageType() == StageType.TT) {
			throw new InvalidStageTypeException("Cannot add segements to time trial stage type");
		}
		segmentList.add(new Segment(stageId, location));
		if(stageList.get(stageId).getSegmentList().size() != 0) {
			for(int i=0; i<stageList.get(stageId).getSegmentList().size(); i++) {
				if (segmentList.get(stageList.get(stageId).getSegmentList().get(i)).getLocation()  > segmentList.get(segmentList.size()-1).getLocation()) {
					stageList.get(stageId).getSegmentList().add(i,(segmentList.size()-1));
					}
				}
			}	else {stageList.get(stageId).getSegmentList().add(0,(segmentList.size()-1));}
		return segmentList.size()-1;
		}

	@Override
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
		stageList.get(segmentList.get(segmentId).getStageId()).removeSegment(segmentId);
		segmentList.set(segmentId, null);
	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		stageList.get(stageId).setIsConcluded(true);
	}

	@Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
		return stageList.get(stageId).getSegmentList().stream().mapToInt(i -> i).toArray();
	}

	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		if ((name == null) || (name.length() > 16)) {
			throw new InvalidNameException();
		}
		for (int i = 0; i< name.length() ;  i++) {
			if (name.charAt(i) == ' ') {
				throw new InvalidNameException("A team name must not contain any spaces");
			}
		}
		for(int i = 0; i< teamList.size(); i++) {
			if(teamList.get(i).getTeamName() == name) {
				throw new IllegalNameException("The team name is already in use");
			}
		}
		teamList.add(new Team(name,description));// add functionality for filling in the gaps
		return teamList.size() -1; // change functionality when filling in gaps
	}

	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		for(int i=0; i<teamList.get(teamId).getRiderList().size(); i++) {
			riderList.set(teamList.get(teamId).getRiderList().get(i),null);
		}
		teamList.set(teamId, null);
	}

	@Override
	public int[] getTeams() {
		ArrayList<Integer> tempTeamIdList = new ArrayList<Integer>();
		for(int i = 0; i< teamList.size(); i++) {
			if(teamList.get(i) != null) {
				tempTeamIdList.add(i);
			}
		}
		int[] teamIdList = tempTeamIdList.stream().mapToInt(i -> i).toArray();
		return teamIdList;
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		int[] riderIdList = teamList.get(teamId).getRiderList().stream().filter(i -> i != null).mapToInt(i -> i).toArray();
		return riderIdList;
	}

	@Override
	public int createRider(int teamID, String name, int yearOfBirth) throws IDNotRecognisedException, IllegalArgumentException {
		riderList.add(new Rider(teamID,name,yearOfBirth));
		teamList.get(teamID).addRider(riderList.size() -1);
		return riderList.size() -1;
	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		teamList.get(riderList.get(riderId).getTeamID()).setRiderList(riderId);
		riderList.set(riderId, null);

	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {
		if(stageList.get(stageId).getIsConcluded() == true) {
			if(stageList.get(stageId).getStageResults().size() == 0) {
				stageList.get(stageId).getStageResults().get(0).add(0,checkpoints);
				stageList.get(stageId).getStageResults().get(0).add(1,riderId);
			}else {
				LocalTime tempCheckpointsElapsedTime = LocalTime.of(checkpoints[checkpoints.length-1].getHour()-checkpoints[0].getHour(),checkpoints[checkpoints.length-1].getMinute()-checkpoints[0].getMinute(),checkpoints[checkpoints.length-1].getSecond()-checkpoints[0].getSecond(),checkpoints[checkpoints.length-1].getNano()-checkpoints[0].getNano());
				for(int i = 0; i<stageList.get(stageId).getStageResults().size(); i++) {
					LocalTime[] tempCheckpoints = (LocalTime[]) stageList.get(stageId).getStageResults().get(i).get(0);
					LocalTime tempElapsedTime = LocalTime.of(tempCheckpoints[tempCheckpoints.length-1].getHour()-tempCheckpoints[0].getHour(),tempCheckpoints[tempCheckpoints.length-1].getMinute()-tempCheckpoints[0].getMinute(),tempCheckpoints[tempCheckpoints.length-1].getSecond()-tempCheckpoints[0].getSecond(),tempCheckpoints[tempCheckpoints.length-1].getNano()-tempCheckpoints[0].getNano());
					if(tempCheckpointsElapsedTime.isBefore(tempElapsedTime)){
						stageList.get(stageId).getStageResults().get(i).add(0,checkpoints);
						stageList.get(stageId).getStageResults().get(i).add(1,riderId);
					}
				}
			}
			
		}else {throw new InvalidStageStateException("Stage state hasnt been concluded");}

	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		return null;
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		for(int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {
			if((Integer)stageList.get(stageId).getStageResults().get(i).get(1) == riderId) {
				break;
			}else if(i == stageList.get(stageId).getStageResults().size()-1){
				return null;
			}
			
		}
		int positionInStageResults = 0;
		for(int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {
			if((Integer)stageList.get(stageId).getStageResults().get(i).get(1) == riderId) {
				positionInStageResults = i;
				break;
			}
		}
		
		LocalTime[] currentElapsedTime = null;
		for(int i = positionInStageResults; i>0 ;i--) {
			LocalTime[] checkpointsCurrent = (LocalTime[])stageList.get(stageId).getStageResults().get(i).get(0);
			LocalTime[] checkpointsBelow = (LocalTime[])stageList.get(stageId).getStageResults().get(i-1).get(0);
			if(checkpointsCurrent[checkpointsCurrent.length-1].minusSeconds(1).isBefore(checkpointsBelow[checkpointsBelow.length-1])) {
				currentElapsedTime = checkpointsBelow;
			}else {
				break;
			}

		}
		return LocalTime.of(currentElapsedTime[currentElapsedTime.length-1].getHour()-currentElapsedTime[0].getHour(),currentElapsedTime[currentElapsedTime.length-1].getMinute()-currentElapsedTime[0].getMinute(),currentElapsedTime[currentElapsedTime.length-1].getSecond()-currentElapsedTime[0].getSecond(),currentElapsedTime[currentElapsedTime.length-1].getNano()-currentElapsedTime[0].getNano());
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		for(int i = 0; i< stageList.get(stageId).getStageResults().size(); i++) {
			if((Integer)stageList.get(stageId).getStageResults().get(i).get(1) == riderId ) {
				stageList.get(stageId).getStageResults().remove(i);
				break;
			}
		}

	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		int[] ridersRanked;
		if(stageList.get(stageId).getStageResults().size()!=0) {
			ridersRanked  =new int[stageList.get(stageId).getStageResults().size()-1];
			for(int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {
				ridersRanked[i] = (Integer)stageList.get(stageId).getStageResults().get(i).get(1);
			}
		}else {
	return new int[0];}
		
	return ridersRanked;
	
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		LocalTime[] rankedElapsedTime;
		if(stageList.get(stageId).getStageResults().size()!=0) {
			rankedElapsedTime = new LocalTime[stageList.get(stageId).getStageResults().size()-1];
			for(int i = 0 ; i<stageList.get(stageId).getStageResults().size(); i++) {
				rankedElapsedTime[i] = getRiderAdjustedElapsedTimeInStage(stageId, (Integer)stageList.get(stageId).getStageResults().get(i).get(1));
			}
		} else {return new LocalTime[0];}
		return rankedElapsedTime;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		int[] riderPosition = getRidersRankInStage(stageId);
		int[] ridersPointsInStage = new int[stageList.get(stageId).getStageResults().size()-1];
		if(stageList.get(stageId).getStageResults().size() !=0)
		if(stageList.get(stageId).getStageType() == StageType.TT ) {
			for(int i = 0; i<stageList.get(stageId).getStageResults().size() ; i++) {
				ridersPointsInStage[i]= TTSTAGESCORE[i];
				return ridersPointsInStage;
			}
		} else {
			for(int j = 0; j < (((LocalTime[])stageList.get(stageId).getStageResults().get(0).get(0)).length-1); j++) {
				ArrayList<ArrayList<Object>> tempSegSort = new ArrayList<>();
				if(tempSegSort.size() == 0) {
					tempSegSort.get(0).add(0,stageList.get(stageId).getStageResults().get(0).get(0));
					tempSegSort.get(0).add(1,stageList.get(stageId).getStageResults().get(0).get(1));
				}else {
					for(int i = 0; i<stageList.get(stageId).getStageResults().size(); i++) {
						for(int z =0 ; z< tempSegSort.size();z++) {
							LocalTime[] tempCurrentSort = (LocalTime[])stageList.get(stageId).getStageResults().get(i).get(0);
							LocalTime[] tempListSort = (LocalTime[])tempSegSort.get(z).get(0);
							if(((LocalTime)(tempListSort[i])).isBefore((LocalTime)tempCurrentSort[i]) == true){
								tempSegSort.get(z).add(0,stageList.get(stageId).getStageResults().get(0).get(0));
								tempSegSort.get(z).add(1,stageList.get(stageId).getStageResults().get(0).get(1));
								break;
							}
						}
					}
				}
				int[] tempRidersRanked  =new int[tempSegSort.size()-1];
				for(int i = 0; i < tempSegSort.size(); i++) {
					tempRidersRanked[i] = (Integer)tempSegSort.get(i).get(1);
				}
				for(int i = 0; i<tempRidersRanked.length ; i++) {
					ridersPointsInStage[i] += SPRINTSEGMENTSCORE[Arrays.binarySearch(tempRidersRanked, riderPosition[i])];
				}
			}
		if(stageList.get(stageId).getStageType() == StageType.FLAT){
			for(int i = 0; i<stageList.get(stageId).getStageResults().size() ; i++) {
				if(i < FLATSTAGESCORE.length) {
					ridersPointsInStage[i] += FLATSTAGESCORE[i];
					}
				}
			}else if(stageList.get(stageId).getStageType() == StageType.MEDIUM_MOUNTAIN){
			for(int i = 0; i<stageList.get(stageId).getStageResults().size() ; i++) {
				if(i<MEDIUMSTAGESCORE.length) {
				ridersPointsInStage[i] += MEDIUMSTAGESCORE[i];
				}
			}
		}else if(stageList.get(stageId).getStageType() == StageType.HIGH_MOUNTAIN) {
			for(int i = 0; i<stageList.get(stageId).getStageResults().size() ; i++) {
				if(i<HIGHSTAGESCORE.length) {
			ridersPointsInStage[i] += HIGHSTAGESCORE[i];
				}
			}
		}
		
		}
		return ridersPointsInStage;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eraseCyclingPortal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRaceByName(String name) throws NameNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

}
