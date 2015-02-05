package model.courses;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;

import model.exceptions.SRSException;
import model.interfaces.ManageCourses;
import model.staff.StaffManager;
import model.staff.interfaces.Instructor;
import model.staff.interfaces.Staff;

/**
 * The CourseManager class manages courses and course offerings.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class CourseManager implements ManageCourses {

    private static CourseManager instance = null;

    private TreeMap<String, Course> courses;
    private TreeMap<String, CourseOffering> courseOfferings;

    /**
     * A private constructor for the CourseManager singleton
     */
    private CourseManager() {
        this.courses = new TreeMap<String, Course>();
        this.courseOfferings = new TreeMap<String, CourseOffering>();
    }

    /**
     * Returns a reference to the CourseManager object. If a CourseManager does
     * not exist, one is created, and the reference to it returned.
     * 
     * @return A reference to the CourseManager object
     */
    public static CourseManager getInstance() {
        if (instance == null) {
            instance = new CourseManager();
        }
        return instance;
    }

    // ManageCourses Interface Methods

    @Override
    public void addStandardCourse(String id, String name, String fee)
        throws SRSException {
        if (courses.containsKey(id)) {
            throw new SRSException("Course " + id
                + " already exists. Cannot add course.");
        }
        int feeInt;
        try {
            feeInt = Integer.parseInt(fee);
            if (feeInt < 0) {
                throw new SRSException("Fee is negative. Cannot add course.");
            }
        } catch (NumberFormatException e) {
            throw new SRSException("Fee not a number. Cannot add course.");
        }
        StandardCourse standardCourse = new StandardCourse(id, name, feeInt);
        courses.put(id, standardCourse);
        StaffManager.getInstance().addCourseToInstructors(standardCourse);
    }

    @Override
    public void addAdvancedCourse(String id, String name, String fee)
        throws SRSException {
        if (courses.containsKey(id)) {
            throw new SRSException("Course " + id
                + " already exists. Cannot add course.");
        }
        int feeInt;
        try {
            feeInt = Integer.parseInt(fee);
            if (feeInt < 0) {
                throw new SRSException("Fee is negative. Cannot add course.");
            }
        } catch (NumberFormatException e) {
            throw new SRSException("Fee not a number. Cannot add course.");
        }
        AdvancedCourse advancedCourse = new AdvancedCourse(id, name, feeInt);
        courses.put(id, advancedCourse);
        StaffManager.getInstance().addCourseToInstructors(advancedCourse);
    }

    @Override
    public void deleteCourse(String id) throws SRSException {
        if (!courses.containsKey(id)) {
            throw new SRSException("Course " + id
                + " does not exist. Cannot delete course.");
        }
        if (courses.get(id).hasCourseOfferings()) {
            throw new SRSException("Course " + id
                + " has offerings. Cannot delete course.");
        }
        Course course = courses.remove(id);
        StaffManager.getInstance().removeCourseFromInstructors(course);
    }

    @Override
    public void addCourseOffering(String courseId, String courseOfferingId,
        String year) throws SRSException {
        if (!courses.containsKey(courseId)) {
            throw new SRSException("Course " + courseId
                + " does not exist. Cannot add course offering.");
        }
        if (courseOfferings.containsKey(courseOfferingId)) {
            throw new SRSException("Course offering " + courseOfferingId
                + " already exist. Cannot add course offering.");
        }
        CourseOffering co = new CourseOffering(courses.get(courseId),
            courseOfferingId, year);
        courseOfferings.put(courseOfferingId, co);
        courses.get(courseId).addCourseOffering(co);
    }

    @Override
    public void deleteCourseOffering(String id) throws SRSException {
        if (!courseOfferings.containsKey(id)) {
            throw new SRSException("Course offering " + id
                + " does not exist. Cannot delete course offering.");
        }
        if (courseOfferings.get(id).isCompleted()) {
            throw new SRSException("Course offering " + id
                + " is completed. Cannot delete course offering.");
        }
        if (courseOfferings.get(id).hasEnrolments()) {
            throw new SRSException("Course offering " + id
                + " has enrolments. Cannot delete course offering.");
        }
        CourseOffering co = courseOfferings.remove(id);
        if (co.hasInstructor()) {
            co.getInstructor().removeCourseOffering(co);
        }
        co.getCourse().removeCourseOffering(co);

    }

    @Override
    public void completeCourseOffering(String id) throws SRSException {
        if (!courseOfferings.containsKey(id)) {
            throw new SRSException("Course offering " + id
                + " does not exist. Cannot complete course offering.");
        }
        if (courseOfferings.get(id).isCompleted()) {
            throw new SRSException("Course offering " + id
                + " already completed.");
        }
        if (!courseOfferings.get(id).hasInstructor()) {
            throw new SRSException("Course offering " + id
                + " has no instructor. Cannot complete course offering.");
        }
        if (!courseOfferings.get(id).hasEnrolments()) {
            throw new SRSException("Course offering " + id
                + " has no enrolments. Cannot complete course offering.");
        }
        courseOfferings.get(id).setCompleted(true);
    }

    @Override
    public void assignInstructor(String staffId, String courseOfferingId)
        throws SRSException {
        if (!StaffManager.getInstance().staffExists(staffId)) {
            throw new SRSException("Staff member " + staffId
                + " does not exist. Cannot assign instructor.");
        }
        Staff staffMember = StaffManager.getInstance().getStaff(staffId);
        if (!(staffMember instanceof Instructor)) {
            throw new SRSException("Staff member " + staffId
                + " is not an instructor. Cannot assign instructor.");
        }
        Instructor instructor = (Instructor) staffMember;
        if (!courseOfferings.containsKey(courseOfferingId)) {
            throw new SRSException("Course offering " + courseOfferingId
                + " does not exist. Cannot assign instructor.");
        }
        CourseOffering courseOffering = courseOfferings.get(courseOfferingId);
        if (instructor.isAlreadyInstructing(courseOfferingId)) {
            throw new SRSException("Instructor " + staffId
                + " is already instructing course offering " + courseOfferingId
                + ". Cannot assign instructor.");
        }
        Course course = courseOffering.getCourse();
        if (!course.canInstruct(instructor)) {
            throw new SRSException("Instructor " + staffId
                + " cannot instruct " + course.getName() + " ("
                + courseOfferingId + "). Cannot assign instructor.");
        }
        courseOffering.setInstructor(instructor);

    }

    @Override
    public void addPrerequisite(String courseId, String prerequisiteCourseId)
        throws SRSException {
        if (!courses.containsKey(courseId)) {
            throw new SRSException("Course " + courseId
                + " does not exist. Cannot add prerequisiste.");
        }
        if (!courses.containsKey(prerequisiteCourseId)) {
            throw new SRSException("Course " + prerequisiteCourseId
                + " does not exist. Cannot add prerequisiste.");
        }
        if (courseId.equals(prerequisiteCourseId)) {
            throw new SRSException("Course " + courseId
                + " cannot be its own prerequisite. Cannot add prerequisiste.");
        }
        courses.get(courseId)
            .addPrerequisite(courses.get(prerequisiteCourseId));
    }

    @Override
    public void removePrerequisite(String courseID, String prerequisiteCourseID)
        throws SRSException {
        if (!courses.containsKey(courseID)) {
            throw new SRSException("Course " + courseID
                + " does not exist. Cannot remove prerequisiste.");
        }
        if (!courses.containsKey(prerequisiteCourseID)) {
            throw new SRSException("Course " + prerequisiteCourseID
                + " does not exist. Cannot remove prerequisiste.");
        }
        courses.get(courseID).removePrerequisite(
            courses.get(prerequisiteCourseID));
    }

    @Override
    public void setFeeForCourseOffering(String id, String fee)
        throws SRSException {
        if (!courseOfferings.containsKey(id)) {
            throw new SRSException("Course offering " + id
                + " does not exist. Cannot set fee.");
        }
        if (courseOfferings.get(id).isCompleted()) {
            throw new SRSException("Course offering " + id
                + " is completed. Cannot set fee.");
        }
        int feeInt;
        try {
            feeInt = Integer.parseInt(fee);
            if (feeInt < 0) {
                throw new SRSException("Fee is negative. Cannot set fee.");
            }
        } catch (NumberFormatException e) {
            throw new SRSException("Fee not a number. Cannot set fee.");
        }
        courseOfferings.get(id).setFee(feeInt);
    }

    @Override
    public void setTextbookForCourseOffering(String id, String textbook)
        throws SRSException {
        if (!courseOfferings.containsKey(id)) {
            throw new SRSException("Course offering " + id
                + " does not exist. Cannot set textbook.");
        }
        if (courseOfferings.get(id).isCompleted()) {
            throw new SRSException("Course offering " + id
                + " is completed. Cannot set textbook.");
        }
        courseOfferings.get(id).setTextbook(textbook);
    }

    @Override
    public Collection<Course> getAllCourses() {
        if (!courses.isEmpty()) {
            return courses.values();
        } else {
            return null;
        }
    }

    @Override
    public Collection<CourseOffering> getAllCourseOfferings() {
        if (!courseOfferings.isEmpty()) {
            return courseOfferings.values();
        } else {
            return null;
        }
    }

    @Override
    public Set<String> getAllCourseIds() {
        if (!courses.isEmpty()) {
            return courses.keySet();
        } else {
            return null;
        }
    }

    @Override
    public Set<String> getAllCourseOfferingIds() {
        if (!courseOfferings.isEmpty()) {
            return courseOfferings.keySet();
        } else {
            return null;
        }
    }

    // Course Manager Methods

    /**
     * Checks whether a course exists.
     * 
     * @param courseId
     *            The id of a course.
     * @return True if a course matching the parameter courseId exists and false
     *         otherwise.
     */
    public boolean courseExists(String courseId) {
        if (courses.containsKey(courseId)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether a course offering exists.
     * 
     * @param courseOfferingId
     *            The id of a course offering.
     * @return True if a course offering matching the parameter courseOfferingId
     *         exists and false otherwise.
     */
    public boolean courseOfferingExists(String courseOfferingId) {
        if (courseOfferings.containsKey(courseOfferingId)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a reference to a course.
     * 
     * @param courseId
     *            The id of a course.
     * @return A reference to a course matching the parameter courseId. Null if
     *         course not found.
     */
    public Course getCourse(String courseId) {
        return courses.get(courseId);
    }

    /**
     * Returns a reference to a course offering.
     * 
     * @param courseOfferingId
     *            The id of a course offering.
     * @return A reference to a course offering matching the parameter
     *         courseOfferingId. Null if course offering not found.
     */
    public CourseOffering getCourseOffering(String courseOfferingId) {
        return courseOfferings.get(courseOfferingId);
    }

    /**
     * Checks whether a course offering is completed.
     * 
     * @param courseOfferingId
     *            The id of the the course offering.
     * @return True if the course offering matching the parameter
     *         courseOfferingId is completed and false otherwise.
     */
    public boolean courseOfferingCompleted(String id) {
        return courseOfferings.get(id).isCompleted();
    }

    /**
     * Adds an instructor to all courses.
     * 
     * @param instructor
     *            A reference to an instructor.
     */
    public void addIntructorToCourses(Instructor instructor) {
        for (Course course : courses.values()) {
            course.addInstructor(instructor);
        }
    }

    /**
     * Removes an instructor from all courses.
     * 
     * @param instructor
     *            A reference to an instructor.
     */
    public void removeIntructorFromCourses(Instructor instructor) {
        for (Course course : courses.values()) {
            course.removeInstructor(instructor);
        }
    }

}
