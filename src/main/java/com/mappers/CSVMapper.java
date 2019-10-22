package com.mappers;

import com.entities.CSVSerializable;

public interface CSVMapper<T extends CSVSerializable> {

    T mapFromCSV(String s);
}
