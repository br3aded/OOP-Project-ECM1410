package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CyclingPortal implements CyclingPortalInterface {
	ArrayList<Team> teamList = new ArrayList<Team>();
	ArrayList<Rider> riderList = new ArrayList<Rider>();
	ArrayList<Race> raceList = new ArrayList<Race>();
	ArrayList<Stage> stageList = new ArrayList<Stage>();
	ArrayList<Segment> segmentList = new ArrayList<>();
	
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
		raceList.add(new Race(name,description));// add functionality for filling in the gaps
		return raceList.size() -1; // change functionality when filling in gaps
	}

	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
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
