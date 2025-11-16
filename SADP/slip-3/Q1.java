import java.util.Observable;
import java.util.Observer;

class WeatherStation extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}

class Display implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherStation) {
            WeatherStation ws = (WeatherStation) o;
            System.out.println("Updated weather data: ");
            System.out.println("Temperature: " + ws.getTemperature());
            System.out.println("Humidity: " + ws.getHumidity());
            System.out.println("Pressure: " + ws.getPressure());
        }
    }
}

public class Q1 {
    public static void main(String[] args) {

        WeatherStation weatherStation = new WeatherStation();
        Display display = new Display();

        weatherStation.addObserver(display);

        weatherStation.setMeasurements(25.5f, 65.0f, 1013.1f);
        weatherStation.setMeasurements(30.0f, 70.0f, 1012.5f);
    }
}
