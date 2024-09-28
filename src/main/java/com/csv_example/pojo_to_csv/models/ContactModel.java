package com.csv_example.pojo_to_csv.models;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactModel {
    @CsvBindByName
    private String email;
    @CsvBindByName
    private String phoneNumberOne;
    @CsvBindByName
    private String phoneNumberTwo;
}
