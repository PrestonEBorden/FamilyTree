package org.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.*;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

public class graph {
    private JPanel panelMain;
    private static JButton helloWorldButton;


    public graph() {}
    public static void main(String[] args) throws InterruptedException {
        List<Node> allEmployees = new ArrayList<>();
        System.setProperty("org.graphstream.ui", "swing");
        var graph = new MultiGraph("Tutorial 1");
        String filePath = "src/main/resources/readable/Family.xlsx";
        FileHandler fileHandler = new FileHandler(filePath);
        Sheet sheet = fileHandler.getExcelSheet();
        List<String> people = new ArrayList<>();
            for (Row row : sheet) {
                String referrer = row.getCell(0).toString(), referee = row.getCell(1).toString();
                if (referrer.equals("N/A")){ continue; }
                else {
                    GraphBuilder graphBuilder = new GraphBuilder(sheet, graph);
                    people = graphBuilder.nodeGenerator(referrer, referee, people);
                    graphBuilder.generateEdgeBetween(referrer, referee, allEmployees);
                }
            }
        Viewer viewer = graph.display();
            graph.setAutoCreate(true);
            graph.setAttribute("layout.force", 1.3);
        graph.setAttribute("layout.quality", 3);
        final View view = viewer.getDefaultView();
        view.getCamera().setViewPercent(1);
        ControlManager controlManager = new ControlManager(view);
        controlManager.enableKeyboardControls(viewer);
        controlManager.enableMouseControls();
        ChatBot chatBot = new ChatBot(allEmployees, view, graph);
        chatBot.routine(viewer);
    }
    private static String edgeNameFactory(String referrer, String referee) { return referrer + "---> " + referee; }
}