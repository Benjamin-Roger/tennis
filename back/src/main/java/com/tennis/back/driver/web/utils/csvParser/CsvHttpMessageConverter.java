package com.tennis.back.driver.web.utils.csvParser;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class CsvHttpMessageConverter<T> extends AbstractGenericHttpMessageConverter<List<T>> {

    private CsvParser<T> csvParser;

    public CsvHttpMessageConverter(CsvParser<T> csvParser, MediaType mediaType) {
        super(mediaType);
        this.csvParser = csvParser;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return List.class.isAssignableFrom(clazz);
    }

    @Override
    protected void writeInternal(List<T> ts, Type type, HttpOutputMessage outputMessage) throws HttpMessageNotWritableException {

    }

    @Override
    public List<T> read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {

        return this.csvParser.parse(inputMessage.getBody());
    }

    @Override
    protected List<T> readInternal(Class<? extends List<T>> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {

        return this.csvParser.parse(inputMessage.getBody());
    }

    @Override
    public List<MediaType> getSupportedMediaTypes(Class<?> clazz) {
        return super.getSupportedMediaTypes(clazz);
    }

}
