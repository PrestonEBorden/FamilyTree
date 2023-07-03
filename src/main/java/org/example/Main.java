package org.example;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import javax.swing.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.*;
public class Main {
    public static class Tutorial1 {

    }
    public static void main(String[] args) throws IOException
    {



        String filePath = "src/main/resources/readable/Family.xlsx";

        try (FileInputStream stream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(stream))
        {
            Sheet sheet = workbook.getSheetAt(0);
            List<String> people = new ArrayList<>();

            for (Row row : sheet)
            {
                String referrer = row.getCell(0).toString();
                String referee = row.getCell(1).toString();

                if(!(people.contains(referrer)))
                {

                }
               // hashMap.put(key,new ArrayList<Node>());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


     /*   HashMap< String, ArrayList<Node> > hashMap = new HashMap<>();

        try (FileInputStream stream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(stream))
        {
            Tree tree = new Tree();

            Sheet sheet = workbook.getSheetAt(0);

            String curKey = null;
            ArrayList<Node> children = new ArrayList<>();

            for (Row row : sheet)
            {
                String key = row.getCell(0).toString();
                hashMap.put(key,new ArrayList<Node>());
            }
            for (Row row : sheet)
            {
                //Node key = new Node(row.getCell(0).toString(), null);
                String key = row.getCell(0).toString();
                Node value = new Node(row.getCell(1).toString(), null);
                hashMap.get(key).add(value);
            }
        System.out.println(hashMap);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

      */

    }
}

