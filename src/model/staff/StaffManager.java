package model.staff;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;

import model.courses.Course;
import model.courses.CourseManager;
import model.exceptions.SRSException;
import model.interfaces.ManageStaff;
import model.staff.concrete.CasualAdministrator;
import model.staff.concrete.CasualInstructor;
import model.staff.concrete.CasualManager;
import model.staff.concrete.PermanentAdministrator;
import model.staff.concrete.PermanentInstructor;
import model.staff.concrete.PermanentManager;
import model.staff.interfaces.Instructor;
import model.staff.interfaces.Staff;
import model.students.Student;
import model.students.StudentManager;

/**
 * The StaffManager class manages staff.
 * 
 * @author Nikolce Ambukovski, s2008618
 * 
 */
public class StaffManager implements ManageStaff {

    private static StaffManager instance = null;

    private TreeMap<String, Staff> staff;
    private TreeMap<String, Instructor> instructors;

    /**
     * A private constructor for the StaffManager singleton
     */
    private StaffManager() {
        super();
        this.staff = new TreeMap<String, Staff>();
        this.instructors = new TreeMap<String, Instructor>();
    }

    /**
     * Returns a reference to the StaffManager object. If a StaffManager does
     * not exist, one is created, and the reference to it returned.
     * 
     * @return A reference to the StaffManager object
     */
    public static StaffManager getInstance() {
        if (instance == null) {
            instance = new StaffManager();
        }
        return instance;
    }

    // ManageStaff Interface Methods

    @Override
    public void addStaff(String staffId, String name,
        EmploymentType employmentType, PositionType positionType, String pay)
        throws SRSException {
        staffId = "E".concat(staffId);
        if (!correctFormat(staffId)) {
            throw new SRSException(
                "Staff id not in the correct format. Enter a number only. Cannot add staff member.");
        }
        if (staff.containsKey(staffId)) {
            throw new SRSException("Staff member " + staffId
                + " already exists. Cannot add staff member.");
        }
        int payInt;
        try {
            payInt = Integer.parseInt(pay);
            if (payInt < 0) {
                throw new SRSException(
                    "Pay is negative. Cannot add staff member.");
            }
        } catch (NumberFormatException e) {
            throw new SRSException("Pay not a number. Cannot add staff member.");
        }
        Staff staffMember = null;
        if (employmentType == EmploymentType.Casual) {
            switch (positionType) {
                case Manager:
                    staffMember = new CasualManager(staffId, name, payInt);
                    break;
                case Administrator:
                    staffMember = new CasualAdministrator(staffId, name, payInt);
                    break;
                case Instructor:
                    staffMember = new CasualInstructor(staffId, name, payInt);
                    break;
                default:

            }
        } else {
            switch (positionType) {
                case Manager:
                    staffMember = new PermanentManager(staffId, name, payInt);
                    break;
                case Administrator:
                    staffMember = new PermanentAdministrator(staffId, name,
                        payInt);
                    break;
                case Instructor:
                    staffMember = new PermanentInstructor(staffId, name, payInt);
                    break;
                default:

            }
        }
        staff.put(staffId, staffMember);
        if (staffMember instanceof Instructor) {
            instructors.put(staffId, (Instructor) staffMember);
            CourseManager.getInstance().addIntructorToCourses(
                (Instructor) staffMember);
        }
    }

    @Override
    public void addStudentInstructor(String studentId, String staffId,
        String pay) throws SRSException {
        if (!StudentManager.getInstance().studentExists(studentId)) {
            throw new SRSException("Student " + studentId
                + " does not exist. Cannot add student instructor.");
        }
        if (staff.containsKey(staffId)) {
            throw new SRSException("Staff member " + staffId
                + " already exist. Cannot add student instructor.");
        }
        Student student = StudentManager.getInstance().getStudent(studentId);
        if (student.isInstructor()) {
            throw new SRSException("Student " + studentId
                + " is already an instructor. Cannot add student instructor.");
        }
        int payInt;
        try {
            payInt = Integer.parseInt(pay);
            if (payInt < 0) {
                throw new SRSException(
                    "Pay is negative. Cannot add student instructor.");
            }
        } catch (NumberFormatException e) {
            throw new SRSException(
                "Pay not a number. Cannot add student instructor.");
        }
        Staff staffMember = new CasualInstructor(staffId, student.getName(),
            payInt);
        Instructor instructor = (Instructor) staffMember;
        staff.put(staffId, staffMember);
        instructors.put(staffId, instructor);
        student.setStaff(staffMember);
        instructor.setStudent(student);
    }

