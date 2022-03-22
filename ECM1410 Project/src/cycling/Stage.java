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
 * @author 700027589 and 710019499
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
	 *  @return the raceID
	 */
	public int getRaceId() {
		return raceId;
	}
	
	/**
	 * The stage name
	 * @return nothing
	 */
	private String stageName;
	
	/**
	 *  returns the name of a single stage
	 *  @return the name of the stage
	 */
	public String getStageName() {
		return stageName;
	}
	
	/**
	 * The stage description
	 * @return nothing
	 */
	private String stageDescription;
	
	/**
	 *  returns the description of a single stage
	 *  @return the description of the stage
	 */
	public String getStageDescription() {
		return stageDescription;
	}
	
	/**
	 * The stage length
	 * @return nothing
	 */
	private double stageLength;
	
	/**
	 *  returns the length (measured in kilometres) of a single stage
	 *  @return the stage length
	 */
	public double getStageLength() {
		return stageLength;
	}
	
	/**
	 * The stages start time
	 * @return nothing
	 */
	private LocalDateTime stageStartTime;
	
	/**
	 *  returns the start time of a single stage
	 *  @return stage start time
	 */
	public LocalDateTime getStageStartTime() {
		return stageStartTime;
	}
	
	/**
	 * The stage type
	 * @return nothing
	 */
	private StageType stageType;
	
	/**
	 *  returns the type (eg time trial) of a single stage
	 *  @return the type of stage
	 */
	public StageType getStageType() {
		return stageType;
	}

	/**
	 * State of if the stage is concluded
	 * @return nothing
	 */
	private boolean isConcluded;
	
	/**
	 *  gets the whether the stage is in the concluded state or not
	 *  @return if the stage is concluded (true or false)
	 */
	public boolean getIsConcluded() {
		return isConcluded;
	}

	/**
	 *  sets a stage to the concluded state meaning that it can now take rider results
	 *  @param isConcluded: is the stage waiting for results or not?
	 */
	public void setIsConcluded(boolean isConcluded) {
		this.isConcluded = isConcluded;
		this.stageResults = new ArrayList<>();
	}
	
	/**
	 * The constructor for the Stage class
	 * 
	 *  @param raceId: The ID number for the race the stage belongs to
	 *  @param stageName: The name of the stage
	 *  @param stageDescription: the description of the stage
	 *  @param stageLength: The length of the stage measured in kilometres
	 *  @param stageStartTime: The time that the stage starts
	 *  @param stageType: the type of stage (eg FLAT,MEDIUM MOUNTAIN,HIGH MOUNTAIN,or TT)
	 *  @param segmentIdList: A list of ids of segments associated with the stage
	 *  @param setIsConcluded: Whether the stage is ready for results or not
	 *  @return nothing
	*/
	
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
	 *  @return nothing
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
	 *  @return nothing
	 */
	public void setSegmentList(int i , int segmentId) {
		segmentIdList.add(i,segmentId);
	}
	
	public ArrayList<Integer> getSegmentList() {return segmentIdList;}
	
	public ArrayList<ArrayList<Object>> getStageResults(){return stageResults;}
}
