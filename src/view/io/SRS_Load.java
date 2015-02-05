package view.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import model.StudentRecordSystem;
import model.exceptions.SRSException;
import model.staff.EmploymentType;
import model.staff.PositionType;
import model.staff.interfaces.Staff;
import model.students.Student;
import view.MainWindow;

public class SRS_Load {

    public SRS_Load() {
        super();
    }

    // Load all data from files to Student Record System
    public static void load(MainWindow mainWindow, StudentRecordSystem srs,
        String studentFilePath, String staffFilePath, String courseFilePath)
        throws FileNotFoundException, IllegalArgumentException {

        ArrayList<StudentFileEntry> studentFileEntryArray = loadStudentFile(studentFilePath);
        ArrayList<StaffFileEntry> staffFileEntryArray = loadStaffFile(staffFilePath);
        ArrayList<CourseFileEntry> courseFileEntryArray = loadCourseFile(courseFilePath);

        loadCourseOfferingsFromFile(mainWindow, srs, courseFileEntryArray);
        loadStudentsFromFile(mainWindow, srs, studentFileEntryArray);
        loadStaffFromFile(mainWindow, srs, staffFileEntryArray);
        loadEnrolmentsFromFile(mainWindow, srs, studentFileEntryArray);
        assignInstructorsFromFile(mainWindow, srs, courseFileEntryArray);
        loadPaymentsFromFile(mainWindow, srs, studentFileEntryArray);

    }

    // Turn student file into a list of student file entry objects
    private static ArrayList<StudentFileEntry> loadStudentFile(String filePath)
        throws FileNotFoundException, IllegalArgumentException {

        Scanner sc = new Scanner(new FileInputStream(filePath));
        ArrayList<StudentFileEntry> sfeArray = new ArrayList<StudentFileEntry>();

        while (sc.hasNextLine()) {

            StudentFileEntry sfe = new StudentFileEntry();

            String nextLine = sc.nextLine();
            StringTokenizer st = new StringTokenizer(nextLine, ",");
            for (int i = 0; i < SRS_IO.ENTRIES_ON_LINE_FOR_STUDENT_FILE; i++) {
                String token;
                try {
                    token = st.nextToken();
                } catch (NoSuchElementException e) {
                    sc.close();
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
                switch (i) {
                    case 0: {
                        sfe.setId(token);
                        break;
                    }
                    case 1: {
                        sfe.setName(token);
                        break;
                    }
                    case 2: {
                        sfe.setYear(token);
                        break;
                    }
                    case 3: {
                        sfe.setResultJP(token);
                        break;
                    }
                    case 4: {
                        sfe.setResultOOD(token);
                        break;
                    }
                    case 5: {
                        sfe.setResultST(token);
                        break;
                    }
                    case 6: {
                        sfe.setResultJ2EE(token);
                        break;
                    }
                    case 7: {
                        sfe.setResultSA(token);
                        break;
                    }
                    case 8: {
                        sfe.setResultDP(token);
                        break;
                    }
                    case 9: {
                        token = token.replace("$", "");
                        token = token.replace(",", "");
                        sfe.setFeePaid(token);
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("Error in file "
                            + SRS_IO.STUDENT_FILE_NAME);
                    }
                }
            }
            sfeArray.add(sfe);
        }
        sc.close();
        return sfeArray;
    }

    // Turn staff file into a list of staff file entry objects
    private static ArrayList<StaffFileEntry> loadStaffFile(String filePath)
        throws FileNotFoundException, IllegalArgumentException {

        Scanner sc = new Scanner(new FileInputStream(filePath));
        ArrayList<StaffFileEntry> sfeArray = new ArrayList<StaffFileEntry>();

        while (sc.hasNextLine()) {

            StaffFileEntry sfe = new StaffFileEntry();

            String nextLine = sc.nextLine();
            StringTokenizer st = new StringTokenizer(nextLine, ",");
            for (int i = 0; i < SRS_IO.ENTRIES_ON_LINE_FOR_STAFF_FILE; i++) {
                String token;
                try {
                    token = st.nextToken();
                } catch (NoSuchElementException e) {
                    sc.close();
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STAFF_FILE_NAME);
                }
                switch (i) {
                    case 0: {
                        sfe.setId(token);
                        break;
                    }
                    case 1: {
                        sfe.setName(token);
                        break;
                    }
                    case 2: {
                        if (token.contains("K")) {
                            token = token.concat("000");
                        }
                        token = token.replace("$", "");
                        token = token.replace(",", "");
                        token = token.replace("K", "");
                        sfe.setPay(token);
                        break;
                    }
                    case 3: {
                        sfe.setRole(token);
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("Error in file "
                            + SRS_IO.STAFF_FILE_NAME);
                    }
                }
            }
            sfeArray.add(sfe);
        }
        sc.close();
        return sfeArray;
    }

