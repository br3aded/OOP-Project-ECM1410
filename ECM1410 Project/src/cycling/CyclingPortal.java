package cycling;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class CyclingPortal implements CyclingPortalInterface{
	//creates the ArrayLists which all the objects will be store
	public ArrayList<Team> teamList = new ArrayList<Team>();
	public ArrayList<Rider> riderList = new ArrayList<Rider>();
	public ArrayList<Race> raceList = new ArrayList<Race>();
	public ArrayList<Stage> stageList = new ArrayList<Stage>();
	public ArrayList<Segment> segmentList = new ArrayList<>();
	//create static lists where the scores are stored
	final int[] FLATSTAGESCORE = { 50, 30, 20, 18, 16, 14, 12, 10, 8, 7, 6, 5, 4, 3, 2, 0 };
	final int[] MEDIUMSTAGESCORE = { 30, 25, 22, 19, 17, 15, 13, 11, 9, 7, 6, 5, 4, 3, 2, 0 };
	final int[] HIGHSTAGESCORE = { 20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
	final int[] TTSTAGESCORE = { 20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
	final int[] SPRINTSEGMENTSCORE = { 20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
	final int[] FOURCSCORE = { 1, 0 };
	final int[] THREECSCORE = { 2, 1, 0 };
	final int[] TWOCSCORE = { 5, 3, 2, 1, 0 };
	final int[] ONECSCORE = { 10, 8, 6, 4, 2, 1 };
	final int[] HCSCORE = { 20, 15, 12, 10, 8, 6, 4, 2 };

	@Override
	public int[] getRaceIds() {
		ArrayList<Integer> tempRaceIdList = new ArrayList<Integer>();//creates a temporary list
		for (int i = 0; i < raceList.size(); i++) {
			if (raceList.get(i) != null) {
				tempRaceIdList.add(i);//adds id's to a temporary list
			}
		}
		int[] raceIdList = tempRaceIdList.stream().mapToInt(i -> i).toArray();// changes the ArrayList to a list
		return raceIdList;
	}

	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
		if ((name == null) || (name.trim() == "")) {// Exception throw
			throw new InvalidNameException("A races name cannot be either null,empty, or whitespace");
		}
		if (name.trim().length() > 30) {// Exception throw
			throw new InvalidNameException("A races name must be 30 characters or less");
		}
		if (name.contains(" ")) {// Exception throw
			throw new InvalidNameException("A races name cannot contain spaces");
		}
		for (int i = 0; i < raceList.size(); i = i + 1) {// Exception throw
			if (name == raceList.get(i).getRaceName()) {
				throw new IllegalNameException("This name is used for another race");
			}
		}
		raceList.add(new Race(name, description));//adds new race object to the race list
		return raceList.size() - 1; //returns the new race id 
	}

	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		if ((raceId<0) || (raceId >= raceList.size()) || (raceList.get(raceId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This raceID does not exist");
		}
		int numberOfStage = raceList.get(raceId).getStageList().size(); // stores the number of stages
		int totalLength = 0;
		for (int i = 0; i < raceList.get(raceId).getStageList().size(); i++) {
			totalLength += stageList.get(raceList.get(raceId).getStageList().get(i)).getStageLength(); // Calculates the total length
		}
		return "[RaceId= " + raceId + " ,Descripiton= " + raceList.get(raceId).getRaceDescription()
				+ " ,number of stages= " + numberOfStage + " ,total length= " + totalLength + "]"; // returns string of race details

	}

	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
		if ((raceId<0) ||(raceId >= raceList.size()) || (raceList.get(raceId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This raceID does not exist");
		}
		for (int i = 0; i < raceList.get(raceId).getStageList().size(); i++) {//iterates through each stage in a race
			for (int j = 0; j < stageList.get(raceList.get(raceId).getStageList().get(i)).getSegmentList()
					.size(); j++) {//iterates through each segment in a stage
				segmentList.set(stageList.get(raceList.get(raceId).getStageList().get(i)).getSegmentList().get(j),
						null);//sets all the segment objects within a race to null in the segment list
			}
		}
		for (int i = 0; i < raceList.get(raceId).getStageList().size(); i++) {//iterates through each stage in a race
			stageList.set(raceList.get(raceId).getStageList().get(i), null);//sets all the stage objects within a race to null
		}
		raceList.set(raceId, null);//sets the race object to null
	} 
	

	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {// Exception throw
		if ((raceId<0) ||(raceId >= raceList.size()) || (raceList.get(raceId) == null)) {
			throw new IDNotRecognisedException("This raceID does not exist");
		}
		return raceList.get(raceId).getStageList().size();// returns the size of the stage list from a race
	}

	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
		if ((raceId<0) ||(raceId >= raceList.size()) || (raceList.get(raceId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This raceID does not exist");
		}
		for(int i =0; i<raceList.get(raceId).getStageList().size();i++) {
			if(stageName == stageList.get(raceList.get(raceId).getStageList().get(i)).getStageName()) {// Exception throw
				throw new IllegalNameException("This stage name is already in use in this race");
			}
		}

		if ((stageName == null) || (stageName.trim() == "")) {// Exception throw
			throw new InvalidNameException("A stages name cannot be either null,empty, or whitespace");
		}
		if (stageName.trim().length() > 30) {// Exception throw
			throw new InvalidNameException("A stages name must be less then 30 characters");
		}

		if (length < 5) {// Exception throw
			throw new InvalidLengthException("A stages length cannot be less then 5km");
		}
		stageList.add(new Stage(raceId, stageName, description, length, startTime, type));//adds a new stage object to the stage list
		if (raceList.get(raceId).getStageList().size() != 0) {//checks to see if stage list within a race is empty
			for (int i = 0; i < raceList.get(raceId).getStageList().size() - 1; i++) {//inserts the new stage into correct place in stage list
				if (stageList.get(raceList.get(raceId).getStageList().get(i)).getStageStartTime()
						.isAfter(stageList.get(stageList.size() - 1).getStageStartTime()) == false) {
					raceList.get(raceId).addStage(stageList.size() - 1);
					return stageList.size() - 1;//returns stage id
				}
			}
			raceList.get(raceId).getStageList().add(stageList.size() - 1);// to end of stage list
		} else {
			raceList.get(raceId).getStageList().add(0, stageList.size() - 1);//adds to list if list is empty
		}

		return stageList.size() - 1;//returns stage id
	}

	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		if ((raceId<0) ||(raceId >= raceList.size()) || (raceList.get(raceId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This raceID does not exist");
		}
		return raceList.get(raceId).getStageList().stream().mapToInt(i -> i).toArray();//returns stage id list
	}

	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		if ((stageId<0) || (stageId >= stageList.size()) || (stageList.get(stageId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This stageID does not exist");
		}
		return stageList.get(stageId).getStageLength();//returns a length of stage
	}

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		if ((stageId<0) || (stageId >= stageList.size()) || (stageList.get(stageId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This stageID does not exist");
		}
		for (int i = 0; i < stageList.get(stageId).getSegmentList().size(); i++) {// Exception throw
			segmentList.set(stageList.get(stageId).getSegmentList().get(i), null);
		}
		raceList.get(stageList.get(stageId).getRaceId()).removeStage(stageId);//removes stage id from race
		stageList.set(stageId, null);
	}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {

		if ((stageId<0) || (stageId >= stageList.size()) || (stageList.get(stageId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This stageID does not exist");
		}
		if ((location < 0) || (location > stageList.get(stageId).getStageLength())) {// Exception throw
			throw new InvalidLocationException("The location has to be within the stages length");
		}

		if (stageList.get(stageId).getIsConcluded()) {// Exception throw
			throw new InvalidStageStateException("Segments cannot be added to stages waiting for results");
		}

		if (stageList.get(stageId).getStageType() == StageType.TT) {// Exception throw
			throw new InvalidStageTypeException("You cannot add segements to time trials");
		}
		
		segmentList.add(new Segment(stageId, location, averageGradient, length, type));//adds new segment object to segment list
		if (stageList.get(stageId).getSegmentList().size() != 0) {//inserts the segment into the correct position in segment list
			for (int i = 0; i < stageList.get(stageId).getSegmentList().size(); i++) {
				if (segmentList.get(stageList.get(stageId).getSegmentList().get(i)).getLocation() >= segmentList
						.get(segmentList.size() - 1).getLocation()) {
					stageList.get(stageId).getSegmentList().add(i, (segmentList.size() - 1));
					return segmentList.size() - 1;//returns segment id
				}
			}
			stageList.get(stageId).getSegmentList().add((segmentList.size() - 1));
		} else {
			stageList.get(stageId).getSegmentList().add((segmentList.size() - 1));
		}
		return segmentList.size() - 1;//returns segment id
	}

	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		if ((stageId<0) || (stageId >= stageList.size()) || (stageList.get(stageId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This stageID does not exist");
		}
		if ((location < 0) || (location > stageList.get(stageId).getStageLength())) {// Exception throw
			throw new InvalidLocationException("The location has to be within the stages length");
		}

		if (stageList.get(stageId).getIsConcluded()) {// Exception throw
			throw new InvalidStageStateException("Segments cannot be added to stages waiting for results");
		}

		if (stageList.get(stageId).getStageType() == StageType.TT) {// Exception throw
			throw new InvalidStageTypeException("You cannot add segements to time trials");
		}
		segmentList.add(new Segment(stageId, location));//adds new segment object to segment list
		if (stageList.get(stageId).getSegmentList().size() != 0) {//inserts segment to the correct 
			for (int i = 0; i < stageList.get(stageId).getSegmentList().size(); i++) {
				if (segmentList.get(stageList.get(stageId).getSegmentList().get(i)).getLocation() >= segmentList
						.get(segmentList.size() - 1).getLocation()) {
					stageList.get(stageId).getSegmentList().add(i, (segmentList.size() - 1));
					return segmentList.size() - 1;//returns segment id
				}
			}
			stageList.get(stageId).getSegmentList().add((segmentList.size() - 1));
		} else {
			stageList.get(stageId).getSegmentList().add((segmentList.size() - 1));
		}
		return segmentList.size() - 1;//returns segment id
	}

	@Override
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
		if ((segmentId<0) ||(segmentId > segmentList.size()) || (segmentList.get(segmentId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This segmentID does not exist");
		}
		if (stageList.get(segmentList.get(segmentId).getStageId()).getIsConcluded()) {// Exception throw
			throw new InvalidStageStateException("This stage is already concluded");
		}
		stageList.get(segmentList.get(segmentId).getStageId()).removeSegment(segmentId);//removes segment id from segment list
		segmentList.set(segmentId, null);//sets segment object to null
	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		if ((stageId<0) || (stageId > stageList.size()) || (stageList.get(stageId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This stageID does not exist");
		}
		if (stageList.get(stageId).getIsConcluded()) {// Exception throw
			throw new InvalidStageStateException("This stage is already concluded");
		}
		stageList.get(stageId).setIsConcluded(true);//runs the is concluded function within the stage object
	}

	@Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
		if ((stageId<0) || (stageId > stageList.size()) || (stageList.get(stageId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This stageID does not exist");
		}
		return stageList.get(stageId).getSegmentList().stream().mapToInt(i -> i).toArray();//returns the segments in stage
	}

	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		for (int i = 0; i < teamList.size(); i++) {// Exception throw
			if (teamList.get(i).getTeamName() == name) {
				throw new IllegalNameException("The team name is already in use");
			}
		}
		if ((name.trim() == "") || (name == null)) {// Exception throw
			throw new IllegalNameException("Team names cannot be empty space or null");
		}
		if (name.length() > 30) {// Exception throw
			throw new IllegalNameException("Team names cannot be more then 30 characters long");
		}
		teamList.add(new Team(name, description));// adds new team object to team list
		return teamList.size() - 1; // returns team id
	}

	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		if ((teamId<0) || (teamId >= teamList.size()) || (teamList.get(teamId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This teamID does not exist");
		}
		for (int i = 0; i < teamList.get(teamId).getRiderList().size(); i++) {//sets all rider objects to null within a team
			riderList.set(teamList.get(teamId).getRiderList().get(i), null);
		}
		teamList.set(teamId, null);
	}

	@Override
	public int[] getTeams() {
		ArrayList<Integer> tempTeamIdList = new ArrayList<Integer>();
		for (int i = 0; i < teamList.size(); i++) {//adds all team id's to list
			if (teamList.get(i) != null) {
				tempTeamIdList.add(i);
			}
		}
		return tempTeamIdList.stream().mapToInt(i -> i).toArray(); //returns list of team id's
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		if ((teamId<0) || (teamId >= teamList.size()) || (teamList.get(teamId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This teamID does not exist");
		}
		if (teamList.get(teamId).getRiderList().size() == 0) {
			int[] emptyList = {};
			return emptyList;
		}
		return teamList.get(teamId).getRiderList().stream().filter(i -> i != null).mapToInt(i -> i)
				.toArray();//returns list of rider id's within a team
	}

	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		if ((teamID<0) || (teamID >= teamList.size()) || (teamList.get(teamID) == null)) {// Exception throw
			throw new IDNotRecognisedException("This teamID does not exist");
		}
		if ((name == null) || (yearOfBirth < 1900)) {// Exception throw
			throw new IllegalArgumentException("A riders name cannot be empty and cannot be born before 1900");
		}
		riderList.add(new Rider(teamID, name, yearOfBirth));//adds new rider object to rider list
		teamList.get(teamID).addRider(riderList.size() - 1);//adds rider id to team rider list
		return riderList.size() - 1;//returns rider id
	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		if ((riderId<0) || (riderId >= riderList.size()) || (riderList.get(riderId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This riderID does not exist");
		}
		teamList.get(riderList.get(riderId).getTeamID()).setRiderList(riderId);//removes the rider id from team list
		riderList.set(riderId, null);// sets rider object to null

	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException, 
			InvalidStageStateException {
		if ((riderId<0) || (riderId >= riderList.size()) || (riderList.get(riderId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This riderID does not exist");
		}
		for(int i =0 ; i<stageList.get(stageId).getStageResults().size();i++) {
			if((Integer)stageList.get(stageId).getStageResults().get(i).get(1) == riderId) {// Exception throw
				throw new DuplicatedResultException("This rider already has results stored");
			}
		}
		if(stageList.get(stageId).getSegmentList().size()+2!=checkpoints.length){// Exception throw
			throw new InvalidCheckpointsException("The number of checkpoints inputted is not correct");
		}
		if (stageList.get(stageId).getIsConcluded() == false) {// Exception throw
			throw new InvalidStageStateException("Segments cannot be added to stages waiting for results");
		}
		if (stageList.get(stageId).getIsConcluded() == true) {//runs if the stage had been concluded
			if (stageList.get(stageId).getStageResults().size() == 0) {//adds to the result list if its empty
				ArrayList<Object> tempStore =  new ArrayList<>();
				tempStore.add(checkpoints);
				tempStore.add(riderId);
				stageList.get(stageId).getStageResults().add(tempStore);
				return;
			} else {
				LocalTime tempCheckpointsElapsedTime = LocalTime.of(
						checkpoints[checkpoints.length - 1].getHour() - checkpoints[0].getHour(),
						checkpoints[checkpoints.length - 1].getMinute() - checkpoints[0].getMinute(),
						checkpoints[checkpoints.length - 1].getSecond() - checkpoints[0].getSecond(),
						checkpoints[checkpoints.length - 1].getNano() - checkpoints[0].getNano());// calculates the elapsed time
				for (int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {//sorts results by elapsed time 
					LocalTime[] tempCheckpoints = (LocalTime[])stageList.get(stageId).getStageResults().get(i).get(0);
					LocalTime tempElapsedTime = LocalTime.of(
							tempCheckpoints[tempCheckpoints.length - 1].getHour() - tempCheckpoints[0].getHour(),
							tempCheckpoints[tempCheckpoints.length - 1].getMinute() - tempCheckpoints[0].getMinute(),
							tempCheckpoints[tempCheckpoints.length - 1].getSecond() - tempCheckpoints[0].getSecond(),
							tempCheckpoints[tempCheckpoints.length - 1].getNano() - tempCheckpoints[0].getNano());
					if (tempCheckpointsElapsedTime.isAfter(tempElapsedTime) == false) {
						ArrayList<Object> tempArrayList = new ArrayList<>();
						tempArrayList.add(checkpoints);
						tempArrayList.add(riderId);
						stageList.get(stageId).getStageResults().add(i, tempArrayList);
						return;
					}
				}
			}ArrayList<Object> tempArrayList = new ArrayList<>();
			tempArrayList.add(checkpoints);
			tempArrayList.add(riderId);
			stageList.get(stageId).getStageResults().add(tempArrayList);
		} 

	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		if ((stageId<0) || (stageId >= stageList.size()) || (stageList.get(stageId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This stageID does not exist");
		}
		if ((riderId<0) || (riderId >= riderList.size()) || (riderList.get(riderId) == null)) {// Exception throw
			throw new IDNotRecognisedException("This riderID does not exist");
		}
		LocalTime[] riderResults = {};
		for (int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {//loops through results list until correct riders results found
			if ((Integer) stageList.get(stageId).getStageResults().get(i).get(1) == riderId) {
				LocalTime[] tempResultsStore = (LocalTime[]) stageList.get(stageId).getStageResults().get(i).get(0);
				riderResults = Arrays.copyOf(tempResultsStore, tempResultsStore.length+1);//creates a copy of results list with an extra element on the end which is elapsed time
				riderResults[riderResults.length-1] = LocalTime.of(
						tempResultsStore[tempResultsStore.length-1].getHour() - tempResultsStore[0].getHour(),
						tempResultsStore[tempResultsStore.length-1].getMinute() - tempResultsStore[0].getMinute(),
						tempResultsStore[tempResultsStore.length-1].getSecond() - tempResultsStore[0].getSecond(),
						tempResultsStore[tempResultsStore.length-1].getNano() - tempResultsStore[0].getNano());
				break;
			}
		}
		return riderResults;//return elapsed time
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		if ((stageId<0) || (stageId >= stageList.size()) || (stageList.get(stageId) == null)) {
			throw new IDNotRecognisedException("This stageID does not exist");
		}
		if ((riderId<0) || (riderId >= riderList.size()) || (riderList.get(riderId) == null)) {
			throw new IDNotRecognisedException("This riderID does not exist");
		}
		for (int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {
			if ((Integer) stageList.get(stageId).getStageResults().get(i).get(1) == riderId) {
				break;
			} else if (i == stageList.get(stageId).getStageResults().size() - 1) {
				return null;
			}

		}
		
		int positionInStageResults = 0;
		for (int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {
			if ((Integer) stageList.get(stageId).getStageResults().get(i).get(1) == riderId) {
				positionInStageResults = i;
				break;
			}
		}

		LocalTime[] currentElapsedTime = (LocalTime[]) stageList.get(stageId).getStageResults().get(positionInStageResults).get(0);
		for (int i = positionInStageResults; i > 0; i--) {
			LocalTime[] checkpointsCurrent = (LocalTime[]) stageList.get(stageId).getStageResults().get(i).get(0);
			LocalTime[] checkpointsBelow = (LocalTime[]) stageList.get(stageId).getStageResults().get(i - 1).get(0);
			if (checkpointsCurrent[checkpointsCurrent.length - 1].minusSeconds(1)
					.isBefore(checkpointsBelow[checkpointsBelow.length - 1])) {
				currentElapsedTime = checkpointsBelow;
			} else {
				break;
			}

		}
		return LocalTime.of(
				currentElapsedTime[currentElapsedTime.length - 1].getHour() - currentElapsedTime[0].getHour(),
				currentElapsedTime[currentElapsedTime.length - 1].getMinute() - currentElapsedTime[0].getMinute(),
				currentElapsedTime[currentElapsedTime.length - 1].getSecond() - currentElapsedTime[0].getSecond(),
				currentElapsedTime[currentElapsedTime.length - 1].getNano() - currentElapsedTime[0].getNano());
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		if ((stageId<0) || (stageId >= stageList.size()) || (stageList.get(stageId) == null)) {
			throw new IDNotRecognisedException("This stageID does not exist");
		}
		if ((riderId<0) || (riderId >= riderList.size()) || (riderList.get(riderId) == null)) {
			throw new IDNotRecognisedException("This riderID does not exist");
		}
		for (int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {
			if ((Integer) stageList.get(stageId).getStageResults().get(i).get(1) == riderId) {
				stageList.get(stageId).getStageResults().remove(i);
				break;
			}
		}

	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		int[] ridersRanked;
		if ((stageId<0) || (stageId >= stageList.size()) || (stageList.get(stageId) == null)) {
			throw new IDNotRecognisedException("This stageID does not exist");
		}
		if (stageList.get(stageId).getStageResults().size() != 0) {
			ridersRanked = new int[stageList.get(stageId).getStageResults().size()];	
			for (int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {
				ridersRanked[i] = (Integer) stageList.get(stageId).getStageResults().get(i).get(1);
			}
		} else {
			return new int[0];
		}

		return ridersRanked;

	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		LocalTime[] rankedElapsedTime;
		if (((stageId<0) || stageId >= stageList.size()) || (stageList.get(stageId) == null)) {
			throw new IDNotRecognisedException("This stageID does not exist");
		}
		if (stageList.get(stageId).getStageResults().size() != 0) {
			rankedElapsedTime = new LocalTime[stageList.get(stageId).getStageResults().size()];
			for (int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {
				rankedElapsedTime[i] = getRiderAdjustedElapsedTimeInStage(stageId,
						(Integer) stageList.get(stageId).getStageResults().get(i).get(1));
			}
		} else {
			return new LocalTime[0];
		}
		return rankedElapsedTime;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		if ((stageId<0) || (stageId >= stageList.size()) || (stageList.get(stageId) == null)) {
			throw new IDNotRecognisedException("This stageID does not exist");
		}
		
		if(stageList.get(stageId).getStageResults().size() != 0) {
			int[] riderPosition = getRidersRankInStage(stageId);
			int[] ridersPointsInStage = new int[stageList.get(stageId).getStageResults().size()];
			if (stageList.get(stageId).getStageResults().size() != 0)
				if (stageList.get(stageId).getStageType() == StageType.TT) {
					for (int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {
						ridersPointsInStage[i] = TTSTAGESCORE[i];
					}
					return ridersPointsInStage;
				} else {
					for (int j = 1; j < (((LocalTime[]) stageList.get(stageId).getStageResults().get(0).get(0)).length)-2; j++) {
						ArrayList<ArrayList<Object>> tempSegSort = new ArrayList<>();
						if (segmentList.get(stageList.get(stageId).getSegmentList().get(j))
								.getType() == SegmentType.SPRINT) {
							if (tempSegSort.size() == 0) {
								tempSegSort.get(0).add(0, stageList.get(stageId).getStageResults().get(0).get(0));
								tempSegSort.get(0).add(1, stageList.get(stageId).getStageResults().get(0).get(1));
							} else {
								for (int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {
									for (int z = 0; z < tempSegSort.size(); z++) {
										LocalTime[] tempCurrentSort = (LocalTime[]) stageList.get(stageId).getStageResults()
												.get(i).get(0);
										LocalTime[] tempListSort = (LocalTime[]) tempSegSort.get(z).get(0);
										if (((LocalTime) (tempListSort[i]))
												.isBefore((LocalTime) tempCurrentSort[i]) == true) {
											tempSegSort.get(z).add(0,
													stageList.get(stageId).getStageResults().get(j).get(0));
											tempSegSort.get(z).add(1,
													stageList.get(stageId).getStageResults().get(j).get(1));
											break;
										}
									}
								}
							}
							int[] tempRidersRanked = new int[tempSegSort.size()];
							for (int i = 0; i < tempSegSort.size(); i++) {
								tempRidersRanked[i] = (Integer) tempSegSort.get(i).get(1);
							}
							for (int i = 0; i < tempRidersRanked.length; i++) {
								ridersPointsInStage[i] += SPRINTSEGMENTSCORE[Arrays.binarySearch(tempRidersRanked,
										riderPosition[i])];
							}
						}
					}
					if (stageList.get(stageId).getStageType() == StageType.FLAT) {
						for (int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {
							if (i < FLATSTAGESCORE.length) {
								ridersPointsInStage[i] += FLATSTAGESCORE[i];
							}
						}
					} else if (stageList.get(stageId).getStageType() == StageType.MEDIUM_MOUNTAIN) {
						for (int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {
							if (i < MEDIUMSTAGESCORE.length) {
								ridersPointsInStage[i] += MEDIUMSTAGESCORE[i];
							}
						}
					} else if (stageList.get(stageId).getStageType() == StageType.HIGH_MOUNTAIN) {
						for (int i = 0; i < stageList.get(stageId).getStageResults().size(); i++) {
							if (i < HIGHSTAGESCORE.length-1) {
								ridersPointsInStage[i] += HIGHSTAGESCORE[i];
							}
						}
					}
	
				}
			return ridersPointsInStage;
		}
	return new int[0];
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException { 
		if ((stageId<0) || (stageId >= stageList.size()) || (stageList.get(stageId) == null)) {
			throw new IDNotRecognisedException("This stageID does not exist");
		}
		int[] riderPosition = getRidersRankInStage(stageId);
		int[] ridersMountainPointsInStage = new int[stageList.get(stageId).getStageResults().size()];
		ArrayList<ArrayList<Integer>> tempStageResults = new ArrayList<>();
		for(int i = 0 ; i < riderPosition.length; i++) {
				ArrayList<Integer> tempArray = new ArrayList<>();
				tempArray.add(0);
				tempArray.add(riderPosition[i]);
				tempStageResults.add(tempArray);
		}
		
		
		for(int i = 0;  i<stageList.get(stageId).getSegmentList().size();i++) { // sorts per segment
			ArrayList<ArrayList<Object>> tempSegmentSort =  new ArrayList<>();
			for(int j = 0 ;  j<stageList.get(stageId).getStageResults().size() ; j++) {
				if(tempSegmentSort.size() == 0) {
					ArrayList<Object> tempArray = new ArrayList<>();
					tempArray.add((((LocalTime[])stageList.get(stageId).getStageResults().get(j).get(0)))[i]);
					tempArray.add(stageList.get(stageId).getStageResults().get(j).get(1));
					tempSegmentSort.add(tempArray);
					continue;
				}
				for(int z = 0; z < tempSegmentSort.size(); z++) {
					if(((LocalTime)tempSegmentSort.get(z).get(0)).isAfter((((LocalTime[])stageList.get(stageId).getStageResults().get(j).get(0)))[i]) == true) {
						ArrayList<Object> tempArray = new ArrayList<>();
						tempArray.add((((LocalTime[])stageList.get(stageId).getStageResults().get(j).get(0)))[i]);
						tempArray.add(stageList.get(stageId).getStageResults().get(j).get(1));
						tempSegmentSort.add(z,tempArray);
						break;
					}else if(z == tempSegmentSort.size()-1){
						ArrayList<Object> tempArray = new ArrayList<>();
						tempArray.add((((LocalTime[])stageList.get(stageId).getStageResults().get(j).get(0)))[i]);
						tempArray.add(stageList.get(stageId).getStageResults().get(j).get(1));
						tempSegmentSort.add(tempArray);
						break;
						}
				
				}
			}
			int[] tempRiderSorted = new int[riderPosition.length];
			for(int j = 0; j< tempSegmentSort.size(); j++) {
				tempRiderSorted[j] = (Integer)tempSegmentSort.get(j).get(1);
			}
			ArrayList<Integer> tempStoreSegmentList = stageList.get(stageId).getSegmentList();
			for(int k = 0 ; k< tempRiderSorted.length; k++) {
				for(int z = 0; z< tempStageResults.size(); z++) {
					if(tempRiderSorted[k] ==  tempStageResults.get(z).get(1)) {
						if(segmentList.get(tempStoreSegmentList.get(i)).getType() == SegmentType.C4 ) {
							if(k< FOURCSCORE.length) {
								tempStageResults.get(z).set(0, tempStageResults.get(z).get(0) + FOURCSCORE[k]);
								break;
							}
						}else if(segmentList.get(tempStoreSegmentList.get(i)).getType() == SegmentType.C3 ) {
							if(k< THREECSCORE.length) {
								tempStageResults.get(z).set(0, tempStageResults.get(z).get(0) + THREECSCORE[k]);
								break;
							}
						}else if(segmentList.get(tempStoreSegmentList.get(i)).getType() == SegmentType.C2 ) {
							if(k< TWOCSCORE.length) {
								tempStageResults.get(z).set(0, tempStageResults.get(z).get(0) + TWOCSCORE[k]);
								break;
							}
						}else if(segmentList.get(tempStoreSegmentList.get(i)).getType() == SegmentType.C1 ) {
							if(k< ONECSCORE.length) {
								tempStageResults.get(z).set(0, tempStageResults.get(z).get(0) + ONECSCORE[k]);
								break;
							}
						}else if(segmentList.get(tempStoreSegmentList.get(i)).getType() == SegmentType.HC ) {
							if(k< HCSCORE.length) {
								tempStageResults.get(z).set(0, tempStageResults.get(z).get(0) + HCSCORE[k]);
								break;
							}
						}
					}
				}
			}
			
		}
		for(int i = 0 ; i<tempStageResults.size(); i++) {
			ridersMountainPointsInStage[i] = tempStageResults.get(i).get(0);
		}

		return ridersMountainPointsInStage;
	}

	@Override
	public void eraseCyclingPortal() {
		teamList = new ArrayList<Team>();
		riderList = new ArrayList<Rider>();
		raceList = new ArrayList<Race>();
		stageList = new ArrayList<Stage>();
		segmentList = new ArrayList<>();
	}

	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		try{
            FileOutputStream writeData = new FileOutputStream(filename+".ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(teamList);
            writeStream.writeObject(riderList);
            writeStream.writeObject(raceList);
            writeStream.writeObject(stageList);
            writeStream.writeObject(segmentList);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }

	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		try{
            FileInputStream readData = new FileInputStream(filename+".ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            teamList = (ArrayList<Team>) readStream.readObject();
            riderList = (ArrayList<Rider>) readStream.readObject();
            raceList = (ArrayList<Race>) readStream.readObject();
            stageList = (ArrayList<Stage>) readStream.readObject();
            segmentList = (ArrayList<Segment>) readStream.readObject();
            readStream.close();

  
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

	}

	@Override
	public void removeRaceByName(String name) throws NameNotRecognisedException { 
		
		for(int i=0;i<raceList.size();i++)
		{
			if(raceList.get(i)!=null)
			{
				if(raceList.get(i).getRaceName()==name)
				{
					try {
						removeRaceById(i);
					} catch (IDNotRecognisedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}
			}
		}
		throw new NameNotRecognisedException("This name is not in use");
	}

	@Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
		if ((raceId<0) ||(raceId >= raceList.size()) || (raceList.get(raceId) == null)) {
			throw new IDNotRecognisedException("This raceID does not exist");
		}
		if (raceList.get(raceId).getStageList().size() != 0) {
			ArrayList<ArrayList<Object>> sortedGeneralTimes =  new ArrayList<>();
			ArrayList<ArrayList<Object>> totalGeneralTimes =  new ArrayList<>();
			for(int i = 0 ; i<raceList.get(raceId).getStageList().size(); i++) {
				int[] stageSortedRiders = getRidersRankInStage(raceList.get(raceId).getStageList().get(i));
				LocalTime[] stageAdjustedElapsedTimes = getRankedAdjustedElapsedTimesInStage(raceList.get(raceId).getStageList().get(i));
				for(int j = 0; j< stageSortedRiders.length; j++) {
					if(totalGeneralTimes.size() == 0) {
						for(int k = 0; k< stageSortedRiders.length; k++) {
								ArrayList<Object> tempStore = new ArrayList<>();
								tempStore.add(stageAdjustedElapsedTimes[k]);
								tempStore.add(stageSortedRiders[k]);
								totalGeneralTimes.add(tempStore);
						}
					}else {
						for(int k = 0; k<totalGeneralTimes.size(); k++ ) {
							if(stageSortedRiders[j] == (Integer)totalGeneralTimes.get(k).get(1)) {
								int totalNano;
								int totalSeconds = 0;
								int totalMinutes = 0;
								int totalHours = 0;
								totalNano = stageAdjustedElapsedTimes[j].getNano() + ((LocalTime)totalGeneralTimes.get(k).get(0)).getNano();
								if(totalNano > 999999999) {
									while(totalNano > 999999999) {
										totalNano -= 1000000000;
										totalSeconds += 1;
									}
								}
								totalSeconds += stageAdjustedElapsedTimes[j].getSecond() + ((LocalTime)totalGeneralTimes.get(k).get(0)).getSecond();
								if(totalSeconds > 59) {
									while(totalSeconds > 59) {
										totalSeconds -= 60;
										totalMinutes += 1;
									}
								}
								totalMinutes += stageAdjustedElapsedTimes[j].getMinute() + ((LocalTime)totalGeneralTimes.get(k).get(0)).getMinute();
								if(totalMinutes > 59) {
									while(totalMinutes > 59) {
										totalMinutes -= 60;
										totalHours += 1;
									}
								}
								totalHours += stageAdjustedElapsedTimes[j].getHour() + ((LocalTime)totalGeneralTimes.get(k).get(0)).getHour();
								LocalTime newTotalElapsedTime = LocalTime.of(totalHours, totalMinutes, totalSeconds, totalNano);
								totalGeneralTimes.get(k).set(0, newTotalElapsedTime);
							}
						}
					}
					
				}
			}
		for(int i = 0; i<totalGeneralTimes.size();i++) {
			if(sortedGeneralTimes.size() == 0) {
				ArrayList<Object> tempStore = new ArrayList<>();
				tempStore.add(totalGeneralTimes.get(0).get(0));
				tempStore.add(totalGeneralTimes.get(0).get(1));
				sortedGeneralTimes.add(tempStore);
				continue;
			}
			for(int j = 0 ; j<sortedGeneralTimes.size();j++) {
				if(((LocalTime)sortedGeneralTimes.get(j).get(0)).isAfter((LocalTime)totalGeneralTimes.get(i).get(0)) == false) {
					ArrayList<Object> tempStore = new ArrayList<>();
					tempStore.add(totalGeneralTimes.get(i).get(0));
					tempStore.add(totalGeneralTimes.get(i).get(1));
					sortedGeneralTimes.add(j,tempStore);
					break;
				}
				if(j == (sortedGeneralTimes.size())) {
					ArrayList<Object> tempStore = new ArrayList<>();
					tempStore.add(totalGeneralTimes.get(i).get(0));
					tempStore.add(totalGeneralTimes.get(i).get(1));
					sortedGeneralTimes.add(tempStore);
				}
			}
		}
		LocalTime[] generalClassficationTimes = new LocalTime[sortedGeneralTimes.size()];
		for(int i = 0 ; i < sortedGeneralTimes.size(); i++) {
			generalClassficationTimes[i] = (LocalTime)sortedGeneralTimes.get(i).get(0);
		}
		return generalClassficationTimes;
		}
		return new LocalTime[0];
	}

	@Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
		if ((raceId<0) ||(raceId >= raceList.size()) || (raceList.get(raceId) == null)) {
			throw new IDNotRecognisedException("This raceID does not exist");
		}
		if (raceList.get(raceId).getStageList().size() != 0) {
			ArrayList<ArrayList<Integer>> tempTotalPoints = new ArrayList<>();
			int[] returnOrder = getRidersGeneralClassificationRank(raceId);
			int[] sortedTotalPoints = new int[returnOrder.length];
			for(int i = 0; i< raceList.get(raceId).getStageList().size(); i++){
				int[] tempStagePoints = getRidersPointsInStage(raceList.get(raceId).getStageList().get(i));
				int[] tempRaceIds = getRidersRankInStage(raceList.get(raceId).getStageList().get(i));
				if(tempTotalPoints.size() == 0) {
					for(int j = 0 ; j< tempRaceIds.length; j++) {
						ArrayList<Integer> tempStore = new ArrayList<>();
						tempStore.add(tempStagePoints[j]);
						tempStore.add(tempRaceIds[j]);
						tempTotalPoints.add(tempStore);
					}
				}else {
					for(int j = 0; j< tempTotalPoints.size(); j++) {
						for(int k = 0; k< tempRaceIds.length ; k++) {
							if(tempRaceIds[k] == tempTotalPoints.get(j).get(1)) {
								tempTotalPoints.get(j).set(0, tempTotalPoints.get(j).get(0) + tempStagePoints[k]);
								break;
							}
						}
					}
				}
			}
			for(int i = 0; i<tempTotalPoints.size(); i++) {
				for(int j = 0; j< returnOrder.length; j++) {
					if(tempTotalPoints.get(i).get(1) == returnOrder[j]) {
						sortedTotalPoints[j] = tempTotalPoints.get(i).get(0);
					}
				}
			}
			return sortedTotalPoints;
		}
		return new int[0];
	}

	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		if ((raceId<0) ||(raceId >= raceList.size()) || (raceList.get(raceId) == null)) {
			throw new IDNotRecognisedException("This raceID does not exist");
		}
		if (raceList.get(raceId).getStageList().size() != 0) {
			ArrayList<ArrayList<Integer>> tempTotalPoints = new ArrayList<>();
			int[] returnOrder = getRidersGeneralClassificationRank(raceId);
			int[] sortedTotalPoints = new int[returnOrder.length];
			for(int i = 0; i< raceList.get(raceId).getStageList().size(); i++){
				int[] tempStagePoints = getRidersMountainPointsInStage(raceList.get(raceId).getStageList().get(i));
				int[] tempRaceIds = getRidersRankInStage(raceList.get(raceId).getStageList().get(i));
				if(tempTotalPoints.size() == 0) {
					for(int j = 0 ; j< tempRaceIds.length; j++) {
						ArrayList<Integer> tempStore = new ArrayList<>();
						tempStore.add(tempStagePoints[j]);
						tempStore.add(tempRaceIds[j]);
						tempTotalPoints.add(tempStore);
					}
				}else {
					for(int j = 0; j< tempTotalPoints.size(); j++) {
						for(int k = 0; k< tempRaceIds.length ; k++) {
							if(tempRaceIds[k] == tempTotalPoints.get(j).get(1)) {
								tempTotalPoints.get(j).set(0, tempTotalPoints.get(j).get(0) + tempStagePoints[k]);
								break;
							}
						}
					}
				}
			}
			for(int i = 0; i<tempTotalPoints.size(); i++) {
				for(int j = 0; j< returnOrder.length; j++) {
					if(tempTotalPoints.get(i).get(1) == returnOrder[j]) {
						sortedTotalPoints[j] = tempTotalPoints.get(i).get(0);
					}
				}
			}
			return sortedTotalPoints;
		}
		return new int[0];
	}

	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException { 
		if ((raceId<0) || (raceId >= raceList.size()) || (raceList.get(raceId) == null)) {
			throw new IDNotRecognisedException("This raceID does not exist");
		}
		if (raceList.get(raceId).getStageList().size() != 0) {
			ArrayList<ArrayList<Object>> sortedGeneralTimes =  new ArrayList<>();
			ArrayList<ArrayList<Object>> totalGeneralTimes =  new ArrayList<>();
			for(int i = 0 ; i<raceList.get(raceId).getStageList().size(); i++) {
				int[] stageSortedRiders = getRidersRankInStage(raceList.get(raceId).getStageList().get(i));
				LocalTime[] stageAdjustedElapsedTimes = getRankedAdjustedElapsedTimesInStage(raceList.get(raceId).getStageList().get(i));
				for(int j = 0; j< stageSortedRiders.length; j++) {
					if(totalGeneralTimes.size() == 0) {
						for(int k = 0; k< stageSortedRiders.length; k++) {
								ArrayList<Object> tempStore = new ArrayList<>();
								tempStore.add(stageAdjustedElapsedTimes[k]);
								tempStore.add(stageSortedRiders[k]);
								totalGeneralTimes.add(tempStore);
						}
					}else {
						for(int k = 0; k<totalGeneralTimes.size(); k++ ) {
							if(stageSortedRiders[j] == (Integer)totalGeneralTimes.get(k).get(1)) {
								int totalNano;
								int totalSeconds = 0;
								int totalMinutes = 0;
								int totalHours = 0;
								totalNano = stageAdjustedElapsedTimes[j].getNano() + ((LocalTime)totalGeneralTimes.get(k).get(0)).getNano();
								if(totalNano > 999999999) {
									while(totalNano > 999999999) {
										totalNano -= 1000000000;
										totalSeconds += 1;
									}
								}
								totalSeconds += stageAdjustedElapsedTimes[j].getSecond() + ((LocalTime)totalGeneralTimes.get(k).get(0)).getSecond();
								if(totalSeconds > 59) {
									while(totalSeconds > 59) {
										totalSeconds -= 60;
										totalMinutes += 1;
									}
								}
								totalMinutes += stageAdjustedElapsedTimes[j].getMinute() + ((LocalTime)totalGeneralTimes.get(k).get(0)).getMinute();
								if(totalMinutes > 59) {
									while(totalMinutes > 59) {
										totalMinutes -= 60;
										totalHours += 1;
									}
								}
								totalHours += stageAdjustedElapsedTimes[j].getHour() + ((LocalTime)totalGeneralTimes.get(k).get(0)).getHour();
								LocalTime newTotalElapsedTime = LocalTime.of(totalHours, totalMinutes, totalSeconds, totalNano);
								totalGeneralTimes.get(k).set(0, newTotalElapsedTime);
							}
						}
					}
					
				}
			}
		for(int i = 0; i<totalGeneralTimes.size();i++) {
			if(sortedGeneralTimes.size() == 0) {
				ArrayList<Object> tempStore = new ArrayList<>();
				tempStore.add(totalGeneralTimes.get(0).get(0));
				tempStore.add(totalGeneralTimes.get(0).get(1));
				sortedGeneralTimes.add(tempStore);
				continue;
			}
			for(int j = 0 ; j<sortedGeneralTimes.size();j++) {
				if(((LocalTime)sortedGeneralTimes.get(j).get(0)).isAfter((LocalTime)totalGeneralTimes.get(i).get(0)) == false) {
					ArrayList<Object> tempStore = new ArrayList<>();
					tempStore.add(totalGeneralTimes.get(i).get(0));
					tempStore.add(totalGeneralTimes.get(i).get(1));
					sortedGeneralTimes.add(j,tempStore);
					break;
				}
				if(j == (sortedGeneralTimes.size())) {
					ArrayList<Object> tempStore = new ArrayList<>();
					tempStore.add(totalGeneralTimes.get(i).get(0));
					tempStore.add(totalGeneralTimes.get(i).get(1));
					sortedGeneralTimes.add(tempStore);
				}
			}
		}
		int[] generalClassficationRanks =  new int[sortedGeneralTimes.size()];
		for(int i = 0 ; i < sortedGeneralTimes.size(); i++) {
			generalClassficationRanks[i] = (Integer)sortedGeneralTimes.get(i).get(1);
		}
		return generalClassficationRanks;
		}
		return new int[0];
	}

	@Override
	public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
		if ((raceId<0) ||(raceId >= raceList.size()) || (raceList.get(raceId) == null)) {
			throw new IDNotRecognisedException("This raceID does not exist");
		}
		if (raceList.get(raceId).getStageList().size() != 0) {
			ArrayList<ArrayList<Integer>> tempTotalPoints = new ArrayList<>();
			ArrayList<ArrayList<Integer>> sortedTotalPoints = new ArrayList<>();
			int[] riderRank= new int[stageList.get(raceList.get(raceId).getStageList().get(0)).getStageResults().size()];
			for(int i = 0; i< raceList.get(raceId).getStageList().size(); i++){
				int[] tempStagePoints = getRidersPointsInStage(raceList.get(raceId).getStageList().get(i));
				int[] tempRaceIds = getRidersRankInStage(raceList.get(raceId).getStageList().get(i));
				if(tempTotalPoints.size() == 0) {
					for(int j = 0 ; j< tempRaceIds.length; j++) {
						ArrayList<Integer> tempStore = new ArrayList<>();
						tempStore.add(tempStagePoints[j]);
						tempStore.add(tempRaceIds[j]);
						tempTotalPoints.add(tempStore);
					}
				}else {
					for(int j = 0; j< tempTotalPoints.size(); j++) {
						for(int k = 0; k< tempRaceIds.length ; k++) {
							if(tempRaceIds[k] == tempTotalPoints.get(j).get(1)) {
								tempTotalPoints.get(j).set(0, tempTotalPoints.get(j).get(0) + tempStagePoints[k]);
								break;
							}
						}
					}
				}
			}
			for(int a = 0; a< tempTotalPoints.size();a++) {
				if(sortedTotalPoints.size() == 0 ) {
					sortedTotalPoints.add(tempTotalPoints.get(0));
					continue;
				}
				for(int j = 0; j< sortedTotalPoints.size();j++) {
					if(sortedTotalPoints.get(j).get(0) < tempTotalPoints.get(a).get(0)) {
				
						sortedTotalPoints.add(j,tempTotalPoints.get(a));
						break;
					}else if(j == sortedTotalPoints.size()-1){
						sortedTotalPoints.add(tempTotalPoints.get(a));
						break;
					}
				}
			}
			for(int x = 0 ; x< sortedTotalPoints.size(); x++) {
				riderRank[x] = sortedTotalPoints.get(x).get(1);
			}
			return riderRank;
			}
		return new int[0];
	}
	

	@Override
	public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
		if ((raceId<0) ||(raceId >= raceList.size()) || (raceList.get(raceId) == null)) {
			throw new IDNotRecognisedException("This raceID does not exist");
		}
		if (raceList.get(raceId).getStageList().size() != 0) {
			ArrayList<ArrayList<Integer>> tempTotalPoints = new ArrayList<>();
			ArrayList<ArrayList<Integer>> sortedTotalPoints = new ArrayList<>();
			int[] riderRank= new int[stageList.get(raceList.get(raceId).getStageList().get(0)).getStageResults().size()];
			for(int i = 0; i< raceList.get(raceId).getStageList().size(); i++){
				int[] tempStagePoints = getRidersMountainPointsInStage(raceList.get(raceId).getStageList().get(i));
				int[] tempRaceIds = getRidersRankInStage(raceList.get(raceId).getStageList().get(i));
				if(tempTotalPoints.size() == 0) {
					for(int j = 0 ; j< tempRaceIds.length; j++) {
						ArrayList<Integer> tempStore = new ArrayList<>();
						tempStore.add(tempStagePoints[j]);
						tempStore.add(tempRaceIds[j]);
						tempTotalPoints.add(tempStore);
					}
				}else {
					for(int j = 0; j< tempTotalPoints.size(); j++) {
						for(int k = 0; k< tempRaceIds.length ; k++) {
							if(tempRaceIds[k] == tempTotalPoints.get(j).get(1)) {
								tempTotalPoints.get(j).set(0, tempTotalPoints.get(j).get(0) + tempStagePoints[k]);
								break;
							}
						}
					}
				}
			}
			for(int a = 0; a< tempTotalPoints.size();a++) {
				if(sortedTotalPoints.size() == 0 ) {
					sortedTotalPoints.add(tempTotalPoints.get(0));
					continue;
				}
				for(int j = 0; j< sortedTotalPoints.size();j++) {
					if(sortedTotalPoints.get(j).get(0) < tempTotalPoints.get(a).get(0)) {
				
						sortedTotalPoints.add(j,tempTotalPoints.get(a));
						break;
					}else if(j == sortedTotalPoints.size()-1){
						sortedTotalPoints.add(tempTotalPoints.get(a));
						break;
					}
				}
			}
			for(int x = 0 ; x< sortedTotalPoints.size(); x++) {
				riderRank[x] = sortedTotalPoints.get(x).get(1);
			}
			return riderRank;
			}
		return new int[0];
	}

}