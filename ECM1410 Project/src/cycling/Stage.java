package cycling;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


/**
 *  Functions related to stages
 * 
 * 
 * @author 700027589 & 710019499
 * @version 1.0
 *
 */

public class Stage implements Serializable{
	ArrayList<Integer> segmentIdList;
	@SuppressWarnings("rawtypes")
	ArrayList<ArrayList<Object>> stageResults;
	
	private int raceId;
	
	/**
	 *  returns the raceID of the race a stage is associated with
	 */
	public int getRaceId() {
		return raceId;
	}
	
	private String stageName;
	
	/**
	 *  returns the name of a single stage
	 */
	public String getStageName() {
		return stageName;
	}
	
	private String stageDescription;
	
	/**
	 *  returns the description of a single stage
	 */
	public String getStageDescription() {
		return stageDescription;
	}
	
	private double stageLength;
	
	/**
	 *  returns the length (measured in kilometres) of a single stage
	 */
	public double getStageLength() {
		return stageLength;
	}
	
	private LocalDateTime stageStartTime;
	
	/**
	 *  returns the start time of a single stage
	 */
	public LocalDateTime getStageStartTime() {
		return stageStartTime;
	}
	
	private StageType stageType;
	
	/**
	 *  returns the type (eg time trial) of a single stage
	 */
	public StageType getStageType() {
		return stageType;
	}

	private boolean isConcluded;
	
	/**
	 *  gets the whether the stage is in the concluded state or not
	 */
	public boolean getIsConcluded() {
		return isConcluded;
	}

	/**
	 *  sets a stage to the concluded state meaning that it can now take rider results
	 */
	public void setIsConcluded(boolean isConcluded) {
		this.isConcluded = isConcluded;
		this.stageResults = new ArrayList<>();
	}
	
	public Stage(int raceId, String stageName, String description, double length, LocalDateTime startTime,StageType type) {
		this.raceId = raceId;
		this.stageName = stageName;
		this.stageDescription = description;
		this.stageLength = length;
		this.stageStartTime = startTime;
		this.stageType = type;
		this.segmentIdList = new ArrayList<Integer>();
		this.setIsConcluded(false);
	}
	
	/**
	 *  removes a segment from a single stage
	 */
	public void removeSegment(int segmentId) {
		for(int i = 0; i< segmentIdList.size(); i++) {
			if(segmentIdList.get(i) == segmentId) {
				segmentIdList.remove(i);
				break;
			}
		}
	}
	
	/**
	 *  adds a segment to a segment list
	 */
	public void setSegmentList(int i , int segmentId) {
		segmentIdList.add(i,segmentId);
	}
	
	public ArrayList<Integer> getSegmentList() {return segmentIdList;}
	
	public ArrayList<ArrayList<Object>> getStageResults(){return stageResults;}
}
