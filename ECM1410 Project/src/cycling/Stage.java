package cycling;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Stage {
	ArrayList<Integer> segmentIdList;
	ArrayList<LocalTime[]> stageResults;
	
	private int raceId;
	
	public int getRaceId() {
		return raceId;
	}
	
	private String stageName;
	
	public String getStageName() {
		return stageName;
	}
	
	private String stageDescription;
	
	public String getStageDescription() {
		return stageDescription;
	}
	
	private double stageLength;
	
	public double getStageLength() {
		return stageLength;
	}
	
	private LocalDateTime stageStartTime;
	
	public LocalDateTime getStageStartTime() {
		return stageStartTime;
	}
	
	private StageType stageType;
	
	public StageType getStageType() {
		return stageType;
	}

	private boolean isConcluded;
	
	public boolean getIsConcluded() {
		return isConcluded;
	}

	public void setIsConcluded(boolean isConcluded) {
		this.isConcluded = isConcluded;
	}
	
	public Stage(int raceId, String stageName, String description, double length, LocalDateTime startTime,StageType type) {
		this.raceId = raceId;
		this.stageName = stageName;
		this.stageDescription = description;
		this.stageLength = length;
		this.stageStartTime = startTime;
		this.stageType = type;
		this.segmentIdList = new ArrayList<Integer>();
		this.stageResults = new ArrayList<>();
		this.setIsConcluded(false);
	}
	
	public void removeSegment(int segmentId) {
		for(int i = 0; i< segmentIdList.size(); i++) {
			if(segmentIdList.get(i) == segmentId) {
				segmentIdList.remove(i);
				break;
			}
		}
	}
	
	public ArrayList<Integer> getSegmentList() {return segmentIdList;}
	
	public ArrayList<LocalTime[]> getStageResults(){return stageResults;}
}
