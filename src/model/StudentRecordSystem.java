package model;

import java.util.Collection;
import java.util.Set;

import model.courses.Course;
import model.courses.CourseManager;
import model.courses.CourseOffering;
import model.enrolments.Enrolment;
import model.enrolments.EnrolmentManager;
import model.exceptions.SRSException;
import model.interfaces.ManageCourses;
import model.interfaces.ManageEnrolments;
import model.interfaces.ManageStaff;
import model.interfaces.ManageStudents;
import model.staff.EmploymentType;
import model.staff.PositionType;
import model.staff.StaffManager;
import model.staff.interfaces.Instructor;
import model.staff.interfaces.Staff;
import model.students.Student;
import model.students.StudentManager;

/**
 * The StudentRecordSystem class acts as the facade or interface of the complete
 * student record system to outside clients. Outside clients could include, but
 * are not limited to, user interface systems. The StudentRecordSystem is
 * implemented as a Singleton.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class StudentRecordSystem implements ManageStudents, ManageCourses,
    ManageStaff, ManageEnrolments {

    private static StudentRecordSystem instance = null;
    private CourseManager courseManager;
    private EnrolmentManager enrolmentManager;
    private StudentManager studentManager;
    private StaffManager staffManager;

    private static double taxRate = 0.3;

    public static final int MIN_VALID_GRADE = 0;
    public static final int MAX_VALID_GRADE = 100;
    public static final int MIN_AGE_FOR_FEE_DISCOUNT = 51;
    public static final int PERCENTAGE_DISCOUNT_FOR_AGE = 35;
    public static final int MAX_NUMBER_OF_EXEMPTIONS = 2;
    public static final int MIN_GRADE_TO_PASS_COURSE = 50;
    public static final int MIN_GRADE_TO_INSTRUCT_COURSE = 80;
    public static final int FIXED_PAY_RATE_FOR_COURSE_OFFERING = 400;
    public static final int INSTRUCT_HOURS_FOR_COURSE_OFFERING = 20;
    public static final String[] COURSES_FOR_STANDARD_CERT = { "JP", "OOD",
        "ST" };
    public static final String[] COURSES_FOR_ADVANCED_CERT = { "J2EE", "SA",
        "DP" };

    /**
     * A private constructor for the StudentRecordSystem singleton
     */
    public StudentRecordSystem() {
        this.courseManager = CourseManager.getInstance();
        this.enrolmentManager = EnrolmentManager.getInstance();
        this.studentManager = StudentManager.getInstance();
        this.staffManager = StaffManager.getInstance();
    }

    /**
     * Returns a reference to the StudentRecordSystem object. If a
     * StudentRecordSystem does not exist, one is created, and the reference to
     * it returned.
     * 
     * @return A reference to the StudentRecordSystem object
     */
    public static StudentRecordSystem getInstance() {
        if (instance == null) {
            instance = new StudentRecordSystem();
        }
        return instance;
    }

    // ManageStudents Interface Methods

    @Override
    public void addStudent(String id, String name, String birthYear)
        throws SRSException {
        studentManager.addStudent(id, name, birthYear);

    }

    @Override
    public void deleteStudent(String id) throws SRSException {
        studentManager.deleteStudent(id);

    }

    @Override
    public void assignExemption(String studentId, String courseId)
        throws SRSException {
        studentManager.assignExemption(studentId, courseId);
    }

    @Override
    public void processPayment(String studentId, String payment)
        throws SRSException {
        studentManager.processPayment(studentId, payment);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return studentManager.getAllStudents();
    }

    @Override
    public Set<String> getAllStudentIds() {
        return studentManager.getAllStudentIds();
    }

    // ManageCourses Interface Methods

    @Override
    public void addStandardCourse(String id, String name, String fee)
        throws SRSException {
        courseManager.addStandardCourse(id, name, fee);
    }

    @Override
    public void addAdvancedCourse(String id, String name, String fee)
        throws SRSException {
        courseManager.addAdvancedCourse(id, name, fee);
    }

    @Override
    public void deleteCourse(String id) throws SRSException {
        courseManager.deleteCourse(id);
    }

    @Override
    public void addPrerequisite(String courseId, String prerequisiteCourseId)
        throws SRSException {
        courseManager.addPrerequisite(courseId, prerequisiteCourseId);
    }

    @Override
    public void removePrerequisite(String courseID, String prerequisiteCourseID)
        throws SRSException {
        courseManager.removePrerequisite(courseID, prerequisiteCourseID);
    }

    @Override
    public void addCourseOffering(String courseId, String courseOfferingId,
        String year) throws SRSException {
        courseManager.addCourseOffering(courseId, courseOfferingId, year);
    }

    @Override
    public void deleteCourseOffering(String id) throws SRSException {
        courseManager.deleteCourseOffering(id);
    }

    @Override
    public void completeCourseOffering(String id) throws SRSException {
        courseManager.completeCourseOffering(id);
    }

    @Override
    public void assignInstructor(String staffId, String courseOfferingId)
        throws SRSException {
        courseManager.assignInstructor(staffId, courseOfferingId);
    }

    @Override
    public void setFeeForCourseOffering(String id, String fee)
        throws SRSException {
        courseManager.setFeeForCourseOffering(id, fee);
    }

    @Override
    public void setTextbookForCourseOffering(String id, String textbook)
        throws SRSException {
        courseManager.setTextbookForCourseOffering(id, textbook);
    }

    @Override
    public Collection<Course> getAllCourses() {
        return courseManager.getAllCourses();
    }

    @Override
    public Collection<CourseOffering> getAllCourseOfferings() {
        return courseManager.getAllCourseOfferings();
    }

    @Override
    public Set<String> getAllCourseIds() {
        return courseManager.getAllCourseIds();
    }

    @Override
    public Set<String> getAllCourseOfferingIds() {
        return courseManager.getAllCourseOfferingIds();
    }

    // ManageStaff Interface Methods

    @Override
    public void addStaff(String staffId, String name,
        EmploymentType employmentType, PositionType positionType, String pay)
        throws SRSException {
        staffManager.addStaff(staffId, name, employmentType, positionType, pay);
    }

    @Override
    public void addStudentInstructor(String studentId, String staffId,
        String pay) throws SRSException {
        staffManager.addStudentInstructor(studentId, staffId, pay);
    }

    @Override
    public void deleteStaff(String staffId) throws SRSException {
        staffManager.deleteStaff(staffId);
    }

    @Override
    public void setPay(String staffId, String pay) throws SRSException {
        staffManager.setPay(staffId, pay);
    }

    @Override
    public Collection<Staff> getAllStaff() {
        return staffManager.getAllStaff();
    }

    @Override
    public Collection<Instructor> getAllInstructors() {
        return staffManager.getAllInstructors();
    }

    @Override
    public Set<String> getAllStaffIds() {
        return staffManager.getAllStaffIds();
    }

    @Override
    public Set<String> getAllInstructorIds() {
        return staffManager.getAllInstructorIds();
    }

    // ManageEnrolments Interface Methods

    @Override
    public void createEnrolment(String studentId, String courseOfferingId)
        throws SRSException {
        enrolmentManager.createEnrolment(studentId, courseOfferingId);
    }

    @Override
    public void deleteEnrolment(String studentId, String courseOfferingId)
        throws SRSException {
        enrolmentManager.deleteEnrolment(studentId, courseOfferingId);

    }

    @Override
    public void completeEnrolment(String studentId, String courseOfferingId,
        String grade) throws SRSException {
        enrolmentManager.completeEnrolment(studentId, courseOfferingId, grade);
    }

    @Override
    public Collection<Enrolment> getAllEnrolments() {
        return enrolmentManager.getAllEnrolments();
    }

    @Override
    public Collection<Enrolment> getCurrentEnrolments() {
        return enrolmentManager.getCurrentEnrolments();
    }

    @Override
    public Collection<Enrolment> getCompletedEnrolments() {
        return enrolmentManager.getCompletedEnrolments();
    }

    @Override
    public Set<String> getAllEnrolmentIds() {
        return enrolmentManager.getAllEnrolmentIds();
    }

    // Student Record System methods

    /**
     * Gets the tax rate.
     * 
     * @return A double representing the current tax rate.
     */
    public static double getTaxRate() {
        return taxRate;
    }

    /**
     * Sets the tax rate. Used for all enrolments.
     * 
     * @param taxRate
     *            A double representing the current tax rate.
     */
    public void setTaxRate(double taxRate) throws SRSException {
        boolean taxRateOutOfRange = (taxRate < 0.0 || taxRate > 1.0);
        if (taxRateOutOfRange) {
            throw new SRSException(
                "Tax rate must be between 0.0 and 1.0. Tax rate unchanged.");
        }
        StudentRecordSystem.taxRate = taxRate;
    }

}
