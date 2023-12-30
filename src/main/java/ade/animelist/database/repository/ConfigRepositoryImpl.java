package ade.animelist.database.repository;

import ade.animelist.database.entity.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.*;

import javax.crypto.spec.PSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigRepositoryImpl implements ConfigRepository{
    @Override
    public void writeConfig(User user) {
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("id", user.getId());
        jsonData.put("username", user.getUsername());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter config = new FileWriter("config.json")) {
            gson.toJson(jsonData, config);
            System.out.println("200 OK");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getCurrentUserId() {
        int userId = -1;
        Gson gson = new Gson();
        try (FileReader config = new FileReader("config.json")) {
            JsonElement jsonElement = JsonParser.parseReader(config);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            userId = jsonObject.get("id").getAsInt();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

        return userId;
    }

    @Override
    public String getCurrentUsername() {
        String username = null;
        Gson gson = new Gson();
        try (FileReader config = new FileReader("config.json")) {
            JsonElement jsonElement = JsonParser.parseReader(config);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            username = jsonObject.get("username").getAsString();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

        return username;
    }
}
