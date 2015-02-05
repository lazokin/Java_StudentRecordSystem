package model.enrolments;

import model.StudentRecordSystem;
import model.courses.Course;
import model.courses.CourseOffering;
import model.students.Student;

/**
 * The Enrolment class represents an enrolment.
 * 
 * @author Nikolce Ambukovski
 * 
 */
public class Enrolment {

    private String id;
    private int fee;
    private Student student;
    private Course course;
    private CourseOffering courseOffering;
    private boolean completed;
    private int grade;
    private double taxRate;
    private int tax;

    /**
     * Constructs a new enrolment for a student to a course offering.
     * 
     * @param student
     *            A reference to the student
     * @param courseOffering
     *            A reference to the course offering
     */
    public Enrolment(Student student, CourseOffering courseOffering) {
        this.id = student.getId() + "-" + courseOffering.getId();
        this.fee = this.calculateFee(student, courseOffering);
        this.student = student;
        this.course = courseOffering.getCourse();
        this.courseOffering = courseOffering;
        this.completed = false;
        this.grade = 0;
        this.taxRate = StudentRecordSystem.getTaxRate();
        this.tax = (int) (this.fee * this.taxRate);
    }

    /**
     * Returns the id of the enrolment.
     * 
     * @return A string representing the id of the enrolment.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the fee for the enrolment.
     * 
     * @return An integer representing the fee for the enrolment.
     */
    public int getFee() {
        return fee;
    }

    /**
     * Returns the student of the enrolment.
     * 
     * @return A Student reference to the student of the enrolment.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Returns the course of the enrolment.
     * 
     * @return A Course reference to the course of the enrolment.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Returns the course offering of the enrolment.
     * 
     * @return A CourseOffering reference to the course offering of the.
     *         enrolment
     */
    public CourseOffering getCourseOffering() {
        return courseOffering;
    }

    /**
     * Returns the grade for the enrolment.
     * 
     * @return An integer representing the grade for the enrolment.
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Returns the tax rate for the enrolment.
     * 
     * @return A double representing the tax rate for the enrolment.
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * Returns the tax for the enrolment.
     * 
     * @return An integer representing the tax for the enrolment.
     */
    public int getTax() {
        return tax;
    }

    /**
     * Returns the completion status for the enrolment.
     * 
     * @return True if the enrolment is completed and false otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the grade for the enrolment.
     * 
     * @param grade
     *            An integer representing the grade for the enrolment.
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * Sets the completion status for the enrolment.
     * 
     * @param completed
     *            True if the enrolment is completed and false otherwise.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Returns the pass status for the enrolment.
     * 
     * @return True if the grade for the enrolment is greater than or equal to
     *         the passing grade, and false otherwise.
     */
    public boolean passed() {
        return (grade >= StudentRecordSystem.MIN_GRADE_TO_PASS_COURSE) ? true
                : false;
    }

    /**
     * Calculates the fee for the enrolment based on the student age
     * 
     * @param s
     *            A reference to the student.
     * @param co
     *            A reference to the course offering.
     * @return An integer representing the fee for the enrolment.
     */
    public int calculateFee(Student s, CourseOffering co) {
        int studentAge = s.getAge();
        int minAgeForFeeDiscount = StudentRecordSystem.MIN_AGE_FOR_FEE_DISCOUNT;
        double percentageDiscountForAge = StudentRecordSystem.PERCENTAGE_DISCOUNT_FOR_AGE;
        int standardFee = co.getFee();
        int discountedFee = (int) (standardFee * (1 - percentageDiscountForAge / 100));
        if (studentAge >= minAgeForFeeDiscount) {
            return discountedFee;
        } else {
            return standardFee;
        }
    }

}
