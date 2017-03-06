/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Entity.Name;
import Ultil.UTF8FileUtility;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khang
 */
public class App {

    ArrayList<Name> nameList;
    private UTF8FileUtility utf;
    private String inputFile;
    private String outputFile;

    public App(String inputFile, String outputFile) {
        nameList = new ArrayList<>();
        utf = new UTF8FileUtility();
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public void runApp() {
        int count = 0;
        ArrayList<String> fnameList = (ArrayList<String>) UTF8FileUtility.getLineList(inputFile);
        for (String string : fnameList) {
            Name temp = new Name(string);
            nameList.add(temp);
        }
        UTF8FileUtility.createWriter(outputFile);

        for (Name name : nameList) {
            System.out.println(name.getFUName());
            UTF8FileUtility.writeln(name.getFUName());
            count++;
        }
        UTF8FileUtility.closeWriter();
        System.err.println("Name count = " + count);
    }
}
