package ru.dpopkov.knowthenics.services.map;

import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.services.KeyTermService;

@Service
public class KeyTermMapService extends AbstractMapService<KeyTerm> implements KeyTermService {
}
