package model.enrolments;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;

import model.StudentRecordSystem;
import model.courses.Course;
import model.courses.CourseManager;
import model.courses.CourseOffering;
import model.exceptions.SRSException;
import model.interfaces.ManageEnrolments;
import model.students.Student;
import model.students.StudentManager;

/**
 * The EnrolmentManager class manages enrolments.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class EnrolmentManager implements ManageEnrolments {

    private static EnrolmentManager instance = null;

    private TreeMap<String, Enrolment> allEnrolments;
    private TreeMap<String, Enrolment> currentEnrolments;
    private TreeMap<String, Enrolment> completedEnrolments;

    /**
     * A private constructor for the EnrolmentManager singleton
     */
    private EnrolmentManager() {
        this.allEnrolments = new TreeMap<String, Enrolment>();
        this.currentEnrolments = new TreeMap<String, Enrolment>();
        this.completedEnrolments = new TreeMap<String, Enrolment>();
    }

    /**
     * Returns a reference to the EnrolmentManager object. If a EnrolmentManager
     * does not exist, one is created, and the reference to it returned.
     * 
     * @return A reference to the EnrolmentManager object
     */
    public static EnrolmentManager getInstance() {
        if (instance == null) {
            instance = new EnrolmentManager();
        }
        return instance;
    }

    // ManageEnrolments Interface Methods

    @Override
    public void createEnrolment(String studentId, String courseOfferingId)
        throws SRSException {
        StudentManager sm = StudentManager.getInstance();
        if (!sm.studentExists(studentId)) {
            throw new SRSException("Student " + studentId
                + " does not exist exist. Cannot create enrolment.");
        }
        CourseManager cm = CourseManager.getInstance();
        if (!cm.courseOfferingExists(courseOfferingId)) {
            throw new SRSException("Course offering " + courseOfferingId
                + " does not exist exist. Cannot create enrolment.");
        }
        if (cm.getCourseOffering(courseOfferingId).isCompleted()) {
            throw new SRSException("Course offering " + courseOfferingId
                + " is completed. Cannot create enrolment.");
        }
        String enrolmentId = studentId + "-" + courseOfferingId;
        if (allEnrolments.containsKey(enrolmentId)) {
            throw new SRSException("Student " + studentId
                + " already enrolled in course offering " + courseOfferingId
                + ". Cannot create enrolment.");
        }
        Student student = sm.getStudent(studentId);
        CourseOffering courseOffering = cm.getCourseOffering(courseOfferingId);
        if (!student.satisfiesPrerequisites(courseOffering.getCourse())) {
            throw new SRSException("Student " + studentId
                + " does not satisfy the prerequisite for course offering "
                + courseOfferingId + ". Cannot create enrolment.");
        }
        Course course = courseOffering.getCourse();
        if (student.hasPassedCourse(course)) {
            throw new SRSException("Student " + studentId
                + " has already passed this course. Cannot create enrolment.");
        }
        if (student.hasExemption(course.getId())) {
            throw new SRSException("Student " + studentId
                + " is exempt from this course. Cannot create enrolment.");
        }
        Enrolment enrolment = new Enrolment(student, courseOffering);
        allEnrolments.put(enrolmentId, enrolment);
        currentEnrolments.put(enrolmentId, enrolment);
        student.addEnrolment(enrolment);
        courseOffering.addEnrolment(enrolment);
    }

    @Override
    public void deleteEnrolment(String studentId, String courseOfferingId)
        throws SRSException {
        StudentManager sm = StudentManager.getInstance();
        if (!sm.studentExists(studentId)) {
            throw new SRSException("Student " + studentId
                + " does not exist exist. Cannot delete enrolment.");
        }
        CourseManager cm = CourseManager.getInstance();
        if (!cm.courseOfferingExists(courseOfferingId)) {
            throw new SRSException("Course offering " + courseOfferingId
                + " does not exist exist. Cannot delete enrolment.");
        }
        String enrolmentId = studentId + "-" + courseOfferingId;
        if (!allEnrolments.containsKey(enrolmentId)) {
            throw new SRSException("Student " + studentId
                + " not already enrolled in course offering "
                + courseOfferingId + ". Cannot delete enrolment.");
        }
        if (allEnrolments.get(enrolmentId).isCompleted()) {
            throw new SRSException("Student " + studentId
                + " has already completed course offering " + courseOfferingId
                + ". Cannot delete enrolment.");
        }
        Student student = sm.getStudent(studentId);
        CourseOffering courseOffering = cm.getCourseOffering(courseOfferingId);
        Enrolment enrolment = allEnrolments.get(enrolmentId);
        allEnrolments.remove(enrolmentId);
        currentEnrolments.remove(enrolmentId);
        student.removeEnrolment(enrolment);
        courseOffering.removeEnrolment(enrolment);

    }

    @Override
    public void completeEnrolment(String studentId, String courseOfferingId,
        String grade) throws SRSException {
        StudentManager sm = StudentManager.getInstance();
        if (!sm.studentExists(studentId)) {
            throw new SRSException("Student " + studentId
                + " does not exist exist. Cannot complete enrolment.");
        }
        CourseManager cm = CourseManager.getInstance();
        if (!cm.courseOfferingExists(courseOfferingId)) {
            throw new SRSException("Course offering " + courseOfferingId
                + " does not exist exist. Cannot complete enrolment.");
        }
        String enrolmentId = studentId + "-" + courseOfferingId;
        if (!allEnrolments.containsKey(enrolmentId)) {
            throw new SRSException("Student " + studentId
                + " not already enrolled in course offering "
                + courseOfferingId + ". Cannot complete enrolment.");
        }
        if (allEnrolments.get(enrolmentId).isCompleted()) {
            throw new SRSException("Student " + studentId
                + " has already completed course offering " + courseOfferingId
                + ". Cannot complete enrolment.");
        }
        int gradeInt;
        try {
            gradeInt = Integer.parseInt(grade);
            int minValidGrade = StudentRecordSystem.MIN_VALID_GRADE;
            int maxValidGrade = StudentRecordSystem.MAX_VALID_GRADE;
            if (gradeInt < minValidGrade || gradeInt > maxValidGrade) {
                throw new SRSException("Grade entered not between "
                    + minValidGrade + " and " + maxValidGrade
                    + ". Cannot complete enrolment.");
            }
        } catch (NumberFormatException e) {
            throw new SRSException(
                "Grade not a number. ECannot complete enrolment.");
        }
        Enrolment enrolment = allEnrolments.get(enrolmentId);
        enrolment.setGrade(gradeInt);
        enrolment.setCompleted(true);
        StudentManager.getInstance().getStudent(studentId).completeEnrolment(
            enrolment);
        currentEnrolments.remove(enrolmentId);
        completedEnrolments.put(enrolmentId, enrolment);
    }

    @Override
    public Collection<Enrolment> getAllEnrolments() {
        if (!allEnrolments.isEmpty()) {
            return allEnrolments.values();
        } else {
            return null;
        }
    }

    @Override
    public Collection<Enrolment> getCurrentEnrolments() {
        if (!currentEnrolments.isEmpty()) {
            return currentEnrolments.values();
        } else {
            return null;
        }
    }

    @Override
    public Collection<Enrolment> getCompletedEnrolments() {
        if (!completedEnrolments.isEmpty()) {
            return completedEnrolments.values();
        } else {
            return null;
        }
    }

    @Override
    public Set<String> getAllEnrolmentIds() {
        if (!allEnrolments.isEmpty()) {
            return allEnrolments.keySet();
        } else {
            return null;
        }
    }

    // EnrolmentManager Methods

    /**
     * Check whether the enrolment exists.
     * 
     * @param id
     *            The id of the enrolment.
     * @return True if the enrolment exists and False otherwise.
     */
    public boolean enrolmentExists(String id) {
        return allEnrolments.containsKey(id);
    }

    /**
     * Check whether the enrolment is completed.
     * 
     * @param enrolmentId
     *            The id of the enrolment.
     * @return True if the enrolment is completed and False otherwise.
     */
    public boolean enrolmentCompleted(String enrolmentId) {
        return allEnrolments.get(enrolmentId).isCompleted();
    }

}
