package com.liverpool.automation.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;

/**
 * Utilidades para leer archivos JSON de datos de prueba
 */
public class DataUtils {
    private static final String TEST_DATA_PATH = "src/test/resources/testdata/products.json";

    /**
     * Lee el archivo JSON completo
     */
    public static JsonObject readTestData() throws IOException {
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(TEST_DATA_PATH);
        JsonObject jsonObject = gson.fromJson(fileReader, JsonObject.class);
        fileReader.close();
        return jsonObject;
    }

    /**
     * Obtiene las credenciales del JSON
     */
    public static String getEmail() throws IOException {
        JsonObject data = readTestData();
        return data.getAsJsonObject("testData")
                .getAsJsonObject("credentials")
                .get("email")
                .getAsString();
    }

    public static String getPassword() throws IOException {
        JsonObject data = readTestData();
        return data.getAsJsonObject("testData")
                .getAsJsonObject("credentials")
                .get("password")
                .getAsString();
    }

    /**
     * Obtiene los datos de un test case específico por ID
     */
    public static JsonObject getTestCaseById(String testId) throws IOException {
        JsonObject data = readTestData();
        var testCases = data.getAsJsonObject("testData").getAsJsonArray("testCases");
        
        for (var testCase : testCases) {
            if (testCase.getAsJsonObject().get("id").getAsString().equals(testId)) {
                return testCase.getAsJsonObject();
            }
        }
        throw new RuntimeException("Test case no encontrado: " + testId);
    }
}