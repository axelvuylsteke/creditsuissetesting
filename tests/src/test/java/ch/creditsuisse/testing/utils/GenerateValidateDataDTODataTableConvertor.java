package ch.creditsuisse.testing.utils;

import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.TableTransformer;

import java.util.List;
import java.util.ListIterator;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class GenerateValidateDataDTODataTableConvertor implements TableTransformer<GenerateValidateDataDTO> {
    @Override
    public GenerateValidateDataDTO transform(DataTable dataTable) {
        List<List<String>> dataTableAsLists = dataTable.asLists();
        List<String> fields = dataTableAsLists.get(0);
        List<String> values = dataTableAsLists.get(1);
        GenerateValidateDataDTO generateValidateDataDTO = new GenerateValidateDataDTO();
        ListIterator<String> fieldsIterator = fields.listIterator();
        while (fieldsIterator.hasNext()) {
            int index = fieldsIterator.nextIndex();
            String fieldName = fieldsIterator.next();
            if (isNotBlank(fieldName)) {
                String valueString = values.get(index);
                generateValidateDataDTO.fieldValueOverride(fieldName, valueString);
            }
        }
        return generateValidateDataDTO;
    }
}
