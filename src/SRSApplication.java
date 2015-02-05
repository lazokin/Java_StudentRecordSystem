import java.io.FileNotFoundException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import model.StudentRecordSystem;
import model.exceptions.SRSException;
import view.MainWindow;
import view.io.SRS_Load;

public class SRSApplication {

    public static void main(final String[] args) throws SRSException {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
            | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        final StudentRecordSystem srs = new StudentRecordSystem();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow mainWindow = new MainWindow(srs);
                // Process input arguments
                if (args.length > 0) {
                    try {
                        SRS_Load.load(mainWindow, srs, args[0], args[1],
                            args[2]);
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(mainWindow, e
                            .getMessage(), "Load From Files Error",
                            JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(mainWindow, e
                            .getMessage(), "Load From Files Error",
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // HARD CODED INTO PROGRAM (NOT READ FROM FILE)

        // courses
        srs.addStandardCourse("JP", "Java Programming", "500");
        srs.addStandardCourse("OOD", "Object-Oriented Design", "500");
        srs.addStandardCourse("ST", "Software Testing", "500");
        srs.addAdvancedCourse("J2EE", "J2EE", "500");
        srs.addAdvancedCourse("SA", "Software Architecture", "500");
        srs.addAdvancedCourse("DP", "Design Patterns", "500");

        // course prerequisites
        srs.addPrerequisite("J2EE", "JP");
        srs.addPrerequisite("J2EE", "OOD");
        srs.addPrerequisite("J2EE", "ST");
        srs.addPrerequisite("SA", "JP");
        srs.addPrerequisite("SA", "OOD");
        srs.addPrerequisite("SA", "ST");
        srs.addPrerequisite("DP", "JP");
        srs.addPrerequisite("DP", "OOD");
        srs.addPrerequisite("DP", "ST");

    }

}
