package se.iths.weatherwebservice.restui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.weatherwebservice.business.MyWeather;
import se.iths.weatherwebservice.service.WeatherService;

@RestController
public class RestWeatherController {

@Autowired
    WeatherService weatherService;

@GetMapping("rest/weather")
public MyWeather getMyBestWeather(){
    return weatherService.getBestWeather();
}


}
