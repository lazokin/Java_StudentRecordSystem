package model.staff.concrete;

import java.util.TreeMap;

import model.courses.Course;
import model.courses.CourseOffering;
import model.exceptions.SRSException;
import model.staff.StaffImpl;
import model.staff.interfaces.Instructor;
import model.staff.interfaces.Permanent;
import model.students.Student;

/**
 * The PermanentInstructor class represents a concrete permanent instructor.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class PermanentInstructor extends StaffImpl implements Permanent,
        Instructor {

    /**
     * Constructs a new PermanentInstructor object.
     * 
     * @param id
     *            The id of the instructor.
     * @param name
     *            The name of the instructor.
     * @param pay
     *            The salary of the instructor.
     * @throws SRSException
     *             When the salary is negative.
     */
    public PermanentInstructor(String id, String name, int pay)
            throws SRSException {
        super(id, name);
        this.employment = new PermanentImpl(this, pay);
        this.position = new InstructorImpl(this);
    }

    @Override
    public TreeMap<String, CourseOffering> getCurrentlyInstructing() {
        return ((InstructorImpl) position).getCurrentlyInstructing();
    }

    @Override
    public TreeMap<String, CourseOffering> getPreviouslyInstructed() {
        return ((InstructorImpl) position).getPreviouslyInstructed();
    }

    @Override
    public TreeMap<String, Course> getCanInstruct() {
        return ((InstructorImpl) position).getCanInstruct();
    }

    @Override
    public Student getStudent() {
        return ((InstructorImpl) position).getStudent();
    }

    @Override
    public void setStudent(Student student) {
        ((InstructorImpl) position).setStudent(student);
    }

    @Override
    public void addCourse(Course course) {
        ((InstructorImpl) position).addCourse(course);
    }

    @Override
    public void removeCourse(Course course) {
        ((InstructorImpl) position).removeCourse(course);
    }

    @Override
    public void addCourseOffering(CourseOffering courseOffering) {
        ((InstructorImpl) position).addCourseOffering(courseOffering);
    }

    @Override
    public void removeCourseOffering(CourseOffering courseOffering) {
        ((InstructorImpl) position).removeCourseOffering(courseOffering);
    }

    @Override
    public boolean isCurrentlyInstructing() {
        return ((InstructorImpl) position).isCurrentlyInstructing();
    }

    @Override
    public boolean hasPreviouslyInstructed() {
        return ((InstructorImpl) position).hasPreviouslyInstructed();
    }

    @Override
    public void completeCourseOffering(CourseOffering courseOffering) {
        ((InstructorImpl) position).completeCourseOffering(courseOffering);
    }

    @Override
    public boolean isAlreadyInstructing(String courseOfferingId) {
        return ((InstructorImpl) position)
                .isAlreadyInstructing(courseOfferingId);
    }

    @Override
    public boolean isStudent() {
        return ((InstructorImpl) position).isStudent();
    }

}
