package entities.event;

import java.io.FileReader;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class EventList {

    private List<Event> events;

    public EventList() {
        load();
    }

    private void load() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader("src/json/events.json");
            this.events = gson.fromJson(reader, new TypeToken<List<Event>>() {}.getType());
            reader.close();
        } catch (Exception e) {
            System.out.println("Failed to load event list.");
            e.printStackTrace();
        }
    }

    public List<Event> getEvents() {
        return events;
    }
}
