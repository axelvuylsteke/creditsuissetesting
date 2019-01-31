package ch.creditsuisse.testing.utils;

import ch.creditsuisse.testing.steps.Validate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import gherkin.deps.com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.currentTimeMillis;
import static org.assertj.core.util.Preconditions.checkArgument;

public class ValidateDataUtils {

    public static String getValidateData(String jsonFile, Map<String, String> fieldValuesOverridesMap, ObjectMapper objectMapper) throws IOException {

        return getValidateDataJson(getValidateDataFile(jsonFile), objectMapper, fieldValuesOverridesMap);
    }

    public static String getValidateBatchData(String jsonFile, ObjectMapper objectMapper) throws IOException {

        return getValidateBatchDataJson(getValidateDataFile(jsonFile), objectMapper);
    }

    private static String getValidatBatcheDataJson(URL jsonUrl, ObjectMapper objectMapper) throws IOException {
        ArrayNode validateDataArrayNode = parseAsJsonArrayNode(jsonUrl, objectMapper);
        return objectMapper.writeValueAsString(validateDataArrayNode);
    }

    private static String getValidateDataJson(URL jsonUrl, ObjectMapper objectMapper, Map<String, String> fieldValuesOverridesMap) throws IOException {
        ObjectNode validateDataObjectNode = parseAsJsonObjectNode(jsonUrl, objectMapper);
        overrideFields(validateDataObjectNode, fieldValuesOverridesMap, objectMapper);
        return objectMapper.writeValueAsString(validateDataObjectNode);
    }

    private static String getValidateBatchDataJson(URL jsonUrl, ObjectMapper objectMapper) throws IOException {
        ArrayNode validateDataArrayNode = parseAsJsonArrayNode(jsonUrl, objectMapper);
        return objectMapper.writeValueAsString(validateDataArrayNode);
    }

    private static ObjectNode overrideFields(ObjectNode objectNode, Map<String, String> fieldValuesOverridesMap, ObjectMapper objectMapper) {
        for (Map.Entry<String, String> fieldValueMapEntry : fieldValuesOverridesMap.entrySet()) {
            String fieldName = fieldValueMapEntry.getKey();
            String valueString = fieldValueMapEntry.getValue();
            objectNode.put(fieldName, valueString);

        }
        return objectNode;
    }

    private static URL getValidateDataFile(String jsonFile) {
        String fileName = removeBrackets(jsonFile);
        String relativePath = String.format("jsondata/%s.json", fileName);
        return Validate.class.getClassLoader().getResource(relativePath);
    }

    private static String removeBrackets(String fileNameFromFeature) {
        Pattern testCaseNamePattern = Pattern.compile("^<(?<fileName>[^>]+)>$");
        Matcher matcher = testCaseNamePattern.matcher(fileNameFromFeature);
        checkArgument(matcher.matches(), "Test case name must match pattern '<fileName>'");
        return matcher.group("fileName");
    }

    static ObjectNode parseAsJsonObjectNode(URL jsonUrl, ObjectMapper objectMapper) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(jsonUrl);
        checkArgument(jsonNode instanceof ObjectNode, "Json data doesn't contain an object");
        return (ObjectNode) jsonNode;
    }

    static ArrayNode parseAsJsonArrayNode(URL jsonUrl, ObjectMapper objectMapper) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(jsonUrl);
        checkArgument(jsonNode instanceof ArrayNode, "Json data doesn't contain an object");
        return (ArrayNode) jsonNode;
    }

    public static HttpResponse executeRequest(String basePath, String jsonBody) throws IOException, UnsupportedEncodingException {
        HttpResponse httpResponse = null;
        HttpPost httpPost = null;
        HttpClient httpClient = HttpClientBuilder.create().build();
        httpPost = new HttpPost(basePath);
        StringEntity params = new StringEntity(jsonBody);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(params);
        return httpClient.execute(httpPost);
    }

    public static String checkResponse(HttpResponse response) throws IOException {
        String responseBody = EntityUtils.toString(response.getEntity());

        Gson gson = new Gson();
        return gson.fromJson(responseBody, Response.class).status;

    }

    private static Response[] checkMultipleResponses(HttpResponse response) throws IOException {
        String resp = EntityUtils.toString(response.getEntity());
        Gson gson = new Gson();
        return gson.fromJson(resp, Response[].class);
    }

    public static int checkNumberOfResponses(HttpResponse response) throws IOException {
        int i = 0;
        for (Response resp : checkMultipleResponses(response)) {
            if (resp.getStatus().equals("SUCCESS")) {
                i++;
            }
        }
        return i;
    }

    class Response {
        private String status;
        private List<String> messages;

        public String getStatus() {
            return status;
        }

        public List<String> getMessages() {
            return messages;
        }
    }
}
