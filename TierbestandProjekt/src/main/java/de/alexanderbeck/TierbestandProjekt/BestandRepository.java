package de.alexanderbeck.TierbestandProjekt;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BestandRepository extends CrudRepository<Bestand, Long>{

	List<Bestand> findByHinweis(String hinweis);
	Bestand findById(long id);
}
