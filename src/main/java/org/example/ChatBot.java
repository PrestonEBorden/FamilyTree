package org.example;
import java.util.List;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.*;
import org.graphstream.stream.ProxyPipe;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import java.util.Scanner;

import static org.graphstream.ui.graphicGraph.GraphPosLengthUtils.nodePointPosition;

public class ChatBot
{
    private final List<Node> allEmployees;
    private final View view;
    private final Graph graph;
    public ChatBot(List<Node> allEmployees, View view, Graph graph)
    {
        this.allEmployees = allEmployees;
        this.view = view;
        this.graph = graph;
    }

    public void routine(Viewer viewer) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Which employee are you looking for?");
        System.out.println("Please say \"exit\" when you are done.");

        while (scan.hasNext()) {
            String employeeName = scan.nextLine();
            if (employeeName.equals("exit") || employeeName.equals("no"))
                System.exit(0);
            boolean found = false;
            Node currentSearchedNode = null;
            for (Node node : allEmployees) {

                String iterator = node.toString();
                if (iterator.equals(employeeName)) {
                    try {
                        if(currentSearchedNode!=null){ currentSearchedNode.setAttribute("ui.style", "fill-color: #A020F0; \n size:5px; \n text-style: bold-italic; \n text-alignment:at-left; " +
                                "\n text-background-mode:rounded-box;\n text-background-color:#C0E4D6;\n text-size:7;\n text-color: BLUE;");}
                        routineFound(node, employeeName,viewer);
                        currentSearchedNode = node;
                        node.setAttribute("ui.style","fill-color:GREEN; \n size: 15px;");
                    }
                    catch (InterruptedException e) {throw new RuntimeException(e);}
                    found = true;
                }
            }
            if (!found) {System.out.println("Sorry, this person is not displayed.");}// switch to using a text-field

            System.out.println("Are you looking for anyone else?");
        }
    }

    public void routineFound(Node node, String employeeName, Viewer viewer) throws InterruptedException { ProxyPipe pipe = viewer.newViewerPipe();
        pipe.addAttributeSink(graph);

        System.out.println("Found " + employeeName + "!");
        while (true)
        {
            Thread.sleep(100);
            pipe.pump();
            Point3 nodeLocation = Toolkit.nodePointPosition(node);
            System.out.println(nodeLocation);
            view.getCamera().setViewCenter(nodeLocation.x,nodeLocation.y,nodeLocation.z);
            view.getCamera().setViewPercent(0.14);
            break;
        }

    }
}
