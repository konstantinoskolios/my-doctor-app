package com.example.mydoctorapp.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DateFormatPattern {
    GMT_FORMAT("dd-MM-yyyy hh:mm:ss");
    private String pattern;

}
