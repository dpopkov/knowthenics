package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.KeyTerm;
import ru.dpopkov.knowthenics.repositories.KeyTermRepository;
import ru.dpopkov.knowthenics.services.KeyTermService;

@Service
@Profile({"spring-data-jpa", "dev", "prod"})
public class KeyTermSDJpaService extends AbstractSDJpaService<KeyTerm> implements KeyTermService {

    public KeyTermSDJpaService(KeyTermRepository keyTermRepository) {
        super(keyTermRepository);
    }
}
