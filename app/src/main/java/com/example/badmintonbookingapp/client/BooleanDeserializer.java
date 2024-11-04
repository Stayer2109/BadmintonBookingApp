package com.example.badmintonbookingapp.client;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class BooleanDeserializer implements JsonDeserializer<Boolean> {
    @Override
    public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.getAsJsonPrimitive().isBoolean()) {
            return json.getAsBoolean();
        } else if (json.getAsJsonPrimitive().isNumber()) {
            return json.getAsInt() == 1; // Interpret non-zero numbers as true
        } else {
            throw new JsonParseException("Expected boolean or number but found " + json);
        }
    }
}