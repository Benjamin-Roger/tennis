package com.tennis.back.driver.repository;

import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import com.tennis.back.utils.csvParser.DefaultCsvParser;

import java.util.HashMap;
import java.util.Map;

public class WikiPlayerCsvParser extends DefaultCsvParser<WikiPlayer> {
    private Class clazz = WikiPlayer.class;

    public WikiPlayerCsvParser() {
        super(WikiPlayer.class);
    }

    protected MappingStrategy<WikiPlayer> getMappingStrategy() {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("name_first", "nameFirst");
        mapping.put("name_last", "nameLast");
        mapping.put("dob", "dob");
        mapping.put("ioc", "ioc");

        HeaderColumnNameTranslateMappingStrategy<WikiPlayer> strategy =
                new HeaderColumnNameTranslateMappingStrategy<>();
        strategy.setColumnMapping(mapping);
        strategy.setType(this.clazz);
        return strategy;
    }
}
