/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author KhangPQ
 */
public class QandA {

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
    public boolean equals(Object obj) {
        QandA o;
        try {
            o = (QandA) obj;
        } catch (Exception e) {
            return false;
        }
        String temp;
        if (o.getQuestion().contains(" ")) {
            int count = 0;
            for (int i = 0; i < o.getQuestion().length(); i++) {
                if (o.getQuestion().charAt(i + 1) == ' ') {
                    break;
                }
                count++;
            }
            temp = o.getQuestion().substring(count + 1).toLowerCase().trim();
        } else {
            temp = o.getQuestion().toLowerCase().trim();
        }

        String temp2;
        if (o.getAnswer().contains(" ")) {
            int count = 0;
            for (int i = 0; i < o.getAnswer().length(); i++) {
                if (o.getAnswer().charAt(i + 1) == ' ') {
                    break;
                }
                count++;
            }
            temp2 = o.getAnswer().substring(count + 1).toLowerCase().trim();
        } else {
            temp2 = o.getAnswer().toLowerCase().trim();
        }

        if (answer.toLowerCase().contains(temp2) && question.toLowerCase().contains(temp)) {
            return true;
        }
        return false;
    }

}
