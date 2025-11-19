package com.me.master.itemcreator.config;

import com.google.gson.*;
import com.me.master.itemcreator.data.ItemDefinition;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ConfigValidator {
    
    public static List<String> validateItemConfig(JsonObject json, Logger logger) {
        List<String> errors = new ArrayList<>();
        
        if (!json.has("name") || json.get("name").getAsString().isEmpty()) {
            errors.add("Missing or empty 'name' field");
        }
        
        if (!json.has("type")) {
            errors.add("Missing 'type' field");
        }
        
        if (json.has("maxStackSize")) {
            int stackSize = json.get("maxStackSize").getAsInt();
            if (stackSize < 1 || stackSize > 64) {
                errors.add("maxStackSize must be between 1 and 64");
            }
        }
        
        if (json.has("maxDamage")) {
            int maxDamage = json.get("maxDamage").getAsInt();
            if (maxDamage < 0) {
                errors.add("maxDamage cannot be negative");
            }
        }
        
        return errors;
    }
}
