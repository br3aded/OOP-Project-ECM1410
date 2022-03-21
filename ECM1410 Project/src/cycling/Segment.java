package cycling;

import java.io.Serializable;

/**
 *  Functions related to segments
 * 
 * 
 * @author 700027589 & 710019499
 * @version 1.0
 *
 */

public class Segment implements Serializable {
	private int stageId;
	private double location;
	private double averageGradient;
	private double length;
	private SegmentType type;
	
	public int getStageId() {
		return stageId;
	}

	public double getLocation() {
		return location;
	}
	
	public double getAverageGradient() {
		return averageGradient;
	}

	public double getLength() {
		return length;
	}

	public SegmentType getType() {
		return type;
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
