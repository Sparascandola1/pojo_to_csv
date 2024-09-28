package com.csv_example.pojo_to_csv.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel {
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String username;
    @CsvRecurse
    private ContactModel contactInformation;
    @CsvRecurse
    private AddressModel address; 
}
