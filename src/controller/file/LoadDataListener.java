package controller.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.StudentRecordSystem;
import view.MainWindow;
import view.io.SRS_IO;

public class LoadDataListener implements ActionListener {

    private MainWindow mainWindow;
    private StudentRecordSystem srs;

    public LoadDataListener(MainWindow mainWindow, StudentRecordSystem srs) {
        this.srs = srs;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SRS_IO.loadFromFiles(mainWindow, srs);
    }

}
