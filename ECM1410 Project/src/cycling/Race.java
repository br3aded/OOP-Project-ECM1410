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
	
	public void addStage(int stageId) {
		stageIdList.add(stageId);
	}
	
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
	
	public String getRaceName() {
		return raceName;
	}

	private String raceDescription;
	
	public String getRaceDescription() {
		return raceDescription;
	}

	public Race(String raceName, String raceDescription) {
		this.raceName = raceName;
		this.raceDescription = raceDescription;
		this.stageIdList = new ArrayList<Integer>();
	}

	@Override
	public String toString() {
		return "Race [raceName=" + raceName + ", raceDescription=" + raceDescription + "]";
	}
	
}
