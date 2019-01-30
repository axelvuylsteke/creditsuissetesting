package ch.creditsuisse.testing;

import ch.creditsuisse.testing.utils.GenerateValidateDataDTO;
import ch.creditsuisse.testing.utils.GenerateValidateDataDTODataTableConvertor;
import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;

import java.util.Locale;

import static java.util.Locale.ENGLISH;

public class TypeRegistryConfiguration implements TypeRegistryConfigurer {
    @Override
    public Locale locale() {
        return ENGLISH;
    }

    public void configureTypeRegistry(TypeRegistry typeRegistry) {

        typeRegistry.defineDataTableType(new DataTableType(GenerateValidateDataDTO.class, new GenerateValidateDataDTODataTableConvertor()));
    }

}
