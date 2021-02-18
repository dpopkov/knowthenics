package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.QCollection;
import ru.dpopkov.knowthenics.repositories.QCollectionRepository;
import ru.dpopkov.knowthenics.services.QCollectionService;

@Service
@Profile({"spring-data-jpa", "dev", "prod"})
public class QCollectionSDJpaService extends AbstractSDJpaService<QCollection> implements QCollectionService {

    public QCollectionSDJpaService(QCollectionRepository qCollectionRepository) {
        super(qCollectionRepository);
    }
}
