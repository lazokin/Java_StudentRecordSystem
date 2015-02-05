package model.staff;

import model.staff.interfaces.Staff;

public abstract class Position {

    protected PositionType positionType;
    protected Staff parentStaffReference;

    /**
     * Constructs a new Position object.
     * 
     * @param staff
     *            A reference to a Staff object.
     */
    public Position(Staff staff) {
        super();
        this.parentStaffReference = staff;
    }

    /**
     * Returns the type of position.
     * 
     * @return An enum of type PositionType representing the type of position.
     */
    PositionType getPositionType() {
        return positionType;
    }

    /**
     * Checks whether a position has active assignments. For the instructor
     * position this means the instructor is assigned to one or more course
     * offerings.
     * 
     * @return True if the position has active assignments and false otherwise.
     */
    public abstract boolean hasAssignments();

}
