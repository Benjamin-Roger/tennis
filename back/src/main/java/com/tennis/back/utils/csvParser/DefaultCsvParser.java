package com.tennis.back.utils.csvParser;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.MappingStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class DefaultCsvParser<T> implements CsvParser<T> {

    private Class<? extends T> clazz;

    public DefaultCsvParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<T> parse(InputStream input) throws IOException {
        MappingStrategy<T> mappingStrategy = this.getMappingStrategy();

        try (Reader reader = new InputStreamReader(input)) {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(this.clazz)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            return csvToBean.parse();
        }
    }

    protected MappingStrategy<T> getMappingStrategy() {
        MappingStrategy<T> result = new ColumnPositionMappingStrategy<>();
        result.setType(this.clazz);
        return result;
    }

}

