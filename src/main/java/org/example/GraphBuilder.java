package org.example;

import org.apache.poi.ss.usermodel.Sheet;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.algorithm.Toolkit;

import java.util.ArrayList;
import java.util.List;

public class GraphBuilder
{
    private final Sheet sheet;
    private final MultiGraph graph;
    public GraphBuilder(Sheet sheet, MultiGraph graph)
    {
        this.sheet = sheet;
        this.graph = graph;
        graph.setAttribute("ui.style", "fill-color: #333333;");
    }
    public void showGraph()
    {
        Viewer viewer = graph.display();
    }
    public List<String> nodeGenerator(String referrer, String referee, List<String> people)
    {

        if (!(people.contains(referrer))) {
            Node n = graph.addNode(referrer);
            n.setAttribute("ui.label", referrer);
            n.setAttribute("ui.style", "fill-color: #A020F0; \n size:5px; \n text-style: bold-italic; \n text-alignment:center; " +
                    "\n text-background-mode:rounded-box;\n text-background-color:#C0E4D6;\n text-size:9;\n text-color: BLUE;");
           n.setAttribute("layout.weight", 100);
            people.add(referrer);
        }

        if (!(people.contains(referee))) {

            Node n = graph.addNode(referee);
            n.setAttribute("ui.style", "fill-color: #FFA500;\n" +
                    "size: 5px;\n text-style: bold-italic; \n text-alignment: center;\n text-background-mode:rounded-box;\n text-background-color:rgb(235,212,235); \n text-size:9; \n text-color: PURPLE;");
            n.setAttribute("ui.label", referee);
            n.setAttribute("layout.weight", 100);
            people.add(referee);
        }
        return people;
    }
    public List<Node> generateEdgeBetween(String referrer, String referee,List<Node> allEmployees)
    {
        Edge link = graph.addEdge(edgeNameFactory(referrer, referee), referrer, referee);
        link.setAttribute("ui.style", "size: 2px;");
        link.setAttribute("layout.weight", 25);
        Node referrerNode = link.getNode0();
        Node refereeNode = link.getNode1();


        return nodeListCheck(referrerNode,refereeNode,allEmployees);

    }

    public List<Node> nodeListCheck (Node referrerNode, Node refereeNode, List<Node> allEmployees)
    {
        if (!allEmployees.contains(referrerNode)) {
            allEmployees.add(referrerNode);
        }
        allEmployees.add(refereeNode);
        return allEmployees;
    }
    public Object changeNodeColor(String rgb, Node node)
    {
        Object originalStyle = node.getAttribute("ui.style");
        node.setAttribute("ui.style", "text-color: YELLOW;");
        return originalStyle;
    }

    private String edgeNameFactory(String referrer, String referee) {
        return referrer + "---> " + referee;
    }
}
