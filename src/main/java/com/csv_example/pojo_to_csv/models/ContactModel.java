package com.csv_example.pojo_to_csv.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactModel {
    private String email;
    private String phoneNumberOne;
    private String phoneNumberTwo;
}
