package com.csv_example.pojo_to_csv.service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.csv_example.pojo_to_csv.models.AddressModel;
import com.csv_example.pojo_to_csv.models.ContactModel;
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
        
        List<UserModel> testUsers = new ArrayList<UserModel>() {
            {
                add(new UserModel("Bob", "bob123",
                        new ContactModel("bob123@hotmail.com", "1234567890", "1234567890"),
                        new AddressModel("123 ave", "new york", "new york", "12345")));
                add(new UserModel("John", "john123",
                        new ContactModel("john123@hotmail.com", "0987654321", "0987654321"),
                        new AddressModel("123 ave", "new york", "new york", "54321")));
            }
        };

        File csv = new File(tempDir, "test.csv");
        service.writeCsvFromBean(csv.toPath(), testUsers);

        List<UserModel> users = new ArrayList<UserModel>();

        try (Reader reader = Files.newBufferedReader(csv.toPath())) {
            CsvToBean<UserModel> cb = new CsvToBeanBuilder<UserModel>(reader)
                    .withType(UserModel.class)
                    .build();
            users = cb.parse();
        }

        assert (users.size() == 2);
        assert (users.get(0).getName().equals("Bob"));
        assert (users.get(1).getName().equals("John"));
        assert (users.get(1).getContactInformation().getEmail().equals("john123@hotmail.com"));
        assert (users.get(0).getAddress().getZip().equals("12345"));
    }

}
