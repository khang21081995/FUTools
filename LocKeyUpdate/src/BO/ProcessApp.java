/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import Entity.QandA;
import Ultility.IOFileHelper;
import java.util.ArrayList;

/**
 *
 * @author khang
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

    public int removeDupplicate() {
        int countDup = 0;
        for (int i = 0; i < listQA.size() - 1; i++) {

            for (int j = i + 1; j < listQA.size(); j++) {
                try {
                    while (listQA.get(i).equals(listQA.get(j))) {
                        System.out.println("Remove: " + listQA.get(j).getQuestion());
                        listQA.remove(j);
                        countDup++;
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return countDup;
    }

    public void run() {
        int countLine = 0;
        int countRemove = 0;
        ArrayList<String> lines = io.getListStringFromFile();
        for (String line : lines) {
            countLine++;
            if (!line.contains("|")) {
                countRemove++;
                continue;
            }
            String qAndA[] = line.replace("|", "~~").split("~~");
            if (qAndA.length != 2 || qAndA[0].trim().length() == 0) {
                countRemove++;
                continue;
            }
            QandA qa = new QandA();
            qa.setQuestion(qAndA[0]);
            qa.setAnswer(qAndA[1]);
            listQA.add(qa);
        }
        countRemove += removeDupplicate();       
        countRemove += removeDupplicate();

        io.writeListQA(listQA);
        System.err.println("Count Remove: " + countRemove);
        System.err.println("Count Line: " + countLine);        
        System.err.println("Count QA: " + listQA.size());

    }

}
