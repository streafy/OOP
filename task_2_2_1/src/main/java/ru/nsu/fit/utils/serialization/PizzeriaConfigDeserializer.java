package ru.nsu.fit.utils.serialization;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;

public class PizzeriaConfigDeserializer {

    private final Gson gson;

    public PizzeriaConfigDeserializer() {
        this.gson = new Gson();
    }

    public PizzeriaConfig deserialize(String filename) {
        InputStream configInputStream = this.getClass()
                .getResourceAsStream(filename);
        if (configInputStream == null) {
            throw new RuntimeException("Config file not found");
        }
        JsonReader reader = new JsonReader(new InputStreamReader(configInputStream));
        return gson.fromJson(reader,
                PizzeriaConfig.class);
    }
}
