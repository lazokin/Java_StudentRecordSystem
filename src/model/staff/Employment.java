package model.staff;

import model.exceptions.SRSException;
import model.staff.interfaces.Staff;

public abstract class Employment {

    protected EmploymentType employmentType;
    protected Staff parentStaffReference;

    /**
     * Constructs a new Employment object.
     * 
     * @param staff
     *            A reference to a Staff object.
     */
    public Employment(Staff staff) {
        super();
        this.parentStaffReference = staff;
    }

    /**
     * Returns the type of employment.
     * 
     * @return An enum of type EmploymentType representing the type of
     *         employment.
     */
    EmploymentType getEmploymentType() {
        return employmentType;
    }

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
    public abstract void setPay(int pay) throws SRSException;

    /**
     * Gets the pay for the staff member. For casual staff members the pay
     * represents their hourly rate. For permanent staff members the pay
     * represents their salary.
     * 
     * @return An integer representing the pay for the staff member.
     */
    public abstract int getPay();

}
