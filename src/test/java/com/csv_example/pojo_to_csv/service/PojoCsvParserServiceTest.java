package com.csv_example.pojo_to_csv.service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.csv_example.pojo_to_csv.models.AddressModel;
import com.csv_example.pojo_to_csv.models.ContactModel;
import com.csv_example.pojo_to_csv.models.CsvPojoModel;
import com.csv_example.pojo_to_csv.models.UserModel;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@SpringBootTest
public class PojoCsvParserServiceTest {

    @Autowired
    PojoCsvParserService service;

    @TempDir
    File tempDir;

    @Test
    void whenParsingCsvPopulateFile_thenSave()
            throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {

        Set<ContactModel> contactsBobAndFather = new HashSet<ContactModel>(
                Arrays.asList(
                        new ContactModel("bob123@hotmail.com", "1234567890", "1234567890"),
                        new ContactModel("bobsdad123@hotmail.com", "1234567890", "1234567890")));

        Set<ContactModel> contactsJohnAndWife = new HashSet<ContactModel>(
                Arrays.asList(
                        new ContactModel("john123@hotmail.com", "0987654321", "0987654321"),
                        new ContactModel("johnswife123@hotmail.com", "0987654321", "0987654321")));

        UserModel userOne = new UserModel("Bob", "bob123",
                contactsBobAndFather,
                new AddressModel("123 ave", "new york", "NV", "12345"));

        UserModel userTwo = new UserModel("John", "john123",
                contactsJohnAndWife,
                new AddressModel("123 ave", "new york", "new york", "54321"));

        List<UserModel> testUsers = new ArrayList<UserModel>() {
            {
                add(userOne);
                add(userTwo);
            }
        };

        File csv = new File(tempDir, "test.csv");
        service.writeCsvFromBean(csv.toPath(), testUsers);

        List<CsvPojoModel> pojos = new ArrayList<CsvPojoModel>();

        CustomCsvHeaders<CsvPojoModel> mappingStrategy = new CustomCsvHeaders<CsvPojoModel>();
        mappingStrategy.setType(CsvPojoModel.class);

        try (Reader reader = Files.newBufferedReader(csv.toPath())) {
            CsvToBean<CsvPojoModel> cb = new CsvToBeanBuilder<CsvPojoModel>(reader)
                    .withType(CsvPojoModel.class)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            pojos = cb.parse();
        }

        Collections.sort(pojos);

        assert (pojos.size() == 4);
        assert (pojos.get(0).getName().equals("John"));
        assert (pojos.get(0).getEmail().equals("john123@hotmail.com"));
        assert (pojos.get(2).getName().equals("Bob"));
        assert (pojos.get(2).getEmail().equals("bob123@hotmail.com"));
        assert (pojos.get(0).getState().equals("new york"));
        assert (pojos.get(2).getState().equals("NV"));
    }

}
