/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Entity.QuestionAndAnswer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khang
 */
public class IOHelper {

    private static QuestionAndAnswer QAndA;
    public static List<QuestionAndAnswer> lQAndA = new ArrayList<>();
    public static String QUESTIONPATH = "QN = ";
    public static String ANSWERPATH = "ANS:";
    public static String TEMPPATH = "a.";

    public static String CHAPTERPATH = "CHAPTER:";
    public static String REGEX1 = ":::";
    public static String REGEX2 = "---";
    public static int NUMBEROFSUBINANSWER = 2;

    public IOHelper() {
        QAndA = new QuestionAndAnswer();
    }
    static Charset charset = Charset.forName("utf-8");

    public static void writeFile(Path file) {
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            for (QuestionAndAnswer q : lQAndA) {
                writer.append(q.toString().toLowerCase().replace(QUESTIONPATH.toLowerCase(), REGEX1 + QUESTIONPATH).replace(ANSWERPATH.toLowerCase(), REGEX2));
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public static void readFile(Path file) {
        int count = 0;
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            QAndA = new QuestionAndAnswer();
            String line = null;
            boolean flagTemp = true;
            while ((line = reader.readLine()) != null) {
                line = ValidationAndNormalizingText.removeUnnecessaryBlank(line);
                if (line.toLowerCase().trim().startsWith(QUESTIONPATH.toLowerCase())) {
                    flagTemp = true;
                    QAndA.setQuestion(line);
                    QAndA.getAnswer().clear();
                    while ((line = reader.readLine()) != null && flagTemp) {
                        line = ValidationAndNormalizingText.removeUnnecessaryBlank(line);
                        if (line.toLowerCase().trim().startsWith(TEMPPATH.toLowerCase().trim())) {
                            QAndA.addAnswer(line, NUMBEROFSUBINANSWER);
                            flagTemp = false;
                        } else {
                            QAndA.setQuestion(QAndA.getQuestion() + " " + line);
                        }
                    }
                    count++;
                } else if (line.toLowerCase().trim().startsWith(ANSWERPATH.toLowerCase())) {
                    QAndA.setChoice(line);
                    lQAndA.add(QAndA);
                    QAndA = new QuestionAndAnswer();

                } else if (line.toLowerCase().trim().startsWith(CHAPTERPATH.toLowerCase())) {
                    QAndA.setChapter(line);
                } else if (!QAndA.getQuestion().equals("")) {
                    QAndA.addAnswer(line, NUMBEROFSUBINANSWER);
                }
            }

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        for (QuestionAndAnswer q : lQAndA) {
            System.out.println(q.toString());
            // System.out.println(q.getString());

        }
        System.out.println("Count = " + count);
    }

    public static void removeDupplicate() {
        for (int i = 0; i < lQAndA.size(); i++) {
            for (int j = i + 1; j < lQAndA.size() - 1; j++) {
                while (lQAndA.get(j).getString().equalsIgnoreCase(lQAndA.get(i).getString())) {
                    System.out.println("Remove " + lQAndA.get(j).getString());
                    lQAndA.remove(j);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Path filePath;
        filePath = Paths.get("input.txt");
        readFile(filePath);
        filePath = Paths.get("output.txt");
        Files.deleteIfExists(filePath);
        //removeDupplicate();
        writeFile(filePath);
    }
}
