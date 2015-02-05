package model.courses;

import java.util.TreeMap;

import model.enrolments.Enrolment;
import model.staff.interfaces.Instructor;

/**
 * The CourseOffering class represents a course offering. A course offering is
 * related to one and only one course.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class CourseOffering {

    private Course course;
    private String id;
    private String year;
    private String textbook;
    private int fee;
    private boolean completed;
    private TreeMap<String, Enrolment> enrolments;
    private Instructor currentInstructor;

    /**
     * Constructs a course offering by initializing the course, id, and year of
     * the course offering.
     * 
     * @param course
     *            A reference to the course of the course offering.
     * @param id
     *            The id of the course offering.
     * @param year
     *            The year of the course offering.
     */
    public CourseOffering(Course course, String id, String year) {
        this.course = course;
        this.id = id;
        this.year = year;
        this.textbook = "None";
        this.fee = course.getFee();
        this.completed = false;
        this.enrolments = new TreeMap<String, Enrolment>();
        this.currentInstructor = null;
    }

    /**
     * Returns the course of the course offering.
     * 
     * @return A Course reference representing the course of the course
     *         offering.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Returns the id of the course offering.
     * 
     * @return A string representing the id of the course offering.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the year of the course offering.
     * 
     * @return A string representing the year of the course offering.
     */
    public String getYear() {
        return year;
    }

    /**
     * Returns the textbook for the course offering.
     * 
     * @return A string representing the textbook for the course offering.
     */
    public String getTextbook() {
        return textbook;
    }

    /**
     * Returns the fee for the course offering.
     * 
     * @return An integer representing the fee for the course offering.
     */
    public int getFee() {
        return fee;
    }

    /**
     * Returns a collection of enrolments for the course offering
     * 
     * @return A TreeMap of type Enrolment representing the enrolments for the
     *         course offering
     */
    public TreeMap<String, Enrolment> getEnrolments() {
        return enrolments;
    }

    /**
     * Returns the completed status for the course offering.
     * 
     * @return True if the course offering is completed and False otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the completed status for the course offering.
     * 
     * @param completed
     *            True for completed course offering and False otherwise.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
        if (currentInstructor != null) {
            this.currentInstructor.completeCourseOffering(this);
        }
    }

    /**
     * Returns the instructor for the course offering.
     * 
     * @return An object of type Instructor representing the instructor for the
     *         course offering.
     */
    public Instructor getInstructor() {
        return currentInstructor;
    }

    /**
     * Sets an instructor for the course offering.
     * 
     * @param instructor
     *            An object of type Instructor representing the instructor for
     *            the course offering.
     */
    public void setInstructor(Instructor instructor) {
        if (this.currentInstructor != null) {
            this.currentInstructor.removeCourseOffering(this);
        }
        this.currentInstructor = instructor;
        this.currentInstructor.addCourseOffering(this);
    }

    /**
     * Sets the fee for the course offering.
     * 
     * @param fee
     *            The fee for the course offering.
     */
    public void setFee(int fee) {
        this.fee = fee;
    }

    /**
     * Sets the textbook for the course offering.
     * 
     * @param textbook
     *            The textbook for the course offering.
     */
    public void setTextbook(String textbook) {
        this.textbook = textbook;
    }

    /**
     * Adds an enrolment to the course offering.
     * 
     * @param enrolment
     *            A reference to the enrolment.
     */
    public void addEnrolment(Enrolment enrolment) {
        enrolments.put(enrolment.getId(), enrolment);
    }

    /**
     * Removes an enrolment from the course offering.
     * 
     * @param enrolment
     *            A reference to the enrolment.
     */
    public void removeEnrolment(Enrolment enrolment) {
        enrolments.remove(enrolment.getId());
    }

    /**
     * Checks if course offering has enrolments
     * 
     * @return True if course offering has at least one enrolment and False
     *         otherwise
     */
    public boolean hasEnrolments() {
        return !enrolments.isEmpty();
    }

    /**
     * Checks if the course offering has been assigned an instructor.
     * 
     * @return True if the course offering has been assigned an instructor and
     *         false otherwise.
     */
    public boolean hasInstructor() {
        return (currentInstructor == null) ? false : true;
    }

}
