/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BO.ProcessApp;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author KhangPQ
 */
public class Main {

    public static void main(String[] args) {

        ProcessApp process;
        String INPUT_PATH = ".\\inputTXT\\";
        String OUTPUT_PATH = ".\\outputTXT\\";

        File folder = new File(INPUT_PATH);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.err.println(file.getName());
                try {
                    process = new ProcessApp(INPUT_PATH + file.getName(), OUTPUT_PATH + file.getName().toLowerCase());
                    process.run();
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }

    }
}
