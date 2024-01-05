package com.example.mydoctorapp.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DateFormatPattern {
    GMT_FORMAT("dd-MM-yyyy hh:mm:ss"),GMT_FORMAT_ONLY_DATE("dd-MM-yyyy");
    private String pattern;
}
