/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import UI.Main;

/**
 *
 * @author KhangPQ
 */
public class QandA implements Comparable<QandA> {

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question.trim().replaceAll("\\s+", " ");
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer.trim().replaceAll("\\s+", " ");
    }
    private String question;
    private String answer;
    public QandA() {
    }

    @Override
    public String toString() {
        return question + " | " + answer;
    }

    @Override
    public int compareTo(QandA o) {
        //  System.out.println("Substring: "+question.substring(2));

        if (o.getQuestion().substring(Main.SUBSTRINGNUMBER).toLowerCase().contains(question.toLowerCase().substring(Main.SUBSTRINGNUMBER)) || question.toLowerCase().substring(Main.SUBSTRINGNUMBER).contains(o.getQuestion().toLowerCase().substring(Main.SUBSTRINGNUMBER))) {
            if (o.getAnswer().toLowerCase().contains(answer.toLowerCase()) || answer.toLowerCase().contains(o.getAnswer().toLowerCase())) {
                return 0;
            }
        }
        return 1;
    }

}
