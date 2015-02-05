package model.staff.interfaces;

import java.util.TreeMap;

import model.courses.Course;
import model.courses.CourseOffering;
import model.students.Student;

/**
 * An interface for an instructor.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public interface Instructor {

    /**
     * Returns the course offerings the instructor is currently instructing.
     * 
     * @return A map of type CourseOffering. Returns null if instructor is not
     *         instructing any course offerings.
     */
    TreeMap<String, CourseOffering> getCurrentlyInstructing();

    /**
     * Returns the course offerings the instructor has previously instructed.
     * 
     * @return A map of type CourseOffering. Returns null if instructor has not
     *         previously instructed any course offerings.
     */
    TreeMap<String, CourseOffering> getPreviouslyInstructed();

    /**
     * Returns the courses the instructor can instruct.
     * 
     * @return A map of type Course. Returns null if instructor cannot instruct
     *         any course.
     */
    TreeMap<String, Course> getCanInstruct();

    /**
     * Returns the student reference for the instructor.
     * 
     * @return A reference to Student. Null if instructor is not a student.
     */
    Student getStudent();

    /**
     * Sets the student reference for the instructor.
     * 
     * @param staff
     *            A reference to a Student object.
     */
    void setStudent(Student student);

    /**
     * Add a course to the list of courses that the instructor can instruct.
     * 
     * @param course
     *            A reference to a course.
     */
    void addCourse(Course course);

    /**
     * Remove a course from the list of courses that the instructor can
     * instruct.
     * 
     * @param course
     *            A reference to a course.
     */
    void removeCourse(Course course);

    /**
     * Add a course offering to the list of courses offerings that the
     * instructor is instructing.
     * 
     * @param courseOffering
     *            A reference to a course offering.
     */
    void addCourseOffering(CourseOffering courseOffering);

    /**
     * Remove a course offering from the list of courses offerings that the
     * instructor is instructing.
     * 
     * @param courseOffering
     *            A reference to a course offering.
     */
    void removeCourseOffering(CourseOffering courseOffering);

    /**
     * Checks whether the instructor is currently instructing any course
     * offerings.
     * 
     * @return True if the instructor is currently instructing any course
     *         offerings and false otherwise.
     */
    boolean isCurrentlyInstructing();

    /**
     * Checks whether the instructor has previously instructed any course
     * offerings.
     * 
     * @return True if the instructor has previously instructed any course
     *         offerings and false otherwise.
     */
    boolean hasPreviouslyInstructed();

    /**
     * Completes a course offering. Moves course offering from currently
     * instructing collection to previously instructed collection.
     * 
     * @param courseOffering
     *            A reference to the course offering.
     */
    void completeCourseOffering(CourseOffering courseOffering);

    /**
     * Checks whether the instructor is already instructing a course offering.
     * 
     * @param courseOfferingId
     *            A string representing the id of a course offering.
     * @return True if the instructor is already instructing a course offering
     *         matching the parameter coureOfferingId and false otherwise.
     */
    boolean isAlreadyInstructing(String courseOfferingId);

    /**
     * Checks whether the instructor is also a student.
     * 
     * @return True if the instructor is also a student and false otherwise.
     */
    boolean isStudent();

}
