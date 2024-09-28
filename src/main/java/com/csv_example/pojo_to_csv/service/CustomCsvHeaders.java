package com.csv_example.pojo_to_csv.service;

import java.util.Arrays;

import com.csv_example.pojo_to_csv.models.CsvPojoModel;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class CustomCsvHeaders<T> extends HeaderColumnNameMappingStrategy<CsvPojoModel> {

    @Override
    public String[] generateHeader(CsvPojoModel csvPojoModel) throws CsvRequiredFieldEmptyException {
        String[] header = super.generateHeader(csvPojoModel);
        return Arrays.stream(header)
                .map(String::toUpperCase)
                .toArray(String[]::new);
    }

}
