package model.interfaces;

import java.util.Collection;
import java.util.Set;

import model.exceptions.SRSException;
import model.staff.EmploymentType;
import model.staff.PositionType;
import model.staff.interfaces.Instructor;
import model.staff.interfaces.Staff;

/**
 * An interface to manage staff.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public interface ManageStaff {

    /**
     * Adds a staff member.
     * 
     * @param staffId
     *            The id of the staff member.
     * @param name
     *            The name of the staff member.
     * @param employmentType
     *            An enum of type EmploymentType representing the employment
     *            type of the staff member.
     * @param positionType
     *            An enum of type PositionType representing the position type of
     *            the staff member.
     * @param pay
     *            The pay for the staff member.
     * @throws SRSException
     *             When a staff member matching the parameter staffId already
     *             exists, or when the pay is invalid.
     */
    void addStaff(String staffId, String name, EmploymentType employmentType,
        PositionType positionType, String pay) throws SRSException;

    /**
     * Adds a casual student instructor.
     * 
     * @param studentId
     *            The id of the student.
     * @param staffId
     *            The id of the staff member.
     * @throws SRSException
     *             When a student matching the parameter studentId does not
     *             exist, when a staff member matching the parameter staffId
     *             already exists, when a student matching the parameter
     *             studentId is already an instructor, or when the pay is
     *             invalid.
     */
    void addStudentInstructor(String studentId, String staffId, String pay)
        throws SRSException;

    /**
     * Deletes a staff member.
     * 
     * @param staffId
     *            The id of the staff member.
     * @throws SRSException
     *             When a staff member matching the parameter staffId does not
     *             exist or when a staff member matching the parameter staffId
     *             is instructing or has instructed a course offering.
     */
    void deleteStaff(String staffId) throws SRSException;

    /**
     * Sets the pay for the staff member. For casual staff members the pay
     * represents their hourly rate. For permanent staff members the pay
     * represents their salary.
     * 
     * @param staffId
     *            The id of the staff member.
     * @param pay
     *            The pay for the staff member.
     * @throws SRSException
     *             When a staff member matching the parameter staffId does not
     *             exist or when the pay is invalid.
     * 
     */
    void setPay(String staffId, String pay) throws SRSException;

    /**
     * Returns a collection of all staff.
     * 
     * @return A collection of type Staff representing a list of all staff.
     *         Returns null if there are no staff.
     */
    Collection<Staff> getAllStaff();

    /**
     * Returns a collection of all instructors.
     * 
     * @return A collection of type Instructor representing a list of all
     *         instructors. Returns null if there are no instructors.
     */
    Collection<Instructor> getAllInstructors();

    /**
     * Returns a set of all staff ids.
     * 
     * @return A set of type String representing a list of all staff ids.
     *         Returns null if there are no staff members.
     */
    Set<String> getAllStaffIds();

    /**
     * Returns a set of all instructor ids.
     * 
     * @return A set of type String representing a list of all instructor ids.
     *         Returns null if there are no instructors.
     */
    Set<String> getAllInstructorIds();

}
