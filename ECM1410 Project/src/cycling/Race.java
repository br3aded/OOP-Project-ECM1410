package cycling;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *  Functions related to races
 * 
 * 
 * @author 700027589 and 710019499
 * @version 1.0
 *
 */


public class Race implements Serializable {
	private ArrayList<Integer> stageIdList;
	
	/**
	 *  adds a stage to a race
	 *  @param stageId: The ID of the stage to be added
	 */
	public void addStage(int stageId) {
		stageIdList.add(stageId);
	}
	
	/**
	 *  removes a stage from a race
	 *  @param stageId: The ID of the stage to be removed
	 */
	public void removeStage(int stageId) {
		for(int i = 0; i< stageIdList.size(); i++) {
			if(stageIdList.get(i) == stageId) {
				stageIdList.remove(i);
				break;
			}
		}
	}
	
	public ArrayList<Integer> getStageList() {return stageIdList;}
	
	/**
	 *  The name of the race
	 */
	private String raceName;
	
	/**
	 *  gets the name of a single race
	 *  @return the name of the race
	 */
	public String getRaceName() {
		return raceName;
	}

	/**
	 *  The description of the race
	 */
	private String raceDescription;
	
	/**
	 *  gets the description of a single race
	 *  @return the descripiton of the race
	 */
	public String getRaceDescription() {
		return raceDescription;
	}

	
	/**
	 *  Constructor for the Race class
	 *  @param raceName: The name of the race
	 *  @param raceDescription: The description of the race
	 *  stageIdList: List of ids of stages associated with the race 
	 */
	public Race(String raceName, String raceDescription) {
		this.raceName = raceName;
		this.raceDescription = raceDescription;
		this.stageIdList = new ArrayList<Integer>();
	}

	/**
	 *  returns the name and description of a single race
	 *  @return A string containing the raceName and raceDescription
	 */
	@Override
	public String toString() {
		return "Race [raceName=" + raceName + ", raceDescription=" + raceDescription + "]";
	}
	
}
