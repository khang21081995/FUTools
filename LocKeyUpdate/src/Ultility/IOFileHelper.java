/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ultility;

//import Entity.QandA;
import Entity.QandA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KhangPQ
 */
public class IOFileHelper {

    private BufferedWriter writer = null;

    private BufferedReader reader = null;

    private final Charset charset = Charset.forName("utf-8");

    private String input;
    private String output;

    public IOFileHelper(String fileInput, String fileOutput) {
        input = fileInput;
        output = fileOutput;
    }

    public void closeReader() {
        try {
            if (reader != null) {
                reader.close();
                reader = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createReader(InputStream inputStream) {
        try {
            // first, try to close the reader if it has already existed and has
            // not been closed
            closeReader();
            // create the reader
            Reader iReader = new InputStreamReader(inputStream, charset);
            reader = new BufferedReader(iReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createWriter(OutputStream outputStream) {
        try {
            // first, try to close the writer if it has already existed and has
            // not been closed
            closeWriter();
            Writer oWriter = new OutputStreamWriter(outputStream, charset);
            writer = new BufferedWriter(oWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeWriter() {
        try {
            // flush and close the writer
            if (writer != null) {
                writer.flush();
                writer.close();
                writer = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createReader(String filename) {
        try {
            createReader(new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createWriter(String filename) {
        try {
            createWriter(new FileOutputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String readLine() {
        try {
            return reader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getListStringFromFile() {
        ArrayList<String> lines = new ArrayList<String>();
        if (reader == null) {
            createReader(input);
        }
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.trim().length() > 0) {
                    lines.add(line.trim().replaceAll("\\s+", " "));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Error err) {
            System.err.println(err.getMessage());
        }
        closeReader();
        return lines;
    }

    public void writeListString(List<String> listString) {
        if (writer == null) {
            createWriter(output);
        }

        try {
            for (String string : listString) {
                writer.append(string);
            }
        } catch (Exception e) {
        }
        closeWriter();
    }

    public void writeLine(String in) {
        if (writer == null) {
            createWriter(output);
        }

        try {
            writer.append(in + "\r\n");
        } catch (Exception e) {
        }
        //closeWriter();
    }

    public void writeListQA(ArrayList<QandA> lQA) {
        Path file = Paths.get(output);
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            int count = 0;
            for (QandA q : lQA) {
            //    System.out.println(count++ + " : " + q.toString());

                writer.append(q.toString() + "\r\n");
            }
            writer.close();
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

}
