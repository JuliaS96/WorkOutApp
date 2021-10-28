package persistence;

// code used from JsonSerializationDemo
// Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
import org.json.JSONObject;

public interface Writeable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
