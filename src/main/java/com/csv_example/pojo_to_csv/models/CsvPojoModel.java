package com.csv_example.pojo_to_csv.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CsvPojoModel implements Comparable<CsvPojoModel> {
    @CsvBindByPosition(position = 0)
    @CsvBindByName
    private String name;
    @CsvBindByPosition(position = 1)
    @CsvBindByName
    private String username;
    @CsvBindByPosition(position = 2)
    @CsvBindByName
    private String email;
    @CsvBindByPosition(position = 3)
    @CsvBindByName
    private String phone;
    @CsvBindByPosition(position = 4)
    @CsvBindByName
    private String phoneTwo;
    @CsvBindByPosition(position = 5)
    @CsvBindByName
    private String street;
    @CsvBindByPosition(position = 6)
    @CsvBindByName
    private String city;
    @CsvBindByPosition(position = 7)
    @CsvBindByName
    private String state;
    @CsvBindByPosition(position = 8)
    @CsvBindByName
    private String zip;

    @Override
    public int compareTo(CsvPojoModel csvPojoModel) {
        if (name == csvPojoModel.name && username == csvPojoModel.username && email == csvPojoModel.email
                && phone == csvPojoModel.phone && phoneTwo == csvPojoModel.phoneTwo && street == csvPojoModel.street
                && city == csvPojoModel.city && state == csvPojoModel.state && zip == csvPojoModel.zip)
            return 0;
        else
            return -1;
    }
}
