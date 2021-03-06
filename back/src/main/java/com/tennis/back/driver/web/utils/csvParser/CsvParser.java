package com.tennis.back.driver.web.utils.csvParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface CsvParser<T> {

    List<T> parse(InputStream input) throws IOException;
}
