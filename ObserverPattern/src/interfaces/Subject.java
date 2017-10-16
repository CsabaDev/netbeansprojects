/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author oktato2
 */
public interface Subject {
    
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(WeatherChange wc);
    
}