    // Turn course file into a list of course file entry objects
    private static ArrayList<CourseFileEntry> loadCourseFile(String filePath)
        throws FileNotFoundException, IllegalArgumentException {

        Scanner sc = new Scanner(new FileInputStream(filePath));
        ArrayList<CourseFileEntry> cfeArray = new ArrayList<CourseFileEntry>();

        while (sc.hasNextLine()) {

            CourseFileEntry cfe = new CourseFileEntry();

            String nextLine = sc.nextLine();
            StringTokenizer st = new StringTokenizer(nextLine, ",");
            for (int i = 0; i < SRS_IO.ENTRIES_ON_LINE_FOR_COURSE_FILE; i++) {
                String token;
                try {
                    token = st.nextToken();
                } catch (NoSuchElementException e) {
                    sc.close();
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.COURSE_FILE_NAME);
                }
                switch (i) {
                    case 0: {
                        cfe.setRunNumber(token);
                        break;
                    }
                    case 1: {
                        cfe.setInstructorJP(token);
                        break;
                    }
                    case 2: {
                        cfe.setInstructorOOD(token);
                        break;
                    }
                    case 3: {
                        cfe.setInstructorST(token);
                        break;
                    }
                    case 4: {
                        cfe.setInstructorJ2EE(token);
                        break;
                    }
                    case 5: {
                        cfe.setInstructorSA(token);
                        break;
                    }
                    case 6: {
                        cfe.setInstructorDP(token);
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("Error in file "
                            + SRS_IO.COURSE_FILE_NAME);
                    }
                }
            }
            cfeArray.add(cfe);
        }
        sc.close();
        return cfeArray;

    }

    // Load all course offerings into Student Record System
    private static void loadCourseOfferingsFromFile(MainWindow mainWindow,
        StudentRecordSystem srs, ArrayList<CourseFileEntry> courseFileEntryArray)
        throws IllegalArgumentException {

        // Load all JP course offerings
        for (int i = 0; i < courseFileEntryArray.size(); i++) {
            CourseFileEntry cfe = courseFileEntryArray.get(i);
            String instructor = cfe.getInstructorJP();
            if (!instructor.equals("-")) {
                String courseOfferingId = "JP-RUN-" + (i + 1);
                try {
                    srs.addCourseOffering("JP", courseOfferingId,
                        SRS_IO.CURRENT_YEAR);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.COURSE_FILE_NAME);
                }
            }
        }

        // Load all OOD course offerings
        for (int i = 0; i < courseFileEntryArray.size(); i++) {
            CourseFileEntry cfe = courseFileEntryArray.get(i);
            String instructor = cfe.getInstructorOOD();
            if (!instructor.equals("-")) {
                String courseOfferingId = "OOD-RUN-" + (i + 1);
                try {
                    srs.addCourseOffering("OOD", courseOfferingId,
                        SRS_IO.CURRENT_YEAR);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.COURSE_FILE_NAME);
                }
            }
        }

        // Load all ST course offerings
        for (int i = 0; i < courseFileEntryArray.size(); i++) {
            CourseFileEntry cfe = courseFileEntryArray.get(i);
            String instructor = cfe.getInstructorST();
            if (!instructor.equals("-")) {
                String courseOfferingId = "ST-RUN-" + (i + 1);
                try {
                    srs.addCourseOffering("ST", courseOfferingId,
                        SRS_IO.CURRENT_YEAR);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.COURSE_FILE_NAME);
                }
            }
        }

        // Load all J2EE course offerings
        for (int i = 0; i < courseFileEntryArray.size(); i++) {
            CourseFileEntry cfe = courseFileEntryArray.get(i);
            String instructor = cfe.getInstructorJ2EE();
            if (!instructor.equals("-")) {
                String courseOfferingId = "J2EE-RUN-" + (i + 1);
                try {
                    srs.addCourseOffering("J2EE", courseOfferingId,
                        SRS_IO.CURRENT_YEAR);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.COURSE_FILE_NAME);
                }
            }
        }

        // Load all SA course offerings
        for (int i = 0; i < courseFileEntryArray.size(); i++) {
            CourseFileEntry cfe = courseFileEntryArray.get(i);
            String instructor = cfe.getInstructorSA();
            if (!instructor.equals("-")) {
                String courseOfferingId = "SA-RUN-" + (i + 1);
                try {
                    srs.addCourseOffering("SA", courseOfferingId,
                        SRS_IO.CURRENT_YEAR);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.COURSE_FILE_NAME);
                }
            }
        }

        // Load all SA course offerings
        for (int i = 0; i < courseFileEntryArray.size(); i++) {
            CourseFileEntry cfe = courseFileEntryArray.get(i);
            String instructor = cfe.getInstructorDP();
            if (!instructor.equals("-")) {
                String courseOfferingId = "DP-RUN-" + (i + 1);
                try {
                    srs.addCourseOffering("DP", courseOfferingId,
                        SRS_IO.CURRENT_YEAR);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.COURSE_FILE_NAME);
                }
            }
        }

        mainWindow.refresh();

    }

    // Load all students into Student Record System
    private static void loadStudentsFromFile(MainWindow mainWindow,
        StudentRecordSystem srs,
        ArrayList<StudentFileEntry> studentFileEntryArray)
        throws IllegalArgumentException {
        for (int i = 0; i < studentFileEntryArray.size(); i++) {
            StudentFileEntry sfe = studentFileEntryArray.get(i);
            String id = sfe.getId().replaceFirst("S", "");
            String name = sfe.getName();
            String year = sfe.getYear();
            try {
                srs.addStudent(id, name, year);
            } catch (SRSException e) {
                throw new IllegalArgumentException("Error in file "
                    + SRS_IO.STUDENT_FILE_NAME);
            }
        }
        mainWindow.refresh();
    }

    // Load all staff into Student Record System
    private static void loadStaffFromFile(MainWindow mainWindow,
        StudentRecordSystem srs, ArrayList<StaffFileEntry> staffFileEntryArray)
        throws IllegalArgumentException {
        for (int i = 0; i < staffFileEntryArray.size(); i++) {
            StaffFileEntry sfe = staffFileEntryArray.get(i);

            boolean isStudentInstructor = isStudentInstructor(srs, sfe.getId());

            if (isStudentInstructor) {
                try {
                    String studentId = sfe.getId().replaceFirst("E", "S");
                    String staffId = sfe.getId();
                    String pay = sfe.getPay();
                    srs.addStudentInstructor(studentId, staffId, pay);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STAFF_FILE_NAME);
                }
            } else {
                String id = sfe.getId().replaceFirst("E", "");
                String name = sfe.getName();
                String role = sfe.getRole();
                EmploymentType employmentType;
                PositionType positionType;
                if (role.compareToIgnoreCase("Manager") == 0) {
                    employmentType = EmploymentType.Permanent;
                    positionType = PositionType.Manager;
                } else if (role.compareToIgnoreCase("Admin") == 0) {
                    employmentType = EmploymentType.Permanent;
                    positionType = PositionType.Administrator;
                } else if (role.compareToIgnoreCase("Instructor") == 0) {
                    employmentType = EmploymentType.Casual;
                    positionType = PositionType.Instructor;
                } else {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STAFF_FILE_NAME);
                }
                String pay = sfe.getPay();
                try {
                    srs.addStaff(id, name, employmentType, positionType, pay);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STAFF_FILE_NAME);
                }
            }

        }
        mainWindow.refresh();

    }

    // Load all student enrolments into Student Record System
    private static void loadEnrolmentsFromFile(MainWindow mainWindow,
        StudentRecordSystem srs,
        ArrayList<StudentFileEntry> studentFileEntryArray)
        throws IllegalArgumentException {

        for (StudentFileEntry sfe : studentFileEntryArray) {
            String result;

            // Create enrolment for course JP
            result = sfe.getResultJP();
            if (result.compareToIgnoreCase("Exp") == 0) {
                try {
                    srs.assignExemption(sfe.getId(), "JP");
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            } else if (result.contains("Run")) {
                String runId = result.replace("Run ", "RUN-");
                try {
                    srs.createEnrolment(sfe.getId(), "JP-" + runId);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            } else if (result.equals("-")) {
                // Do nothing
            } else {
                try {
                    srs.createEnrolment(sfe.getId(), "JP-RUN-1");
                    srs.completeEnrolment(sfe.getId(), "JP-RUN-1", result);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            }

            // Create enrolment for course OOD
            result = sfe.getResultOOD();
            if (result.compareToIgnoreCase("Exp") == 0) {
                try {
                    srs.assignExemption(sfe.getId(), "OOD");
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            } else if (result.contains("Run")) {
                String runId = result.replace("Run ", "RUN-");
                try {
                    srs.createEnrolment(sfe.getId(), "OOD-" + runId);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            } else if (result.equals("-")) {
                // Do nothing
            } else {
                try {
                    srs.createEnrolment(sfe.getId(), "OOD-RUN-1");
                    srs.completeEnrolment(sfe.getId(), "OOD-RUN-1", result);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            }

            // Create enrolment for course ST
            result = sfe.getResultST();
            if (result.compareToIgnoreCase("Exp") == 0) {
                try {
                    srs.assignExemption(sfe.getId(), "ST");
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            } else if (result.contains("Run")) {
                String runId = result.replace("Run ", "RUN-");
                try {
                    srs.createEnrolment(sfe.getId(), "ST-" + runId);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            } else if (result.equals("-")) {
                // Do nothing
            } else {
                try {
                    srs.createEnrolment(sfe.getId(), "ST-RUN-1");
                    srs.completeEnrolment(sfe.getId(), "ST-RUN-1", result);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            }

            // Create enrolment for course J2EE
            result = sfe.getResultJ2EE();
            if (result.compareToIgnoreCase("Exp") == 0) {
                try {
                    srs.assignExemption(sfe.getId(), "J2EE");
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            } else if (result.contains("Run")) {
                String runId = result.replace("Run ", "RUN-");
                try {
                    srs.createEnrolment(sfe.getId(), "J2EE-" + runId);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            } else if (result.equals("-")) {
                // Do nothing
            } else {
                try {
                    srs.createEnrolment(sfe.getId(), "J2EE-RUN-1");
                    srs.completeEnrolment(sfe.getId(), "J2EE-RUN-1", result);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            }

            // Create enrolment for course SA
            result = sfe.getResultSA();
            if (result.compareToIgnoreCase("Exp") == 0) {
                try {
                    srs.assignExemption(sfe.getId(), "SA");
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            } else if (result.contains("Run")) {
                String runId = result.replace("Run ", "RUN-");
                try {
                    srs.createEnrolment(sfe.getId(), "SA-" + runId);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            } else if (result.equals("-")) {
                // Do nothing
            } else {
                try {
                    srs.createEnrolment(sfe.getId(), "SA-RUN-1");
                    srs.completeEnrolment(sfe.getId(), "SA-RUN-1", result);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            }

            // Create enrolment for course DP
            result = sfe.getResultDP();
            if (result.compareToIgnoreCase("Exp") == 0) {
                try {
                    srs.assignExemption(sfe.getId(), "DP");
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            } else if (result.contains("Run")) {
                String runId = result.replace("Run ", "RUN-");
                try {
                    srs.createEnrolment(sfe.getId(), "DP-" + runId);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            } else if (result.equals("-")) {
                // Do nothing
            } else {
                try {
                    srs.createEnrolment(sfe.getId(), "DP-RUN-1");
                    srs.completeEnrolment(sfe.getId(), "DP-RUN-1", result);
                } catch (SRSException e) {
                    throw new IllegalArgumentException("Error in file "
                        + SRS_IO.STUDENT_FILE_NAME);
                }
            }

        }

        mainWindow.refresh();

    }

    // Assign instructors into Student Record System
    private static void assignInstructorsFromFile(MainWindow mainWindow,
        StudentRecordSystem srs, ArrayList<CourseFileEntry> courseFileEntryArray) {

        for (int i = 0; i < courseFileEntryArray.size(); i++) {

            String instructorName;
            String courseOfferingId;
            CourseFileEntry cfe = courseFileEntryArray.get(i);

            // Assign instructor for course offerings JP
            instructorName = cfe.getInstructorJP();
            if (!instructorName.equals("-") && !instructorName.equals("Null")) {
                courseOfferingId = "JP-RUN-" + (i + 1);
                assignInstructor(srs, instructorName, courseOfferingId);
            }

            // Assign instructor for course offerings OOD
            instructorName = cfe.getInstructorOOD();
            if (!instructorName.equals("-") && !instructorName.equals("Null")) {
                courseOfferingId = "OOD-RUN-" + (i + 1);
                assignInstructor(srs, instructorName, courseOfferingId);
            }

            // Assign instructor for course offerings ST
            instructorName = cfe.getInstructorST();
            if (!instructorName.equals("-") && !instructorName.equals("Null")) {
                courseOfferingId = "ST-RUN-" + (i + 1);
                assignInstructor(srs, instructorName, courseOfferingId);
            }

            // Assign instructor for course offerings J2EE
            instructorName = cfe.getInstructorJ2EE();
            if (!instructorName.equals("-") && !instructorName.equals("Null")) {
                courseOfferingId = "J2EE-RUN-" + (i + 1);
                assignInstructor(srs, instructorName, courseOfferingId);
            }

            // Assign instructor for course offerings SA
            instructorName = cfe.getInstructorSA();
            if (!instructorName.equals("-") && !instructorName.equals("Null")) {
                courseOfferingId = "SA-RUN-" + (i + 1);
                assignInstructor(srs, instructorName, courseOfferingId);
            }

            // Assign instructor for course offerings DP
            instructorName = cfe.getInstructorDP();
            if (!instructorName.equals("-") && !instructorName.equals("Null")) {
                courseOfferingId = "DP-RUN-" + (i + 1);
                assignInstructor(srs, instructorName, courseOfferingId);
            }

        }

        mainWindow.refresh();

    }

    // Assign instructors into Student Record System
    private static void assignInstructor(StudentRecordSystem srs,
        String instructorName, String courseOfferingId) {
        String staffId;
        if (isAStudent(srs, instructorName)) {
            Student student = getStudent(srs, instructorName);
            staffId = student.getId().replace("S", "E");
        } else {
            Staff staff = getStaff(srs, instructorName);
            staffId = staff.getId();
        }
        try {
            srs.assignInstructor(staffId, courseOfferingId);
        } catch (SRSException e) {
            throw new IllegalArgumentException("Error in file "
                + SRS_IO.COURSE_FILE_NAME);
        }
    }

    // Load payments into Student Record System
    private static void loadPaymentsFromFile(MainWindow mainWindow,
        StudentRecordSystem srs,
        ArrayList<StudentFileEntry> studentFileEntryArray) {

        for (StudentFileEntry sfe : studentFileEntryArray) {
            try {
                srs.processPayment(sfe.getId(), sfe.getFeePaid());
            } catch (SRSException e) {
                throw new IllegalArgumentException("Error in file "
                    + SRS_IO.STUDENT_FILE_NAME);
            }
        }

    }

    // Check for student by name
    private static boolean isAStudent(StudentRecordSystem srs, String name) {
        boolean result = false;
        Collection<Student> students = srs.getAllStudents();
        for (Student s : students) {
            if (s.getName().contains(name)) {
                result = true;
            }
        }
        return result;
    }

    // Get student by name
    private static Student getStudent(StudentRecordSystem srs, String name) {
        Student result = null;
        Collection<Student> students = srs.getAllStudents();
        for (Student s : students) {
            if (s.getName().contains(name)) {
                result = s;
            }
        }
        return result;
    }

    // Get staff by name
    private static Staff getStaff(StudentRecordSystem srs, String name) {
        Staff result = null;
        Collection<Staff> staff = srs.getAllStaff();
        for (Staff s : staff) {
            if (s.getName().contains(name)) {
                result = s;
            }
        }
        return result;
    }

    // Check if staff id is a student instructor
    private static boolean isStudentInstructor(StudentRecordSystem srs,
        String staffId) {
        boolean result = false;
        Collection<String> studentIds = srs.getAllStudentIds();
        if (studentIds.contains(staffId.replace("E", "S"))) {
            result = true;
        }
        return result;
    }
}
