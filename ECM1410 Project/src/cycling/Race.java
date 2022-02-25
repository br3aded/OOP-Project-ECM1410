package cycling;

public class Race {
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
	}

	@Override
	public String toString() {
		return "Race [raceName=" + raceName + ", raceDescription=" + raceDescription + "]";
	}
	
	

	

}
