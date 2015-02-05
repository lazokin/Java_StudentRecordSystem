package view.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import model.StudentRecordSystem;
import model.courses.CourseOffering;
import model.enrolments.Enrolment;
import model.staff.EmploymentType;
import model.staff.PositionType;
import model.staff.interfaces.Instructor;
import model.staff.interfaces.Staff;
import model.students.Student;
import view.MainWindow;

public class SRS_Save {

    public SRS_Save() {
        super();
    }

    // Save all data from Student Record System to files
    public static void save(MainWindow mainWindow, StudentRecordSystem srs,
        String studentFilePath, String staffFilePath, String courseFilePath) {

        ArrayList<StudentFileEntry> studentFileEntryArray = createStudentFileEntryArray(srs);
        ArrayList<StaffFileEntry> staffFileEntryArray = createStaffFileEntryArray(srs);
        ArrayList<CourseFileEntry> courseFileEntryArray = createCourseFileEntryArray(srs);

        saveStudentDataToFile(studentFilePath, studentFileEntryArray);
        saveStaffDataToFile(staffFilePath, staffFileEntryArray);
        saveCourseDataToFile(courseFilePath, courseFileEntryArray);

    }

    private static ArrayList<StudentFileEntry> createStudentFileEntryArray(
        StudentRecordSystem srs) {

        Collection<Student> students = srs.getAllStudents();
        ArrayList<StudentFileEntry> studentFileEntryArray = new ArrayList<StudentFileEntry>();

        if (students != null) {
            for (Student s : students) {
                StudentFileEntry sfe = new StudentFileEntry();
                sfe.setId(s.getId());
                sfe.setName(s.getName());
                sfe.setYear(s.getYearOfBirth());
                sfe.setResultJP(getResultForCourse(s, "JP"));
                sfe.setResultOOD(getResultForCourse(s, "OOD"));
                sfe.setResultST(getResultForCourse(s, "ST"));
                sfe.setResultJ2EE(getResultForCourse(s, "J2EE"));
                sfe.setResultSA(getResultForCourse(s, "SA"));
                sfe.setResultDP(getResultForCourse(s, "DP"));
                sfe.setFeePaid("$".concat(String.valueOf(s.getTotalPayments())));
                studentFileEntryArray.add(sfe);
            }
        }
        return studentFileEntryArray;
    }

    private static ArrayList<StaffFileEntry> createStaffFileEntryArray(
        StudentRecordSystem srs) {

        Collection<Staff> staff = srs.getAllStaff();
        ArrayList<StaffFileEntry> staffFileEntryArray = new ArrayList<StaffFileEntry>();

        if (staff != null) {
            for (Staff s : staff) {
                StaffFileEntry sfe = new StaffFileEntry();
                sfe.setId(s.getId());
                sfe.setName(s.getName());
                String pay = String.valueOf(s.getPay());
                if (s.getEmploymentType() == EmploymentType.Permanent) {
                    pay = pay.replace("000", "");
                    pay = pay.concat("K");
                } else {
                    pay = "$".concat(pay);
                }
                sfe.setPay(pay);
                String role;
                if (s.getPositionType() == PositionType.Manager) {
                    role = "Manager";
                } else if (s.getPositionType() == PositionType.Administrator) {
                    role = "Admin";
                } else {
                    role = "Instructor";
                }
                sfe.setRole(role);

                staffFileEntryArray.add(sfe);
            }
        }
        return staffFileEntryArray;
    }

    private static ArrayList<CourseFileEntry> createCourseFileEntryArray(
        StudentRecordSystem srs) {

        Collection<String> courseOfferingsIds = srs.getAllCourseOfferingIds();
        Collection<CourseOffering> courseOfferings = srs
            .getAllCourseOfferings();
        ArrayList<CourseFileEntry> courseFileEntryArray = new ArrayList<CourseFileEntry>();

        for (int i = 0; i < 5; i++) {
            CourseFileEntry cfe = new CourseFileEntry();
            String coureOfferingId;
            String instructorName;
            String runNumber = String.valueOf(i + 1);
            cfe.setRunNumber(runNumber);

            // Get instructor for course offering JP
            coureOfferingId = "JP-RUN-" + runNumber;
            if (courseOfferingsIds.contains(coureOfferingId)) {
                Instructor instructor = getCourseOffering(courseOfferings,
                    coureOfferingId).getInstructor();
                if (instructor == null) {
                    instructorName = "Null";
                } else {
                    instructorName = ((Staff) instructor).getName();
                    instructorName = instructorName.split(" ")[0];
                }

            } else {
                instructorName = "-";
            }
            cfe.setInstructorJP(instructorName);

            // Get instructor for course offering OOD
            coureOfferingId = "OOD-RUN-" + runNumber;
            if (courseOfferingsIds.contains(coureOfferingId)) {
                Instructor instructor = getCourseOffering(courseOfferings,
                    coureOfferingId).getInstructor();
                instructorName = ((Staff) instructor).getName();
                instructorName = instructorName.split(" ")[0];
            } else {
                instructorName = "-";
            }
            cfe.setInstructorOOD(instructorName);

            // Get instructor for course offering ST
            coureOfferingId = "ST-RUN-" + runNumber;
            if (courseOfferingsIds.contains(coureOfferingId)) {
                Instructor instructor = getCourseOffering(courseOfferings,
                    coureOfferingId).getInstructor();
                instructorName = ((Staff) instructor).getName();
                instructorName = instructorName.split(" ")[0];
            } else {
                instructorName = "-";
            }
            cfe.setInstructorST(instructorName);

            // Get instructor for course offering J2EE
            coureOfferingId = "J2EE-RUN-" + runNumber;
            if (courseOfferingsIds.contains(coureOfferingId)) {
                Instructor instructor = getCourseOffering(courseOfferings,
                    coureOfferingId).getInstructor();
                instructorName = ((Staff) instructor).getName();
                instructorName = instructorName.split(" ")[0];
            } else {
                instructorName = "-";
            }
            cfe.setInstructorJ2EE(instructorName);

            // Get instructor for course offering SA
            coureOfferingId = "SA-RUN-" + runNumber;
            if (courseOfferingsIds.contains(coureOfferingId)) {
                Instructor instructor = getCourseOffering(courseOfferings,
                    coureOfferingId).getInstructor();
                instructorName = ((Staff) instructor).getName();
                instructorName = instructorName.split(" ")[0];
            } else {
                instructorName = "-";
            }
            cfe.setInstructorSA(instructorName);

            // Get instructor for course offering DP
            coureOfferingId = "DP-RUN-" + runNumber;
            if (courseOfferingsIds.contains(coureOfferingId)) {
                Instructor instructor = getCourseOffering(courseOfferings,
                    coureOfferingId).getInstructor();
                instructorName = ((Staff) instructor).getName();
                instructorName = instructorName.split(" ")[0];
            } else {
                instructorName = "-";
            }
            cfe.setInstructorDP(instructorName);

            courseFileEntryArray.add(cfe);

        }
        return courseFileEntryArray;
    }

