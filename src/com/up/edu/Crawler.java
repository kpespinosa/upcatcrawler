/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.up.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DCS
 */
public class Crawler {
    Passers p = null;
    public Crawler(){
        p = new Passers();
    }
    
    public static void main(String[] args) {
            Crawler c = new Crawler();
            c.crawlAllPassers();
            List l = c.p.getPassers("CEBU", "BS COMPUTER SCIENCE");
            Iterator i  = l.iterator();
            int ctr = 0;
            while(i.hasNext()){
                ctr++;
                System.out.println(ctr+". "+i.next());
            }
    }
    
    public void crawlAllPassers(){
        String page = "";
        String pre = "http://upcat.up.edu.ph/results/page-";
        String post = ".html";
        String url = "";
        for (int i = 1; i < 10; i++) {
            page = "00"+i;
            url = pre+page+post;
            System.out.print("Crawling page "+page+"...");
            crawlPassersPerPage(url);
            System.out.println("done");
            if(i==9){
                break;
            }
                
        }
        for (int i = 10; i <100; i++) {
            page = "0"+i;
            url = pre+page+post;
            System.out.print("Crawling page "+page+"...");
            crawlPassersPerPage(url);
            System.out.println("done");
            if(i==99)
                break;
        }
        for (int i = 100; i <= 150; i++) {
            url = pre+i+post;
            System.out.print("Crawling page "+i+"...");
            crawlPassersPerPage(url);
            System.out.println("done");
            if(i==150)
                break;
        }
    }
    
    public void crawlPassersPerPage(String url){
        try {
            URL u = new URL(url);
            InputStream is= u.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String s = br.readLine();
            String endS = "Previous";
            int ctr = 0;
            boolean end = false;
            String validInfo = "printable";
            String name="", course="",campus=""; 
            Applicant a = null;
            int ctrPerApplicant=1;
            
            while(s!=null){
                ctr++;
                if(s.contains(endS)){
                    end = true;
                }
                if(ctr>=45 && !end && s.contains(validInfo)){
                    if(ctrPerApplicant==1){
                        a = new Applicant();
                        //System.out.println(s);
                        s = getContentsOnly(s);
                        if(s.contains("see notes")){
                            s = s.substring(0, s.length()-34);
                            //System.out.println(s);
                        }
                        a.setName(s);
                        ctrPerApplicant++;
                    }else if(ctrPerApplicant==2){
                        //System.out.println(s);
                        a.setCampus(getContentsOnly(s));
                        ctrPerApplicant++;
                    }else if(ctrPerApplicant==3){
                        //System.out.println(s);
                        a.setCourse(getContentsOnly(s));
                        p.addIntoPassersList(a);
                        ctrPerApplicant=1;
                    }
                        
                }
                    
                s = br.readLine();
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static String getContentsOnly(String s){
        String pre = "<td class=\"printable\">";
        String post = "</td>";
        String[] s1 = s.split(pre);
        //System.out.println(s1[1]);
        String[] s2 = s1[1].split(post);
        //System.out.println(s2[0]);
        return s2[0];
    }
}
