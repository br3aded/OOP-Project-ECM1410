package cycling;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *  Functions related to races
 * 
 * 
 * @author 700027589 & 710019499
 * @version 1.0
 *
 */


public class Race implements Serializable {
	private ArrayList<Integer> stageIdList;
	
	/**
	 *  adds a stage to a race
	 */
	public void addStage(int stageId) {
		stageIdList.add(stageId);
	}
	
	/**
	 *  removes a stage from a race
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
	
	private String raceName;
	
	/**
	 *  gets the name of a single race
	 */
	public String getRaceName() {
		return raceName;
	}

	private String raceDescription;
	
	/**
	 *  gets the description of a single race
	 */
	public String getRaceDescription() {
		return raceDescription;
	}

	public Race(String raceName, String raceDescription) {
		this.raceName = raceName;
		this.raceDescription = raceDescription;
		this.stageIdList = new ArrayList<Integer>();
	}

	/**
	 *  returns the name and description of a single race
	 */
	@Override
	public String toString() {
		return "Race [raceName=" + raceName + ", raceDescription=" + raceDescription + "]";
	}
	
}
