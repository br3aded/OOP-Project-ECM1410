package cycling;

import java.io.Serializable;

/**
 *  Functions related to segments
 * 
 * 
 * @author 700027589 and 710019499
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
	 *  @return The stageID
	 */
	public int getStageId() {
		return stageId;
	}
	/**
	 *  gets the location (where the segment finishes) of a segment
	 *  @return the finishing location of the segment
	 */
	public double getLocation() {
		return location;
	}
	/**
	 *  returns the average gradient of a segment
	 *  @return the average gradient
	 */
	public double getAverageGradient() {
		return averageGradient;
	}

	/**
	 *  returns the length (measured in kilometres) of a segment
	 *  @returns The length of a segment
	 */
	public double getLength() {
		return length;
	}

	/**
	 *  Returns the segment type eg some form hill climb or sprint
	 *  @return the segment type
	 */ 
	public SegmentType getType() {
		return type;
	}

	/**
	 *  Constructor for the sprint segment
	 *  @param stageId: The ID of the stage associated with the segment
	 *  @param location: Where the segment finishes
	 *  @return nothing
	 */
	public Segment(int stageId, double location) {
		this.stageId = stageId;
		this.location = location;
	}
	
	/**
	 *  Constructor for the hill climb segment
	 *  @param stageId: The ID of the stage associated with the segment
	 *  @param location: Where the segment finishes
	 *  @param averageGradient: The average gradient through the segment
	 *  @param The length of the stage measured in kilometres
	 *  @param type: The type of hill climb (HC,C1,C2,C3,or C4)
	 *  @return nothing
	 */
	public Segment(int stageId, double location , double averageGradient ,double length, SegmentType type) {
		this.stageId = stageId;
		this.location = location;
		this.averageGradient = averageGradient;
		this.length = length;
		this.type = type;
	}
	
}
