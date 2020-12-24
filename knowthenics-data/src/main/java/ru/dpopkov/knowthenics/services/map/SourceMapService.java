package ru.dpopkov.knowthenics.services.map;

import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Source;
import ru.dpopkov.knowthenics.services.SourceService;

@Service
public class SourceMapService extends AbstractMapService<Source> implements SourceService {
}
