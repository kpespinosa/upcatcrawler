/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.up.edu;

/**
 *
 * @author DCS
 */
public class Applicant {
    String name;
    String course;
    String campus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }
    public String toString(){
        return name;
    }
    public static void main(String[] args) {
        String s = "UY, ARLAN VINCENT DIEGO <a href=\"#notes\">(*see notes)</a>";
        if(s.contains("see notes")){
            s = s.substring(0, s.length()-34);
            System.out.println(s);
        }
    }
}
