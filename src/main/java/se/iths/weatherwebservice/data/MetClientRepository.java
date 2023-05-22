package se.iths.weatherwebservice.data;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import se.iths.weatherwebservice.business.MyWeather;
import se.iths.weatherwebservice.data.met.MetWeather;
import se.iths.weatherwebservice.data.met.Timeseries;


@Repository
public class MetClientRepository {

    WebClient client = WebClient.create("http://localhost:8080");

    public MyWeather getMetWeather() {
        Mono<MetWeather> m = client
                .get()
                .uri("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=59.3110&lon=18.0300")
                .retrieve()
                .bodyToMono(MetWeather.class);

        MetWeather metWeather = m.block();

        Timeseries weatherParams = metWeather.getProperties().getTimeseries().get(27);
        double temp = weatherParams.getData().getInstant().getDetails().getAirTemperature();
        String timestamp = weatherParams.getTime();
        double humid=weatherParams.getData().getInstant().getDetails().getRelativeHumidity();
        double windspeed= weatherParams.getData().getInstant().getDetails().getWindSpeed();



        return new MyWeather(temp, humid,windspeed, "Met", timestamp);

    }
}
