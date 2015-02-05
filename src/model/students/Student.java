package model.students;

import java.util.TreeMap;

import model.Person;
import model.courses.Course;
import model.enrolments.Enrolment;
import model.exceptions.SRSException;
import model.staff.interfaces.Staff;

/**
 * An interface for a student.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public interface Student extends Person {

    /**
     * Returns the year of birth of the student.
     * 
     * @return A string representing the year of birth of the student
     */
    String getYearOfBirth();

    /**
     * Returns the status of the standard certificate.
     * 
     * @return True if the student has been awarded the standard certificate and
     *         false otherwise.
     */
    boolean hasStandardCertificate();

    /**
     * Returns the status of the advanced certificate.
     * 
     * @return True if the student has been awarded the advanced certificate and
     *         false otherwise.
     */
    boolean hasAdvancedCertificate();

    /**
     * Returns the current enrolments for the student,
     * 
     * @return A map representing the current enrolments for the student,
     */
    TreeMap<String, Enrolment> getCurrentEnrolments();

    /**
     * Returns the completed enrolments for the student,
     * 
     * @return A map representing the completed enrolments for the student,
     */
    TreeMap<String, Enrolment> getCompletedEnrolments();

    /**
     * Returns the exemptions for the student.
     * 
     * @return A map representing the exemptions for the student.
     */
    TreeMap<String, Course> getExemptions();

    /**
     * Returns the total payments for the student.
     * 
     * @return An integer representing the total payments for the student.
     */
    int getTotalPayments();

    /**
     * Returns the fee balance for the student.
     * 
     * @return An integer representing the fee balance for the student.
     */
    int getTuitionFeeBalance();

    /**
     * Returns the total fee for the student.
     * 
     * @return An integer representing the total fee for the student.
     */
    int getTuitionFeeTotal();

    /**
     * Returns the total tax for the student.
     * 
     * @return An integer representing the total tax for the student.
     */
    int getTotalTax();

    /**
     * Returns the staff reference for the student.
     * 
     * @return A reference to Staff. Null if student is not a staff member.
     */
    Staff getStaff();

    /**
     * Sets the staff reference for the student.
     * 
     * @param staff
     *            A reference to a Staff object.
     */
    void setStaff(Staff staff);

    /**
     * Adds an enrolment to the student.
     * 
     * @param enrolment
     *            A reference to the enrolment.
     */
    void addEnrolment(Enrolment enrolment);

    /**
     * Removes an enrolment from the student.
     * 
     * @param enrolment
     *            A reference to the enrolment.
     * @return A reference to the removed enrolment.
     */
    Enrolment removeEnrolment(Enrolment enrolment);

    /**
     * Completes an enrolment for the student.
     * 
     * @param enrolment
     *            A reference to the enrolment.
     */
    void completeEnrolment(Enrolment enrolment);

    /**
     * Checks if student is currently enrolled in any course offerings
     * 
     * @return True if student is currently enrolled in any course offerings and
     *         False otherwise
     */
    boolean hasCurrentEnrolments();

    /**
     * Checks if student has completed enrolments in any course offerings
     * 
     * @return True if student has completed enrolments in any course offerings
     *         and False otherwise
     */
    boolean hasCompletedEnrolments();

    /**
     * Checks if student has any enrolments
     * 
     * @return True if student has any enrolments and False otherwise
     */
    boolean hasEnrolments();

    /**
     * Assign a course exemption to the student.
     * 
     * @param course
     *            A reference to a course.
     * @throws SRSException
     *             When the student is already exempt from a course matching the
     *             parameter course.
     */
    void assignExemption(Course course) throws SRSException;

    /**
     * Checks if student satisfies a course prerequisite.
     * 
     * @param course
     *            A reference to the course
     * @return True if all prerequisites are satisfied for the course and false
     *         otherwise.
     */
    boolean satisfiesPrerequisites(Course course);

    /**
     * Checks whether the student is already exempt from a course.
     * 
     * @param courseId
     *            The id of a course.
     * @return True if the student is already exempt from the course and false
     *         otherwise.
     */
    boolean hasExemption(String courseId);

    /**
     * Checks whether the student has already passed a course.
     * 
     * @param course
     *            A reference to the course.
     * @return True if the student has already passed the course and false
     *         otherwise.
     */
    boolean hasPassedCourse(Course course);

    /**
     * Calculates and returns the age of the student.
     * 
     * @return An integer representing the age of the student.
     */
    int getAge();

    /**
     * Process a payment. A negative fee balance represent a credit.
     * 
     * @param payment
     *            An integer representing the payment amount.
     */
    void processPayment(int payment);

    /**
     * Checks whether the student is also an instructor.
     * 
     * @return True if the student is also an instructor and false otherwise.
     */
    boolean isInstructor();

    boolean hasCurrentEnrolmentInCourse(String courseId);

    boolean hasCompletedEnrolmentInCourse(String courseId);

    Enrolment getCurrentEnrolmentForCourse(String courseId);

    Enrolment getCompletedEnrolmentForCourse(String courseId);

}
