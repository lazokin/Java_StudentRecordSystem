package model.courses;

import java.util.TreeMap;

import model.exceptions.SRSException;
import model.staff.interfaces.Instructor;
import model.staff.interfaces.Staff;

/**
 * The Course class represents a course. A course can have none to many course
 * offerings.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public abstract class Course {

    private String id;
    private String name;
    private int fee;
    private TreeMap<String, CourseOffering> courseOfferings;
    private TreeMap<String, Course> prerequisites;
    private TreeMap<String, Instructor> availableInstructors;

    /**
     * Constructs a new course by initializing the id, name, and fee for the
     * course.
     * 
     * @param id
     *            The id of the course.
     * @param name
     *            The name of the course.
     * @param fee
     *            The fee for the course.
     */
    public Course(String id, String name, int fee) {
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.courseOfferings = new TreeMap<String, CourseOffering>();
        this.prerequisites = new TreeMap<String, Course>();
        this.availableInstructors = new TreeMap<String, Instructor>();
    }

    /**
     * Returns the id of the course.
     * 
     * @return A string representing the id of the course.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the course.
     * 
     * @return A string representing the name of the course.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the fee for the course.
     * 
     * @return An integer representing the fee for the course.
     */
    public int getFee() {
        return fee;
    }

    /**
     * Returns the course offerings for the course.
     * 
     * @return A Map of type CourseOffering representing a list of course
     *         offerings.
     */
    public TreeMap<String, CourseOffering> getCourseOfferings() {
        return courseOfferings;
    }

    /**
     * Returns the prerequisites for the course.
     * 
     * @return A Map of type Course representing a list of prerequisites.
     */
    public TreeMap<String, Course> getPrerequisites() {
        return prerequisites;
    }

    /**
     * Sets the fee for the course.
     * 
     * @param fee
     *            The fee for the course
     */
    public void setFee(int fee) {
        this.fee = fee;
    }

    /**
     * Adds a course offering to the course.
     * 
     * @param courseOffering
     *            A reference to a course offering.
     */
    public void addCourseOffering(CourseOffering courseOffering) {
        courseOfferings.put(courseOffering.getId(), courseOffering);
    }

    /**
     * Removes a course offering from the course.
     * 
     * @param courseOffering
     *            A reference to a course offering.
     */
    public void removeCourseOffering(CourseOffering courseOffering) {
        courseOfferings.remove(courseOffering.getId());
    }

    /**
     * Checks if the course has any course offerings.
     * 
     * @return True if this course has at least one offering and false
     *         otherwise.
     */
    public boolean hasCourseOfferings() {
        return !courseOfferings.isEmpty();
    }

    /**
     * Add a prerequisite course to this course.
     * 
     * @param course
     *            A reference to a prerequisite course.
     * @throws SRSException
     *             When a course matching the parameter course already exists as
     *             a prerequisite to this course.
     */
    public void addPrerequisite(Course course) throws SRSException {
        if (prerequisites.containsValue(course)) {
            throw new SRSException("Course " + course.getId()
                + " is already a prerequisite for course " + this.getId()
                + ". Cannot add prerequisite.");
        }
        prerequisites.put(course.getId(), course);
    }

    /**
     * Removes a prerequisite course from this course.
     * 
     * @param course
     *            A reference to a prerequisite course.
     * @throws SRSException
     *             When a course matching the parameter course does not already
     *             exist as a prerequisite to this course.
     */
    public void removePrerequisite(Course course) throws SRSException {
        if (!prerequisites.containsValue(course)) {
            throw new SRSException("Course " + course.getId()
                + " is not a prerequisite for course " + this.getId()
                + ". Cannot remove prerequisite.");
        }
        prerequisites.remove(course.getId());
    }

    /**
     * Checks if the course has any prerequisite courses.
     * 
     * @return True if the course has prerequisite courses and false otherwise.
     */
    public boolean hasPrerequisites() {
        return !prerequisites.isEmpty();
    }

    /**
     * Adds an instructor to the list of available instructors for the course.
     * 
     * @param instructor
     *            A reference to an instructor.
     */
    public void addInstructor(Instructor instructor) {
        String staffId = ((Staff) instructor).getId();
        if (!availableInstructors.containsKey(staffId)) {
            availableInstructors.put(staffId, instructor);
            instructor.addCourse(this);
        }
    }

    /**
     * Removes an instructor from the list of available instructors for the
     * course.
     * 
     * @param instructor
     *            A reference to an instructor.
     */
    public void removeInstructor(Instructor instructor) {
        String staffId = ((Staff) instructor).getId();
        if (availableInstructors.containsKey(staffId)) {
            availableInstructors.remove(((Staff) instructor).getId());
            instructor.removeCourse(this);
        }
    }

    /**
     * Checks whether an instructor can instruct this course
     * 
     * @param instructor
     *            A reference to an instructor.
     * @return True if an instructor matching the parameter instructor can teach
     *         this course and false otherwise.
     */
    public boolean canInstruct(Instructor instructor) {
        boolean result = false;
        if (availableInstructors.containsValue(instructor)) {
            result = true;
        }
        return result;
    }

}
