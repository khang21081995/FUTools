/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Ultil.ValidationAndNormalizingText;

/**
 *
 * @author khang
 */
public class Name {

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName.isEmpty()) {
            return;
        }
        fullName = ValidationAndNormalizingText.xoaDauCauVN(fullName);
        this.fullName = ValidationAndNormalizingText.normalFormName(fullName);
        spilitName();
    }

    private String surName;
    private String midName;
    private String firstName;
    private String fullName;
    private String midNameChar;

    public Name() {
        surName = "";
        midName = "";
        fullName = "";
        firstName = "";

    }

    public Name(String fullName) {
        setFullName(fullName);
    }

    private void spilitName() {
        String temp[] = fullName.split(" ");
        switch (temp.length) {
            default:
                midName = "";
                midNameChar = "";
                for (int i = 1; i < temp.length - 1; i++) {
                    midName += temp[i] + " ";
                    midNameChar += temp[i].charAt(0) + "";
                }
            case 2:
                surName = temp[0];
            case 1:
                firstName = temp[temp.length - 1];
        }
    }

    @Override
    public String toString() {
        return firstName + " " + surName + " " + midName + "\r\n";
    }

    public String getFUName() {
        String midname = midNameChar == null ? "" : midNameChar;
        String sName = surName == null ? "" : surName.charAt(0) + "";
        return firstName + sName + midname;
    }

}
