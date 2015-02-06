/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.up.edu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author DCS
 */
public class Passers {
    TreeMap passers;
    public Passers(){
        passers = new TreeMap();
    }
    
    public void addIntoPassersList(Applicant a){
        passers.put(a.getName(), a);
    }
    public List getPassers(String campus, String course){
        List l = new ArrayList();
        
        Set set = passers.entrySet();
        Iterator i = set.iterator();
        String tcampus = "";
        String tcourse = "";
        while(i.hasNext()){
            Map.Entry e = (Map.Entry)i.next();
            Applicant a = (Applicant)e.getValue();
            tcampus = a.getCampus();
            tcourse = a.getCourse();
            if(tcampus.equals(campus) && (tcourse.contains(course))){
                l.add(a);
            }
        }
        return l;
    }
    public int getNumOfPassers(){
        return passers.size();
    }
}
