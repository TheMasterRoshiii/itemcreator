package com.me.master.itemcreator.config;

import com.google.gson.JsonObject;
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
        
        if (json.has("name")) {
            String name = json.get("name").getAsString();
            if (!name.matches("^[a-z0-9_]+$")) {
                errors.add("Item name must be lowercase and contain only letters, numbers, and underscores");
            }
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
        
        if (json.has("type")) {
            String type = json.get("type").getAsString();
            if (type.equals("pickaxe") || type.equals("axe")) {
                if (!json.has("material")) {
                    errors.add("Tool type '" + type + "' requires 'material' field");
                }
                if (!json.has("attackDamage")) {
                    errors.add("Tool type '" + type + "' requires 'attackDamage' field");
                }
                if (!json.has("attackSpeed")) {
                    errors.add("Tool type '" + type + "' requires 'attackSpeed' field");
                }
            }
            
            if (type.equals("armor")) {
                if (!json.has("slot")) {
                    errors.add("Armor requires 'slot' field (HELMET, CHESTPLATE, LEGGINGS, BOOTS)");
                }
            }
        }
        
        return errors;
    }
    
    public static boolean isValid(JsonObject json, Logger logger) {
        List<String> errors = validateItemConfig(json, logger);
        
        if (!errors.isEmpty()) {
            logger.error("Invalid item configuration:");
            errors.forEach(error -> logger.error("  - {}", error));
            return false;
        }
        
        return true;
    }
}
