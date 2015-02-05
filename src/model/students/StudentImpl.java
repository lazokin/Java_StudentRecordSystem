package model.students;

import java.util.Calendar;
import java.util.TreeMap;

import model.PersonImpl;
import model.StudentRecordSystem;
import model.courses.Course;
import model.courses.CourseManager;
import model.enrolments.Enrolment;
import model.exceptions.SRSException;
import model.staff.interfaces.Instructor;
import model.staff.interfaces.Staff;

/**
 * The Student class represents a student.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class StudentImpl extends PersonImpl implements Student {

    private String yearOfBirth;
    private boolean standardCertificate;
    private boolean advancedCertificate;
    private TreeMap<String, Enrolment> currentEnrolments;
    private TreeMap<String, Enrolment> completedEnrolments;
    private TreeMap<String, Course> exemptions;
    private int totalPayments;
    private int tuitionFeeTotal;
    private int feeBalanceBalance;
    private int totalTax;
    private Staff staff;

    /**
     * Constructs a new student with parameters id, name, and year of birth.
     * 
     * @param id
     *            The id of the student
     * @param name
     *            The name of the student
     * @param yearOfBirth
     *            The year of birth of the student
     */
    public StudentImpl(String id, String name, String yearOfBirth) {
        super(id, name);
        this.yearOfBirth = yearOfBirth;
        this.standardCertificate = false;
        this.advancedCertificate = false;
        this.currentEnrolments = new TreeMap<String, Enrolment>();
        this.completedEnrolments = new TreeMap<String, Enrolment>();
        this.exemptions = new TreeMap<String, Course>();
        this.totalPayments = 0;
        this.tuitionFeeTotal = 0;
        this.feeBalanceBalance = 0;
        this.totalTax = 0;
        this.staff = null;
    }

    @Override
    public String getYearOfBirth() {
        return yearOfBirth;
    }

    @Override
    public boolean hasStandardCertificate() {
        return standardCertificate;
    }

    @Override
    public boolean hasAdvancedCertificate() {
        return advancedCertificate;
    }

    @Override
    public TreeMap<String, Enrolment> getCurrentEnrolments() {
        return currentEnrolments;
    }

    @Override
    public TreeMap<String, Enrolment> getCompletedEnrolments() {
        return completedEnrolments;
    }

    @Override
    public TreeMap<String, Course> getExemptions() {
        return exemptions;
    }

    @Override
    public int getTotalPayments() {
        return totalPayments;
    }

    @Override
    public int getTuitionFeeBalance() {
        return feeBalanceBalance;
    }

    @Override
    public int getTuitionFeeTotal() {
        return tuitionFeeTotal;
    }

    @Override
    public int getTotalTax() {
        return totalTax;
    }

    @Override
    public Staff getStaff() {
        return staff;
    }

    @Override
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public void addEnrolment(Enrolment enrolment) {
        currentEnrolments.put(enrolment.getId(), enrolment);
        feeBalanceBalance += enrolment.getFee();
        tuitionFeeTotal += enrolment.getFee();
        totalTax += enrolment.getTax();
    }

    @Override
    public Enrolment removeEnrolment(Enrolment enrolment) {
        feeBalanceBalance -= enrolment.getFee();
        tuitionFeeTotal -= enrolment.getFee();
        totalTax -= enrolment.getTax();
        return currentEnrolments.remove(enrolment.getId());

    }

    @Override
    public void completeEnrolment(Enrolment enrolment) {
        currentEnrolments.remove(enrolment.getId());
        completedEnrolments.put(enrolment.getId(), enrolment);
        if (this.isInstructor()) {
            if (this.hasPassedCourse(enrolment.getCourse())) {
                ((Instructor) staff).addCourse(enrolment.getCourse());
            }
        }
        if (satisfiesStandardCertificate()) {
            this.standardCertificate = true;
        }
        if (satisfiesAdvancedCertificate()) {
            this.advancedCertificate = true;
        }
    }

    @Override
    public boolean hasCurrentEnrolments() {
        return !currentEnrolments.isEmpty();
    }

    @Override
    public boolean hasCompletedEnrolments() {
        return !completedEnrolments.isEmpty();
    }

    @Override
    public boolean hasEnrolments() {
        return (hasCurrentEnrolments() || hasCompletedEnrolments());
    }

    @Override
    public void assignExemption(Course course) throws SRSException {
        if (exemptions.containsValue(course)) {
            throw new SRSException("Student " + this.id
                + " is already exempt from course " + course.getId()
                + ". Cannot assign exemption.");
        }
        int maxNoExcemptions = StudentRecordSystem.MAX_NUMBER_OF_EXEMPTIONS;
        if (exemptions.size() == 2) {
            throw new SRSException("Student " + this.id
                + " is already exempt from the maximum number of courses ("
                + maxNoExcemptions + "). Cannot assign exemption.");
        }
        exemptions.put(course.getId(), course);
    }

    @Override
    public boolean satisfiesPrerequisites(Course course) {

        if (!course.hasPrerequisites()) {
            return true;
        }

        for (Course prerequisiteCourse : course.getPrerequisites().values()) {
            if (!hasExemption(prerequisiteCourse.getId())
                && !hasPassedCourse(prerequisiteCourse)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasExemption(String courseId) {
        boolean result = false;
        if (exemptions.containsKey(courseId)) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean hasPassedCourse(Course course) {
        boolean result = false;
        for (Enrolment completedEnrolment : completedEnrolments.values()) {
            if (course.getId()
                .compareTo(completedEnrolment.getCourse().getId()) == 0) {
                if (completedEnrolment.passed()) {
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public int getAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int birthYear = Integer.parseInt(yearOfBirth);
        int age = currentYear - birthYear;
        return age;

    }

    @Override
    public void processPayment(int payment) {
        feeBalanceBalance -= payment;
        totalPayments += payment;
    }

    @Override
    public boolean isInstructor() {
        return (staff != null) ? true : false;
    }

    @Override
    public boolean hasCurrentEnrolmentInCourse(String courseId) {
        boolean result = false;
        for (Enrolment e : currentEnrolments.values()) {
            if (e.getCourse().getId().equals(courseId)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean hasCompletedEnrolmentInCourse(String courseId) {
        boolean result = false;
        for (Enrolment e : completedEnrolments.values()) {
            if (e.getCourse().getId().equals(courseId)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Enrolment getCurrentEnrolmentForCourse(String courseId) {
        Enrolment result = null;
        for (Enrolment e : currentEnrolments.values()) {
            if (e.getCourse().getId().equals(courseId)) {
                result = e;
            }
        }
        return result;
    }

    @Override
    public Enrolment getCompletedEnrolmentForCourse(String courseId) {
        Enrolment result = null;
        for (Enrolment e : completedEnrolments.values()) {
            if (e.getCourse().getId().equals(courseId)) {
                result = e;
            }
        }
        return result;
    }

    // StudentImpl methods

    /**
     * Checks whether student has passed or been exempt from all the courses
     * required for the standard certificate.
     * 
     * @return True if the student satisfies the standard certificate
     *         requirements and false otherwise.
     */
    private boolean satisfiesStandardCertificate() {
        return satisfiesCourses(StudentRecordSystem.COURSES_FOR_STANDARD_CERT);
    }

    /**
     * Checks whether student has passed or been exempt from all the courses
     * required for the advanced certificate.
     * 
     * @return True if the student satisfies the advanced certificate
     *         requirements and false otherwise.
     */
    private boolean satisfiesAdvancedCertificate() {
        return satisfiesCourses(StudentRecordSystem.COURSES_FOR_ADVANCED_CERT);
    }

    /**
     * Checks if the students has passed or been exempt from a list of courses.
     * 
     * @param courseIds
     *            A list of course ids.
     * @return True if the student has passed or been exempt from the list of
     *         courses represented by the list of courseIds and false otherwise.
     */
    private boolean satisfiesCourses(String[] courseIds) {
        boolean result = true;
        for (String courseId : courseIds) {
            if (result == true) {
                Course c = CourseManager.getInstance().getCourse(courseId);
                if (!hasExemption(c.getId()) && !hasPassedCourse(c)) {
                    result = false;
                }
            }
        }
        return result;
    }

}
