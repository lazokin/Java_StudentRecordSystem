package model.interfaces;

import java.util.Collection;
import java.util.Set;

import model.courses.Course;
import model.courses.CourseOffering;
import model.exceptions.SRSException;

/**
 * An interface to manage courses.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public interface ManageCourses {

    /**
     * Adds a standard course.
     * 
     * @param courseId
     *            The id of the course.
     * @param name
     *            The name of the course.
     * @param fee
     *            The fee for the course.
     * @throws SRSException
     *             When a course matching the parameter courseId already exists,
     *             when the fee is not a number or when the fee is negative.
     */
    void addStandardCourse(String courseId, String name, String fee)
        throws SRSException;

    /**
     * Adds an advanced course.
     * 
     * @param courseId
     *            The id of the course.
     * @param name
     *            The name of the course.
     * @param fee
     *            The fee for the course.
     * @throws SRSException
     *             When a course matching the parameter courseId already exists,
     *             when the fee is not a number or when the fee is negative.
     */
    void addAdvancedCourse(String courseId, String name, String fee)
        throws SRSException;

    /**
     * Deletes a course.
     * 
     * @param id
     *            The id of the course
     * @throws SRSException
     *             When a course matching the parameter id does not exist or
     *             when a course matching the parameter id has course offerings
     *             associated
     */
    void deleteCourse(String id) throws SRSException;

    /**
     * Adds a prerequisite to a course.
     * 
     * @param courseId
     *            The id of the course to add a prerequisite to.
     * @param prerequisiteCourseId
     *            The id of the course to add as a prerequisite.
     * @throws SRSException
     *             When a course matching the parameter courseId does not exist,
     *             when a course matching the parameter prerequisiteCourseId
     *             does not exist, when a course matching the parameter
     *             prerequisiteCourseId is already a prerequisite for the course
     *             matching the parameter courseId, or when a course matching
     *             the parameter courseId equals a course matching the parameter
     *             prerequisiteCourseId.
     */
    void addPrerequisite(String courseId, String prerequisiteCourseId)
        throws SRSException;

    /**
     * Removes a prerequisite from a course.
     * 
     * @param courseId
     *            The id of the course to remove a prerequisite from.
     * @param prerequisiteCourseId
     *            The id of the course to remove as a prerequisite.
     * @throws SRSException
     *             When a course matching the parameter courseId does not exist,
     *             when a course matching the parameter prerequisiteCourseId
     *             does not exist, or when a course matching the parameter
     *             prerequisiteCourseId is not already a prerequisite for the
     *             course matching the parameter courseId.
     */
    void removePrerequisite(String courseId, String prerequisiteCourseId)
        throws SRSException;

    /**
     * Adds a course offering.
     * 
     * @param courseId
     *            The id of the course.
     * @param courseOfferingId
     *            The id of the course offering.
     * @param year
     *            The year of the course offering.
     * @throws SRSException
     *             When a course matching the parameter courseId does not exist
     *             or when a course offering matching the parameter
     *             courseOfferingId already exists.
     * 
     */
    void addCourseOffering(String courseId, String courseOfferingId, String id)
        throws SRSException;

    /**
     * Deletes a course offering.
     * 
     * @param courseOfferingId
     *            The id of the course offering.
     * @throws SRSException
     *             When a course offering matching the parameter
     *             courseOfferingId does not exist or when a course offering
     *             matching the parameter courseOfferingId has enrolments.
     */
    void deleteCourseOffering(String courseOfferingId) throws SRSException;

    /**
     * Closes the course offering from further enrolments.
     * 
     * @param courseOfferingId
     *            The id of the course offering.
     * @throws SRSException
     *             When a course offering matching the parameter
     *             courseOfferingId does not exist, when a course offering
     *             matching the parameter courseOfferingId is already completed,
     *             or when a course offering matching the parameter id has no
     *             instructor assigned.
     */
    void completeCourseOffering(String courseOfferingId) throws SRSException;

    /**
     * Assigns an instructor to a course offering.
     * 
     * @param staffId
     *            The id of the instructor.
     * @param courseOfferingId
     *            The id of the course offering.
     * @throws SRSException
     *             When a staff member matching the parameter staffId does not
     *             exist, when a staff member matching the parameter staffId is
     *             not an instructor, when a course offering matching the
     *             parameter courseOfferingId does not exist, when a staff
     *             member matching the parameter staffId is already instructing
     *             a course offering matching the parameter courseOfferingId, or
     *             when the staff member matching the parameter staffId cannot
     *             instruct the course offering matching the parameter
     *             courseOfferingId.
     */
    void assignInstructor(String staffId, String courseOfferingId)
        throws SRSException;

    /**
     * Sets the fee for a course offering.
     * 
     * @param courseOfferingId
     *            The id of the course offering.
     * @param fee
     *            The fee for the course offering.
     * @throws SRSException
     *             When the course offering matching the parameter
     *             courseOfferingId does not exist, when the course offering
     *             matching the parameter courseOfferingId is completed, or when
     *             the fee is negative.
     */
    void setFeeForCourseOffering(String courseOfferingId, String fee)
        throws SRSException;

    /**
     * Sets the textbook for a course offering.
     * 
     * @param courseOfferingId
     *            The id of the course offering.
     * @param textbook
     *            The textbook for the course offering.
     * @throws SRSException
     *             When the course offering matching the parameter
     *             courseOfferingId does not exist, when the course offering
     *             matching the parameter courseOfferingId is completed, when
     *             the fee is not a number, or when the fee is negative.
     */
    void setTextbookForCourseOffering(String courseOfferingId, String textbook)
        throws SRSException;

    /**
     * Returns a collection of all courses.
     * 
     * @return A collection of type Course representing a list of all courses.
     *         Returns null if there are no courses.
     */
    Collection<Course> getAllCourses();

    /**
     * Returns a collection of all course offerings.
     * 
     * @return A collection of type CourseOffering representing a list of all
     *         course offerings. Returns null if there are no course offerings.
     */
    Collection<CourseOffering> getAllCourseOfferings();

    /**
     * Returns a set of all student course ids.
     * 
     * @return A set of type String representing a set of all course ids.
     *         Returns null if there are no courses.
     */
    Set<String> getAllCourseIds();

    /**
     * Returns a set of all course offering ids.
     * 
     * @return A set of type String representing a set of all course offering
     *         ids. Returns null if there are no course offerings.
     */
    Set<String> getAllCourseOfferingIds();

}
