package challenge;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Integer> {
    public long count();
    public long countByActor(String actor);
    public Page<Quote> findAll(Pageable pageable);
    public Page<Quote> findByActor(String actor, Pageable pageable);
}