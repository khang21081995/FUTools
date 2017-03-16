/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Helper.ValidationAndNormalizingText;
import java.lang.reflect.Array;
import java.util.*;

/**
 *
 * @author khang
 */
public class QuestionAndAnswer {

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }
    private String question;
    private List<String> answer;
    private String choice;
    private String chapter;

    public QuestionAndAnswer() {
        question = "";
        answer = new ArrayList<>();
        choice = "";
        chapter = "";

    }

    public void addAnswer(String ans, int number) {
        try {
            ans = ValidationAndNormalizingText.removeUnnecessaryBlank(ans);
            if (ans.substring(number).trim().equals("")) {
                return;
            } else {
                answer.add(ans);
            }

        } catch (Exception e) {
            return;
        }

    }

    public String getString() {
        int count = 0;
        for (int i = 0; i < question.length(); i++) {
            if (question.charAt(i + 1) == ' ') {
                break;
            }
            count++;
        }
        String temp = question.substring(count+1).trim();
        String ret = temp + "\r\n";
        for (String string : answer) {
            ret += string + "\r\n";
        }

        return ret;
    }

    @Override
    public String toString() {
        String ret = choice + "\r\n";
        ret += question + "\r\n";
        for (String string : answer) {
            ret += string + "\r\n";
        }

        return ret;
    }

}
