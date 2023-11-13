package com.peach.sellbuy_ecommerce.util;

import net.ucanaccess.jdbc.UcanaccessDriver;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Data class for managing file paths, database connection, and JSON file reading.
 */
public class Data {
    public static final String ROOT = "/Users/calebsmith/IdeaProjects/SellBuy/";
    public static final String DATA_ROOT = ROOT + "data/";
    public static final String IMAGE = ROOT + "data/images/";
    public static final String DATABASE = UcanaccessDriver.URL_PREFIX + file("data.accdb");

    /**
     * Constructs the full file path from the root directory.
     *
     * @param file The file to append to the root directory.
     * @return The full file path.
     */
    public static String file(String file) {
        return DATA_ROOT + file;
    }

    /**
     * Constructs the full file path for test files.
     *
     * @param file The test file to append to the test directory.
     * @return The full test file path.
     */
    public static String testFile(String file) {
        return file("test/" + file);
    }

    public static String image() {
        return IMAGE;
    }

    /**
     * Checks if a file exists at the specified path.
     *
     * @param filePath The path of the file to check.
     * @return True if the file exists, false otherwise.
     */
    public static boolean doesFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    /**
     * Reads a JSON file and converts it to a map.
     *
     * @param filePath The path of the JSON file to read.
     * @return A map containing the JSON data.
     */
    public static Map<String, Object> readJsonFile(String filePath) {
        Map<String, Object> jsonMap = new HashMap<>();

        try (FileReader fileReader = new FileReader(filePath)) {
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONObject jsonObject = new JSONObject(jsonTokener);

            // Convert the JSON object to a map
            jsonMap = toMap(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonMap;
    }

    /**
     * Converts a JSON object to a map.
     *
     * @param jsonObject The JSON object to convert.
     * @return A map containing the JSON object's data.
     */
    private static Map<String, Object> toMap(JSONObject jsonObject) {
        Map<String, Object> map = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                map.put(key, toMap((JSONObject) value));
            } else if (value instanceof JSONArray) {
                map.put(key, toList((JSONArray) value));
            } else {
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * Converts a JSON array to a list.
     *
     * @param jsonArray The JSON array to convert.
     * @return A list containing the JSON array's elements.
     */
    private static Object toList(JSONArray jsonArray) {
        java.util.List<Object> list = new java.util.ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (value instanceof JSONObject) {
                list.add(toMap((JSONObject) value));
            } else if (value instanceof JSONArray) {
                list.add(toList((JSONArray) value));
            } else {
                list.add(value);
            }
        }
        return list;
    }
}
