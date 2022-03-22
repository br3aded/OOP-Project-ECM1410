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
	
	/**
	 *  gets the stageID of the stage associated with a segment
	 */
	public int getStageId() {
		return stageId;
	}
	/**
	 *  gets the location (where the segment finishes) of a segment
	 */
	public double getLocation() {
		return location;
	}
	/**
	 *  returns the average gradient of a segment
	 */
	public double getAverageGradient() {
		return averageGradient;
	}

	/**
	 *  returns the length (measured in kilometres) of a segment
	 */
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
