package se.iths.weatherwebservice.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.iths.weatherwebservice.service.WeatherService;

@Controller
public class WeatherController {
    @Autowired
    private WeatherService weatherService;


@GetMapping("/weather")
    public String showTheBestWeather(Model m){
    m.addAttribute("bestweather", weatherService.getBestWeather());
    return "weather";
}


}
