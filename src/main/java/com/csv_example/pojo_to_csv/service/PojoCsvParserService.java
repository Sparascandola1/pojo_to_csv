package com.csv_example.pojo_to_csv.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;

import com.csv_example.pojo_to_csv.models.UserModel;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public class PojoCsvParserService {

    public String writeCsvFromBean(Path path, List<UserModel> users) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        try (Writer writer = new FileWriter(path.toString())) {

            StatefulBeanToCsv<UserModel> sbc = new StatefulBeanToCsvBuilder<UserModel>(writer)
                    .withQuotechar('\"')
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            sbc.write(users);
            return "CSV writer succeeded";
        } catch (Exception e) {
            throw new IOException(e.getMessage() + "CSV writer failed");
        }
    }
}
