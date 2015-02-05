package model.interfaces;

import java.util.Collection;
import java.util.Set;

import model.enrolments.Enrolment;
import model.exceptions.SRSException;

/**
 * An interface to manage enrolments.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public interface ManageEnrolments {

    /**
     * Creates an enrolment. Enrols a student into a course offering.
     * 
     * @param studentId
     *            The id of the student.
     * @param courseOfferingId
     *            The id of the course offering.
     * @throws SRSException
     *             When a student matching the parameter studentId does not
     *             exist, when a course offering matching the parameter
     *             courseOfferingId does not exist, when a course offering
     *             matching the parameter courseOfferingId is completed, when a
     *             student matching the parameter studentId is already enrolled
     *             in a course offering matching the parameter courseOfferingId,
     *             when a student matching the parameter studentId does not
     *             satisfy the prerequisites to enrol in a course offering
     *             matching the parameter courseOfferingId, when a student
     *             matching the parameter studentId has already passed the
     *             course associate with the the course offering matching the
     *             parameter courseOfferingId, or when a student matching the
     *             parameter studentId has an exemption for the course matchign
     *             the parameter courseOfferingId.
     */
    void createEnrolment(String studentId, String courseOfferingId)
        throws SRSException;

    /**
     * Deletes an enrolment. De-enrols a student from a course offering.
     * 
     * @param studentId
     *            The id of the student.
     * @param courseOfferingId
     *            The id of the course offering.
     * @throws SRSException
     *             When a student matching the parameter studentId does not
     *             exist, when a course offering matching the parameter
     *             courseOfferingId does not exist, when a student matching the
     *             parameter studentId is not already enrolled in a course
     *             offering matching the parameter courseOfferingId, or when a
     *             student matching the parameter studentId is enrolled in a
     *             course offering matching the parameter courseOfferingId and
     *             the enrolment is completed.
     */
    void deleteEnrolment(String studentId, String courseOfferingId)
        throws SRSException;

    /**
     * Completes an enrolment.
     * 
     * @param studentId
     *            The id of the student
     * @param courseOfferingId
     *            The id of the course offering
     * @param grade
     *            The grade for the enrolment
     * @throws SRSException
     *             When a student matching the parameter studentId does not
     *             exist, when a course offering matching the parameter
     *             courseOfferingId does not exist, when a student matching the
     *             parameter studentId is not already enrolled in a course
     *             offering matching the parameter courseOfferingId, when a
     *             student matching the parameter studentId is enrolled in a
     *             course offering matching the parameter courseOfferingId and
     *             the enrolment is completed, or when the grade is invalid.
     */
    void completeEnrolment(String studentId, String courseOfferingId,
        String grade) throws SRSException;

    /**
     * Returns a collection of all enrolments.
     * 
     * @return A collection of type Enrolment representing a list of all
     *         enrolments. Returns null if there are no enrolments.
     */
    Collection<Enrolment> getAllEnrolments();

    /**
     * Returns a collection of current enrolments.
     * 
     * @return A collection of type Enrolment representing a list of all current
     *         enrolments. Returns null if there are no current enrolments.
     */
    Collection<Enrolment> getCurrentEnrolments();

    /**
     * Returns a collection of completed enrolments.
     * 
     * @return A collection of type Enrolment representing a list of all
     *         completed enrolments. Returns null if there are no completed
     *         enrolments.
     */
    Collection<Enrolment> getCompletedEnrolments();

    /**
     * Returns a set of all enrolment ids.
     * 
     * @return A set of type String representing a set of all enrolment ids.
     *         Returns null if there are no enrolments.
     */
    Set<String> getAllEnrolmentIds();

}
