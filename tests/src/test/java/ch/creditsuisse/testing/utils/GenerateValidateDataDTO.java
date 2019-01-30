package ch.creditsuisse.testing.utils;

import java.util.HashMap;
import java.util.Map;

public class GenerateValidateDataDTO {
    private final Map<String, String> fieldValueOverridesMap = new HashMap<>();

    public GenerateValidateDataDTO fieldValueOverride(String fieldName, String valueString) {
        fieldValueOverridesMap.put(fieldName, valueString);
        return this;
    }

    public Map<String, String> getFieldValueOverridesMap() {
        return fieldValueOverridesMap;
    }
}
