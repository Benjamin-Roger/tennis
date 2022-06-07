package com.tennis.back.utils.csvParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface CsvParser<T> {

    List<T> parse(InputStream input) throws IOException;
}
