package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.Source;
import ru.dpopkov.knowthenics.repositories.SourceRepository;
import ru.dpopkov.knowthenics.services.SourceService;

@Service
@Profile({"spring-data-jpa", "dev", "prod"})
public class SourceSDJpaService extends AbstractSDJpaService<Source> implements SourceService {

    public SourceSDJpaService(SourceRepository sourceRepository) {
        super(sourceRepository);
    }
}
