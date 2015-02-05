package model.interfaces;

import java.util.Collection;
import java.util.Set;

import model.exceptions.SRSException;
import model.students.Student;

/**
 * An interface to manage students.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public interface ManageStudents {

    /**
     * Adds a student.
     * 
     * @param studentId
     *            The id of the student.
     * @param name
     *            The name of the student.
     * @param yearOfBirth
     *            The year of birth of the student.
     * @throws SRSException
     *             When a student matching the parameter studentId already
     *             exists, or when the parameter yearOfBirth is not a number or
     *             is negative.
     * 
     */
    void addStudent(String studentId, String name, String yearOfBirth)
        throws SRSException;

    /**
     * Deletes a student.
     * 
     * @param studentId
     *            The id of a student.
     * @throws SRSException
     *             When a student matching the parameter studentId does not
     *             exist, when a student matching the parameter studentId has
     *             any enrolments, or when a student matching the parameter
     *             studentId is also an instructor.
     */
    void deleteStudent(String studentId) throws SRSException;

    /**
     * Exempts the student from a course.
     * 
     * @param studentId
     *            The id of a student.
     * @param courseId
     *            The id of a course.
     * @throws SRSException
     *             When a student matching the parameter studentId does not
     *             exist, when a course matching the parameter courseId does not
     *             exist, when a student matching the parameter studentId is
     *             already exempt from a course matching the parameter courseId,
     *             or when a student matching the parameter studentId is already
     *             exempt from the maximum number of courses allowed.
     */
    void assignExemption(String studentId, String courseId) throws SRSException;

    /**
     * Processes a payment and reduces the amount owing by the payment amount.
     * 
     * @param payment
     *            An integer representing the payment amount.
     * @throws SRSException
     *             When a student matching the parameter studentId does not
     *             exist, or when the payment is invalid.
     */
    void processPayment(String studentId, String payment) throws SRSException;

    /**
     * Returns a collection of all students.
     * 
     * @return A collection of type Student representing a list of all students.
     *         Returns null if there are no students.
     */
    Collection<Student> getAllStudents();

    /**
     * Returns a set of all student ids.
     * 
     * @return A set of type String representing a list of all student ids.
     *         Returns null if there are no students.
     */
    Set<String> getAllStudentIds();

}