    @Override
    public void deleteStaff(String staffId) throws SRSException {
        if (!staff.containsKey(staffId)) {
            throw new SRSException("Staff member " + staffId
                + " does not exist. Cannot delete staff member.");
        }
        if (staff.get(staffId).hasAssignments()) {
            throw new SRSException("Staff member " + staffId
                + " has assignments. Cannot delete staff member.");
        }
        Staff staffMember = staff.remove(staffId);
        if (staffMember instanceof Instructor) {
            Instructor instructor = instructors.remove(staffId);
            CourseManager.getInstance().removeIntructorFromCourses(instructor);
            if (instructor.isStudent()) {
                instructor.getStudent().setStaff(null);
            }
        }
    }

    @Override
    public void setPay(String staffId, String pay) throws SRSException {
        if (!staff.containsKey(staffId)) {
            throw new SRSException("Staff member " + staffId
                + " does not exist. Cannot set pay.");
        }
        int payInt;
        try {
            payInt = Integer.parseInt(pay);
            if (payInt < 0) {
                throw new SRSException("Pay is negative. Cannot set pay.");
            }
        } catch (NumberFormatException e) {
            throw new SRSException("Pay not a number. Cannot set pay.");
        }
        staff.get(staffId).setPay(payInt);
    }

    @Override
    public Collection<Staff> getAllStaff() {
        if (!staff.isEmpty()) {
            return staff.values();
        } else {
            return null;
        }
    }

    @Override
    public Collection<Instructor> getAllInstructors() {
        if (!instructors.isEmpty()) {
            return instructors.values();
        } else {
            return null;
        }
    }

    @Override
    public Set<String> getAllStaffIds() {
        if (!staff.isEmpty()) {
            return staff.keySet();
        } else {
            return null;
        }
    }

    @Override
    public Set<String> getAllInstructorIds() {
        if (!instructors.isEmpty()) {
            return instructors.keySet();
        } else {
            return null;
        }
    }

    // Staff Manager Methods

    /**
     * Checks whether a staff member exists.
     * 
     * @param staffId
     *            The id of the the staff member.
     * @return True if a staff member matching the parameter staffId exists and
     *         false otherwise.
     */
    public boolean staffExists(String staffId) {
        return staff.containsKey(staffId);
    }

    /**
     * Returns a reference to a staff member.
     * 
     * @param staffId
     *            The id of the staff member.
     * @return A reference to a staff member matching the parameter staffId.
     *         Null if staff member not found.
     */
    public Staff getStaff(String staffId) {
        return staff.get(staffId);
    }

    /**
     * Returns a reference to an instructor.
     * 
     * @param instructorId
     *            The id of the instructor.
     * @return A reference to an instructor matching the parameter instructorId.
     *         Null if instructor not found.
     */
    public Instructor getInstructor(String instructorId) {
        return instructors.get(instructorId);
    }

    /**
     * Adds a course to all instructors.
     * 
     * @param course
     *            A reference to a course.
     */
    public void addCourseToInstructors(Course course) {
        for (Instructor instructor : instructors.values()) {
            instructor.addCourse(course);
        }
    }

    /**
     * Removes a course to all instructors.
     * 
     * @param course
     *            A reference to a course.
     */
    public void removeCourseFromInstructors(Course course) {
        for (Instructor instructor : instructors.values()) {
            instructor.removeCourse(course);
        }
    }

    /**
     * Checks for the correct format of the staff id.
     * 
     * @param id
     *            The staff id
     * @return Returns true when the id is in the correct format, and false
     *         otherwise.
     */
    private boolean correctFormat(String id) {
        boolean result = true;
        id = id.replaceFirst("E", "");
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

}
