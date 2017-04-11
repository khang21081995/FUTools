/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Entity.QandA;
import Ultility.IOFileHelper;
import Ultility.UTF8FileUtility;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KhangPQ
 */
public class ProcessApp {

    ArrayList<QandA> listQA;
    IOFileHelper io;
    String fileOutput;

    public ProcessApp(String fileInput, String fileOutput) {
        listQA = new ArrayList<>();
        io = new IOFileHelper(fileInput, fileOutput);
        this.fileOutput = fileOutput;
    }

    public void run() {
        int countLine = 0;
        int countRemove = 0;
        int countQA = 0;
        ArrayList<String> lines = io.getListStringFromFile();
//        System.out.println(lines.get(1));
        UTF8FileUtility.createWriter(fileOutput);
        for (String line : lines) {
//            System.out.println(line);
            try {
                UTF8FileUtility.writer.append(line + "\r\n");
            } catch (IOException ex) {
            }
            String qAndA[] = line.replace("|", "~~").split("~~");
            if (qAndA.length != 2 || qAndA[0].equals("")) {
                continue;
            }

            countLine++;
            QandA qa = new QandA();
            qa.setQuestion(qAndA[0]);
            qa.setAnswer(qAndA[1]);
//            System.out.println(countQA+" : "+qa.toString());
            boolean flag = true;
            if (listQA.isEmpty()) {
                listQA.add(qa);
                countQA++;
            } else {
                for (QandA qandA : listQA) {
                    if (qandA.compareTo(qa) == 0) {
                        countRemove++;
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    listQA.add(qa);
                    countQA++;
                }
            }
        }
        //      io.writeListQA(listQA);
        int countWrite = 0;
        UTF8FileUtility.createWriter("out.txt");
        for (QandA qandA : listQA) {
            countWrite++;
            System.out.println(qandA.toString());
            UTF8FileUtility.writeln(qandA.toString());
        }
        UTF8FileUtility.closeWriter();
        System.out.println("Count Remove: " + countRemove);
        System.out.println("Count Write: " + countWrite);

        System.out.println("Count QA: " + countQA);
        System.out.println("Count Line: " + countLine);

    }

}
