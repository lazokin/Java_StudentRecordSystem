package model.students;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;

import model.courses.Course;
import model.courses.CourseManager;
import model.exceptions.SRSException;
import model.interfaces.ManageStudents;

/**
 * The StudentManager class manages Students.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class StudentManager implements ManageStudents {

    private static StudentManager instance = null;

    private TreeMap<String, Student> students;

    /**
     * A private constructor for the StudentManager singleton
     */
    private StudentManager() {
        super();
        this.students = new TreeMap<String, Student>();
    }

    /**
     * Returns a reference to the StudentManager object. If a StudentManager
     * does not exist, one is created, and the reference to it returned.
     * 
     * @return A reference to the StudentManager object
     */
    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }

    // ManageStudents Interface Methods

    @Override
    public void addStudent(String id, String name, String yearOfBirth)
        throws SRSException {
        id = "S".concat(id);
        if (!correctFormat(id)) {
            throw new SRSException(
                "Student id not in the correct format. Enter a number only. Cannot add student.");
        }
        if (students.containsKey(id)) {
            throw new SRSException("Student " + id
                + " already exists. Cannot add student.");
        }
        int yearOfBirthInt;
        try {
            yearOfBirthInt = Integer.parseInt(yearOfBirth);
            if (yearOfBirthInt < 0) {
                throw new SRSException(
                    "Year of birth is negative. Cannot add student.");
            }
        } catch (NumberFormatException e) {
            throw new SRSException(
                "Year of birth not a number. Cannot add student.");
        }
        students.put(id, new StudentImpl(id, name, yearOfBirth));
    }

    @Override
    public void deleteStudent(String id) throws SRSException {
        if (!students.containsKey(id)) {
            throw new SRSException("Student " + id
                + " does not exist. Cannot delete student.");
        }
        if (students.get(id).hasCurrentEnrolments()) {
            throw new SRSException("Student " + id
                + " enrolled in course offerings. Cannot delete student.");
        }
        if (students.get(id).isInstructor()) {
            throw new SRSException("Student " + id
                + " is an instructor. Cannot delete student.");
        }
        students.remove(id);
    }

    @Override
    public void assignExemption(String studentId, String courseId)
        throws SRSException {
        if (!students.containsKey(studentId)) {
            throw new SRSException("Student " + studentId
                + " does not exist. Cannot assign exemption.");
        }
        if (!CourseManager.getInstance().courseExists(courseId)) {
            throw new SRSException("Course " + courseId
                + " does not exist. Cannot assign exemption.");
        }
        Course course = CourseManager.getInstance().getCourse(courseId);
        students.get(studentId).assignExemption(course);

    }

    @Override
    public void processPayment(String studentId, String payment)
        throws SRSException {
        if (!students.containsKey(studentId)) {
            throw new SRSException("Student " + studentId
                + " does not exist. Cannot process payment.");
        }
        int intPayment;
        try {
            intPayment = Integer.parseInt(payment);
            if (intPayment < 0) {
                throw new SRSException(
                    "Payment is negative. Cannot process payment.");
            }
        } catch (NumberFormatException e) {
            throw new SRSException(
                "Payment not a number. Cannot process payment.");
        }
        students.get(studentId).processPayment(intPayment);
    }

    @Override
    public Collection<Student> getAllStudents() {
        if (!students.isEmpty()) {
            return students.values();
        } else {
            return null;
        }
    }

    @Override
    public Set<String> getAllStudentIds() {
        if (!students.isEmpty()) {
            return students.keySet();
        } else {
            return null;
        }
    }

    // Student Manager Methods

    /**
     * Checks whether a student exists.
     * 
     * @param studentId
     *            The id of the the student.
     * @return True if a student matching the parameter studentId exists and
     *         false otherwise.
     */
    public boolean studentExists(String studentId) {
        return students.containsKey(studentId);
    }

    /**
     * Returns a reference to a student.
     * 
     * @param studentId
     *            The id of the student.
     * @return A reference to a student matching the parameter studentId. Null
     *         if student not found.
     */
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    /**
     * Checks for the correct format of the student id.
     * 
     * @param id
     *            The student id
     * @return Returns true when the id is in the correct format, and false
     *         otherwise.
     */
    private boolean correctFormat(String id) {
        boolean result = true;
        id = id.replaceFirst("S", "");
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

}
