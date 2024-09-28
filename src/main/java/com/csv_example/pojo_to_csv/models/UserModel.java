package com.csv_example.pojo_to_csv.models;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel {
    private String name;
    private String username;
    private Set<ContactModel> contactInformation;
    private AddressModel address; 
}
