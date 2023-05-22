package se.iths.weatherwebservice.data;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import se.iths.weatherwebservice.business.MyWeather;
import se.iths.weatherwebservice.data.openmeteo.Openmeteo;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OpenMeteoClientRepository {
    WebClient client = WebClient.create("http://localhost:8080");

    public MyWeather getOpenMWeather() {
        Mono<Openmeteo> m = client
                .get()
                .uri("https://api.open-meteo.com/v1/forecast?latitude=59.31&longitude=18.02&hourly=temperature_2m,relativehumidity_2m,windspeed_10m&forecast_days=3&timezone=auto")
                .retrieve()
                .bodyToMono(Openmeteo.class);

        Openmeteo openMWeather = m.block();

        LocalDateTime timePlusOneDay = LocalDateTime.now().plusHours(24);
        String dateTime = timePlusOneDay.toString();
        String timestamp = null;

        List<String> timestamps = openMWeather.getHourly().getTime();
        int index = -1;
        for (int i=0;i<timestamps.size();i++){
            String t = timestamps.get(i);
            if(t.substring(0, 13).equals(dateTime.substring(0, 13))){
                timestamp=t;
                index=i;
                break;
            }
        }

        double temp = openMWeather.getHourly().getTemperature2m().get(index);
        double humid = openMWeather.getHourly().getRelativehumidity2m().get(index);
        double windspeed = openMWeather.getHourly().getWindspeed10m().get(index);

        return new MyWeather(temp, humid, windspeed, "OpenMeteo", timestamp);

    }
}
