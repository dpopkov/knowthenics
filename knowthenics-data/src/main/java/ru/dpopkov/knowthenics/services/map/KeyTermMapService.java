package ru.dpopkov.knowthenics.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.services.KeyTermService;

@Service
@Profile({"default", "map-service"})
public class KeyTermMapService extends AbstractMapService<KeyTerm> implements KeyTermService {
}
