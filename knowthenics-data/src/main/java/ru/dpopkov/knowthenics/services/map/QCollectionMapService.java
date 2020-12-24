package ru.dpopkov.knowthenics.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.QCollection;
import ru.dpopkov.knowthenics.services.QCollectionService;

@Service
@Profile({"default", "map-service"})
public class QCollectionMapService extends AbstractMapService<QCollection> implements QCollectionService {
}
