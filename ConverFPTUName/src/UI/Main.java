/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BO.App;
import Entity.Name;
import Ultil.ValidationAndNormalizingText;

/**
 *
 * @author khang
 */
public class Main {

    public static void main(String[] args) {
//        String inp = "Quang Khang";
//        String[] temString = inp.split(" ");
//        System.out.println(temString.length);
        try {
            App ap = new App("input.txt", "output.txt");
            ap.runApp();
        } catch (Error e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
