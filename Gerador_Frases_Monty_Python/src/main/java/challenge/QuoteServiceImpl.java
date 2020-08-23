package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	private QuoteRepository repository;

	@Override
	public Quote getQuote() {
		Long numberOfQuotes = repository.count();
		int randomIndex = (int) (Math.random() * numberOfQuotes);
		Page<Quote> quotePage = repository.findAll(new PageRequest(randomIndex, 1));
		return  quotePage.getContent().get(0);
	}

	@Override
	public Quote getQuoteByActor(String actor) {
		Long numberOfQuotes = repository.countByActor(actor);
		int randomIndex = (int) (Math.random() * numberOfQuotes);
		Page<Quote> quotePage = repository.findByActor(actor, new PageRequest(randomIndex, 1));
		return  quotePage.getContent().get(0);
	}

}