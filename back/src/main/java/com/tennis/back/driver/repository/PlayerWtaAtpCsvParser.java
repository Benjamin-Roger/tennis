package com.tennis.back.driver.repository;

import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import com.tennis.back.utils.csvParser.DefaultCsvParser;

import java.util.HashMap;
import java.util.Map;

public class PlayerWtaAtpCsvParser extends DefaultCsvParser<PlayerWtaAtp> {
    private Class clazz = PlayerWtaAtp.class;

    public PlayerWtaAtpCsvParser() {
        super(PlayerWtaAtp.class);
    }

    protected MappingStrategy<PlayerWtaAtp> getMappingStrategy() {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("name_first", "nameFirst");
        mapping.put("name_last", "nameLast");
        mapping.put("dob", "dob");
        mapping.put("ioc", "ioc");

        HeaderColumnNameTranslateMappingStrategy<PlayerWtaAtp> strategy =
                new HeaderColumnNameTranslateMappingStrategy<>();
        strategy.setColumnMapping(mapping);
        strategy.setType(this.clazz);
        return strategy;
    }
}
