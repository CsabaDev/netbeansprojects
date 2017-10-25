/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemodel;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author czine
 */
public class Result {
    String userName;
    int number;
    int time;
    String date;

    public Result(String userName, int number, int time, Date date) {
        this.userName = userName;
        this.number = number;
        this.time = time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.");
        this.date = sdf.format(date);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.");
        this.date = sdf.format(date);
    }
    
    
}
