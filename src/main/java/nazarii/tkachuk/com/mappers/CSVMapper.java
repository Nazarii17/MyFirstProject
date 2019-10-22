package nazarii.tkachuk.com.mappers;

import nazarii.tkachuk.com.entities.CSVSerializable;

public interface CSVMapper<T extends CSVSerializable> {

    T mapFromCSV(String s);
}
