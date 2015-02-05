package model.staff.interfaces;

import model.Person;
import model.exceptions.SRSException;
import model.staff.EmploymentType;
import model.staff.PositionType;

/**
 * An interface for a staff member.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public interface Staff extends Person {

    /**
     * Returns the type of employment.
     * 
     * @return An enum of type EmploymentType representing the type of
     *         employment.
     */
    EmploymentType getEmploymentType();

    /**
     * Returns the type of position.
     * 
     * @return An enum of type PositionType representing the type of position.
     */
    PositionType getPositionType();

    /**
     * Checks whether a staff member has active assignments. For an instructor
     * this means the instructor is assigned to one or more course offerings.
     * 
     * @return True if the staff member has active assignments and false
     *         otherwise.
     */
    boolean hasAssignments();

    /**
     * Gets the pay for the staff member. For casual staff members the pay
     * represents their hourly rate. For permanent staff members the pay
     * represents their salary.
     * 
     * @return An integer representing the pay for the staff member.
     */
    int getPay();

    /**
     * Sets the pay for the staff member. For casual staff members the pay
     * represents their hourly rate. For permanent staff members the pay
     * represents their salary.
     * 
     * @param pay
     *            An integer representing the pay for the staff member.
     * @throws SRSException
     *             When the pay is negative.
     * 
     */
    void setPay(int pay) throws SRSException;

}
