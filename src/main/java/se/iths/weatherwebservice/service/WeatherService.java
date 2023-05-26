package se.iths.weatherwebservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.weatherwebservice.business.MyWeather;
import se.iths.weatherwebservice.data.MetClientRepository;
import se.iths.weatherwebservice.data.OpenMeteoClientRepository;
import se.iths.weatherwebservice.data.SmhiClientRepository;

@Service
public class WeatherService {

    @Autowired
    SmhiClientRepository smhiClientRepository;

    @Autowired
    MetClientRepository metClientRepository;
    @Autowired
    OpenMeteoClientRepository openMeteoClientRepository;

    public MyWeather getBestWeather() {

        MyWeather smhi = smhiClientRepository.getSmhiWeather();
        MyWeather met = metClientRepository.getMetWeather();
        MyWeather openM = openMeteoClientRepository.getOpenMWeather();

        if (smhi.getTemp() >= met.getTemp() && smhi.getTemp() >= openM.getTemp()){
            return smhi;
        }
        else if (met.getTemp()>= smhi.getTemp()&&met.getTemp()>= openM.getTemp()) {
            return met;
        }
        else {
            return openM;
        }

    }


}
