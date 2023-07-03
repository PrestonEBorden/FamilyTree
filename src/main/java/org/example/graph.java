package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.*;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

public class graph {
    private JPanel panelMain;
    private static JButton helloWorldButton;


    public graph() {}
    public static void main(String[] args) throws InterruptedException {


        JFrame frame = new JFrame("frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(1,2));
        JLabel label = new JLabel("howdy");
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
        final View view = viewer.getDefaultView();
        panel.add((Component) view);
        viewer.enableAutoLayout();
        frame.add((Component) view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
            graph.setAutoCreate(true);
            graph.setAttribute("layout.force", 1.3);
        graph.setAttribute("layout.quality", 3);

        view.getCamera().setViewPercent(1);
        ControlManager controlManager = new ControlManager(view);
        controlManager.enableKeyboardControls(viewer);
        controlManager.enableMouseControls();
        label.setSize(10,10);
        frame.add(label);
        frame.setSize(1000,600);


        ChatBot chatBot = new ChatBot(allEmployees, view, graph);
        chatBot.routine(viewer);



    }
    private static String edgeNameFactory(String referrer, String referee) { return referrer + "---> " + referee; }
}