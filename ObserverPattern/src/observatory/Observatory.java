/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observatory;

import interfaces.Observer;
import interfaces.Subject;
import interfaces.WeatherChange;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oktato2
 */
public class Observatory implements Subject {

    private final List<Observer> observers;

    public Observatory() {
        observers=new ArrayList<>();
    }
    
    
    @Override
    public void addObserver(Observer o) {
          observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(WeatherChange c ) {
        for (Observer observer : observers) {
            //ez a callback
            observer.update(c);
        }
    }
    
    //teszt metodus
    public void change(int temp, int humidity, int wind){
        WeatherChange wc=new WeatherChange();
        wc.setHumidity(humidity);
        wc.setTemp(temp);
        wc.setWind(wind);
        //most ertesitem a megfigyeloket
        notifyObservers(wc);
    }
    
}
