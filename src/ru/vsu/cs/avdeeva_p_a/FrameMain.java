package ru.vsu.cs.avdeeva_p_a;

import utils.ArrayUtils;
import utils.JTableUtils;
import utils.SwingUtils;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.List;

public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableInput;
    private JScrollPane scrollPaneTableInput;
    private JButton buttonLoadInputFromFile;
    private JButton buttonRandomInput;
    private JButton buttonExecute;
    private JTable tableOutput;
    private JScrollPane scrollPaneTableOutput;

    private JFileChooser fileChooserOpen;

    public FrameMain() {
        this.setTitle("Task_6");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(700, 100, 300, 300);
        this.setResizable(true);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 30, false, true, false, true, 30, 30);
        JTableUtils.initJTableForArray(tableOutput, 30, false, true, false, false, 30, 30);

        tableInput.setRowHeight(30);
        tableOutput.setRowHeight(30);

        fileChooserOpen = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);

        buttonLoadInputFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[] arr = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                        assert arr != null;
                        JTableUtils.writeArrayToJTable(tableInput, arr, "%d");
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonRandomInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[][] matrix = ArrayUtils.createRandomIntMatrix(
                            tableInput.getRowCount(), tableInput.getColumnCount(), -10, 20);
                    JTableUtils.writeArrayToJTable(tableInput, matrix, "%d");
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int sum = 5;
                    int[] arr = JTableUtils.readIntArrayFromJTable(tableInput);
                    List<Integer> resultArr = FindPairs.getDifferentPairs(arr, sum);
                    int[] result = ArrayUtils.intListToArray(resultArr);
                    JTableUtils.writeArrayToJTable(tableOutput, result, "%d");
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }
}