package se.iths.weatherwebservice.business;


public class MyWeather {
    private double temp;
    private double humidity;

    private double windspeed;

    private String origin;

    private String time;

    public MyWeather(double temp, double humidity, double windspeed, String origin, String time) {
        this.temp = temp;
        this.humidity = humidity;
        this.windspeed = windspeed;
        this.origin = origin;
        this.time = time;
    }

    public MyWeather() {
    }

    @Override
    public String toString() {
        return "Temperatur: " + temp +
                " Relativ fuktighet: " + humidity +
                " Vindhastighet: " + windspeed +
                " Källa: " + origin +
                " Tidpunkt: " + time;
    }


    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
