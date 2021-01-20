package ru.dpopkov.knowthenics.formatters;

import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.model.Source;
import ru.dpopkov.knowthenics.services.SourceService;

import java.util.Locale;
import java.util.Set;

@Component
public class SourceFormatter implements Formatter<Source> {

    private final SourceService sourceService;

    public SourceFormatter(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @Override
    @NonNull
    public Source parse(@NonNull String text, @SuppressWarnings("NullableProblems") Locale locale) {
        Set<Source> all = sourceService.findAll();
        for (Source source : all) {
            if (source.getShortTitle().equals(text)) {
                return source;
            }
        }
        throw new IllegalArgumentException("Source not found for text " + text);
    }

    @Override
    @NonNull
    public String print(Source object, @SuppressWarnings("NullableProblems") Locale locale) {
        return object.getShortTitle();
    }
}
