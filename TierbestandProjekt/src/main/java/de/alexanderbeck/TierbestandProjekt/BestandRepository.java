package de.alexanderbeck.TierbestandProjekt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BestandRepository extends JpaRepository<Bestand, Long>{

	List<Bestand> findByHinweis(String hinweis);
	Bestand findById(long id);
	Bestand findFirstByNutzartcodeOrderByZeitstampDesc(int nutzartcode);
}
