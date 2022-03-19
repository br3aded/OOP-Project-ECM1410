package cycling;

import java.io.Serializable;

public class Segment implements Serializable {
	private int stageId;
	private double location;
	private double averageGradient;
	private double length;
	private SegmentType type;
	
	public int getStageId() {
		return stageId;
	}
	public void setStageId(int stageId) {
		this.stageId = stageId;
	}
	public double getLocation() {
		return location;
	}
	public void setLocation(double location) {
		this.location = location;
	}
	
	public double getAverageGradient() {
		return averageGradient;
	}
	public void setAverageGradient(double averageGradient) {
		this.averageGradient = averageGradient;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public SegmentType getType() {
		return type;
	}
	public void setType(SegmentType type) {
		this.type = type;
	}
	
	public Segment(int stageId, double location) {
		this.stageId = stageId;
		this.location = location;
	}
	
	public Segment(int stageId, double location , double averageGradient ,double length, SegmentType type) {
		this.stageId = stageId;
		this.location = location;
		this.averageGradient = averageGradient;
		this.length = length;
		this.type = type;
	}
	
}