    private static void saveStudentDataToFile(String studentFilePath,
        ArrayList<StudentFileEntry> studentFileEntryArray) {

        File file = new File(studentFilePath);

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);

        pw.print("");

        if (!studentFileEntryArray.isEmpty()) {
            for (StudentFileEntry sfe : studentFileEntryArray) {
                writeStudentFileEntryToFile(pw, sfe);
                pw.print("\n");
            }
        }

        pw.close();
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void saveStaffDataToFile(String staffFilePath,
        ArrayList<StaffFileEntry> staffFileEntryArray) {

        File file = new File(staffFilePath);

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);

        pw.print("");

        if (!staffFileEntryArray.isEmpty()) {
            for (StaffFileEntry sfe : staffFileEntryArray) {
                writeStaffFileEntryToFile(pw, sfe);
                pw.print("\n");
            }
        }

        pw.close();
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void saveCourseDataToFile(String courseFilePath,
        ArrayList<CourseFileEntry> courseFileEntryArray) {

        File file = new File(courseFilePath);

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);

        pw.print("");

        if (!courseFileEntryArray.isEmpty()) {
            for (CourseFileEntry cfe : courseFileEntryArray) {
                writeCourseFileEntryToFile(pw, cfe);
                pw.print("\n");
            }
        }

        pw.close();
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeStudentFileEntryToFile(PrintWriter pw,
        StudentFileEntry sfe) {
        pw.print(sfe.getId());
        pw.print(",");
        pw.print(sfe.getName());
        pw.print(",");
        pw.print(sfe.getYear());
        pw.print(",");
        pw.print(sfe.getResultJP());
        pw.print(",");
        pw.print(sfe.getResultOOD());
        pw.print(",");
        pw.print(sfe.getResultST());
        pw.print(",");
        pw.print(sfe.getResultJ2EE());
        pw.print(",");
        pw.print(sfe.getResultSA());
        pw.print(",");
        pw.print(sfe.getResultDP());
        pw.print(",");
        pw.print(sfe.getFeePaid());
    }

    private static void writeStaffFileEntryToFile(PrintWriter pw,
        StaffFileEntry sfe) {
        pw.print(sfe.getId());
        pw.print(",");
        pw.print(sfe.getName());
        pw.print(",");
        pw.print(sfe.getPay());
        pw.print(",");
        pw.print(sfe.getRole());
    }

    private static void writeCourseFileEntryToFile(PrintWriter pw,
        CourseFileEntry cfe) {
        pw.print(cfe.getRunNumber());
        pw.print(",");
        pw.print(cfe.getInstructorJP());
        pw.print(",");
        pw.print(cfe.getInstructorOOD());
        pw.print(",");
        pw.print(cfe.getInstructorST());
        pw.print(",");
        pw.print(cfe.getInstructorJ2EE());
        pw.print(",");
        pw.print(cfe.getInstructorSA());
        pw.print(",");
        pw.print(cfe.getInstructorDP());
    }

    private static String getResultForCourse(Student student, String courseId) {

        if (student.hasExemption(courseId)) {
            return "Exp";
        }

        if (student.hasCurrentEnrolmentInCourse(courseId)) {
            Enrolment e = student.getCurrentEnrolmentForCourse(courseId);
            String courseOfferingId = e.getCourseOffering().getId();
            String runNumber = courseOfferingId.replace(courseId + "-RUN-", "");
            return "Run " + runNumber;
        }

        if (student.hasCompletedEnrolmentInCourse(courseId)) {
            Enrolment e = student.getCompletedEnrolmentForCourse(courseId);
            return String.valueOf(e.getGrade());
        }

        return "-";

    }

    private static CourseOffering getCourseOffering(
        Collection<CourseOffering> courseOfferings, String courseOfferingId) {
        CourseOffering result = null;
        for (CourseOffering co : courseOfferings) {
            if (co.getId().equals(courseOfferingId)) {
                result = co;
            }
        }
        return result;
    }

}
