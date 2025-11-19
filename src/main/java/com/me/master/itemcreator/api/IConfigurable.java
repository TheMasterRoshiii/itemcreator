package com.me.master.itemcreator.api;

import com.google.gson.JsonObject;

public interface IConfigurable {
    void loadFromJson(JsonObject json);
    JsonObject saveToJson();
    void validate() throws IllegalArgumentException;
}
