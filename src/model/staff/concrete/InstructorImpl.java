package model.staff.concrete;

import java.util.Collection;
import java.util.TreeMap;

import model.courses.Course;
import model.courses.CourseManager;
import model.courses.CourseOffering;
import model.staff.Position;
import model.staff.PositionType;
import model.staff.interfaces.Instructor;
import model.staff.interfaces.Staff;
import model.students.Student;

/**
 * The InstructorImpl class represents a concrete instructor.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class InstructorImpl extends Position implements Instructor {

    private TreeMap<String, CourseOffering> currentlyInstructing;
    private TreeMap<String, CourseOffering> previouslyInstructed;
    private TreeMap<String, Course> canInstruct;
    private Student student;

    /**
     * Constructs a new instructor position object.
     * 
     * @param staff
     *            A reference to the parent staff object.
     */
    public InstructorImpl(Staff staff) {
        super(staff);
        super.positionType = PositionType.Instructor;
        this.currentlyInstructing = new TreeMap<String, CourseOffering>();
        this.previouslyInstructed = new TreeMap<String, CourseOffering>();
        this.canInstruct = new TreeMap<String, Course>();
    }

    @Override
    public TreeMap<String, CourseOffering> getCurrentlyInstructing() {
        return currentlyInstructing;
    }

    @Override
    public TreeMap<String, CourseOffering> getPreviouslyInstructed() {
        return previouslyInstructed;
    }

    @Override
    public TreeMap<String, Course> getCanInstruct() {
        return canInstruct;
    }

    @Override
    public Student getStudent() {
        return student;
    }

    @Override
    public void setStudent(Student student) {
        this.student = student;
        Course[] canInstructArray = new Course[canInstruct.size()];
        canInstruct.values().toArray(canInstructArray);
        for (Course c : canInstructArray) {
            this.removeCourse(c);
        }
        Collection<Course> courses = CourseManager.getInstance()
                .getAllCourses();
        for (Course c : courses) {
            if (student.hasPassedCourse(c)) {
                this.addCourse(c);
            }
        }
    }

    @Override
    public boolean hasAssignments() {
        boolean result = false;
        if (isCurrentlyInstructing() || hasPreviouslyInstructed()) {
            result = true;
        }
        return result;
    }

    @Override
    public void addCourse(Course course) {
        String courseId = course.getId();
        if (!canInstruct.containsKey(courseId)) {
            canInstruct.put(courseId, course);
            course.addInstructor((Instructor) parentStaffReference);
        }
    }

    @Override
    public void removeCourse(Course course) {
        String courseId = course.getId();
        if (canInstruct.containsKey(courseId)) {
            canInstruct.remove(courseId);
            course.removeInstructor((Instructor) parentStaffReference);
        }
    }

    @Override
    public void addCourseOffering(CourseOffering courseOffering) {
        String courseOfferingId = courseOffering.getId();
        if (!currentlyInstructing.containsKey(courseOfferingId)) {
            currentlyInstructing.put(courseOfferingId, courseOffering);
        }
    }

    @Override
    public void removeCourseOffering(CourseOffering courseOffering) {
        String courseOfferingId = courseOffering.getId();
        if (currentlyInstructing.containsKey(courseOfferingId)) {
            currentlyInstructing.remove(courseOfferingId);
        }
    }

    @Override
    public boolean isCurrentlyInstructing() {
        return (!currentlyInstructing.isEmpty()) ? true : false;
    }

    @Override
    public boolean hasPreviouslyInstructed() {
        return (!previouslyInstructed.isEmpty()) ? true : false;
    }

    @Override
    public void completeCourseOffering(CourseOffering courseOffering) {
        currentlyInstructing.remove(courseOffering.getId());
        previouslyInstructed.put(courseOffering.getId(), courseOffering);
    }

    @Override
    public boolean isAlreadyInstructing(String courseOfferingId) {
        boolean result = false;
        if (currentlyInstructing.containsKey(courseOfferingId)) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean isStudent() {
        return (student != null) ? true : false;
    }

}
