package cycling;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Stage {
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

	public Stage(int raceId, String stageName, String description, double length, LocalDateTime startTime,StageType type) {
		this.raceId = raceId;
		this.stageName = stageName;
		this.stageDescription = description;
		this.stageLength = length;
		this.stageStartTime = startTime;
		this.stageType = type;
	}
	
	
}
