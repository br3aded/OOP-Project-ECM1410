package cycling;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Race {
	private ArrayList<Integer> stageIdList;
	
	public void addStage(int stageId) {
		stageIdList.add(stageId);
	}
	
	public void setStageList(int stageId) {
		for(int i = 0; i< stageIdList.size(); i++) {
			if(stageIdList.get(i) == stageId) {
				stageIdList.set(i, null);
				break;
			}
		}
	}
	
	public ArrayList<Integer> getStageList() {return stageIdList;}
	
	private String raceName;
	
	public String getRaceName() {
		return raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}
	
	private String raceDescription;
	
	public String getRaceDescription() {
		return raceDescription;
	}

	public void setRaceDescription(String raceDescription) {
		this.raceDescription = raceDescription;
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
