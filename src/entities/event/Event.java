package entities.event;

import java.util.Map;

public class Event {
    private String name;
    private String description;
    private Map<String, EventChoice> choices;

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Map<String, EventChoice> getChoices() { return choices; }
}
