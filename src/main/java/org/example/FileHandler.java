package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.*;
import org.graphstream.ui.geom.Point2;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.camera.Camera;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.Scanner;
public class FileHandler
{
    private String filePath;
    private String format;
    public FileHandler(String filePath, String format) {
        this.filePath = filePath;
        this.format = format;
    }
    public FileHandler(String filePath) {
        this.filePath = filePath;
        this.format ="xlsx";
    }

    public Sheet getExcelSheet()
    {
        try (FileInputStream stream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(stream)) {
             return workbook.getSheetAt(0);
        }
        catch
        (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
