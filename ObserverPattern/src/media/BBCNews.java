/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media;

import interfaces.Observer;
import interfaces.WeatherChange;

/**
 *
 * @author oktato2
 */
public class BBCNews implements Observer {
//ezt a metodust hivja meg a subject (Observatory)
    @Override
    public void update(WeatherChange wc) {
           System.out.println("Wind: "+wc.getWind()+" Humidity: "
           +wc.getHumidity()+" Temp: "+wc.getTemp());
    }
    
}
