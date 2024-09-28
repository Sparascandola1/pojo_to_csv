package com.csv_example.pojo_to_csv.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.csv_example.pojo_to_csv.models.ContactModel;
import com.csv_example.pojo_to_csv.models.CsvPojoModel;
import com.csv_example.pojo_to_csv.models.UserModel;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public class PojoCsvParserService {

    public String writeCsvFromBean(Path path, List<UserModel> users)
            throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        List<CsvPojoModel> pojoModels = new ArrayList<CsvPojoModel>();

        for (UserModel user : users) {
            for (ContactModel contact : user.getContactInformation()) {
                pojoModels.add(new CsvPojoModel(
                        user.getName(),
                        user.getUsername(),
                        contact.getEmail(),
                        contact.getPhoneNumberOne(),
                        contact.getPhoneNumberTwo(),
                        user.getAddress().getStreet(),
                        user.getAddress().getCity(),
                        user.getAddress().getState(),
                        user.getAddress().getZip()));
            }
        }

        CustomCsvHeaders<CsvPojoModel> mappingStrategy = new CustomCsvHeaders<CsvPojoModel>();
        mappingStrategy.setType(CsvPojoModel.class);

        try (Writer writer = new FileWriter(path.toString())) {

            StatefulBeanToCsv<CsvPojoModel> sbc = new StatefulBeanToCsvBuilder<CsvPojoModel>(writer)
                    .withQuotechar('\"')
                    .withMappingStrategy(mappingStrategy)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            sbc.write(pojoModels);
            return "CSV writer succeeded";
        } catch (Exception e) {
            throw new IOException(e.getMessage() + "CSV writer failed");
        }
    }
}
