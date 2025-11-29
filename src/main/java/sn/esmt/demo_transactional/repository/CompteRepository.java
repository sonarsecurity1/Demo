package sn.esmt.demo_transactional.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.esmt.demo_transactional.model.Compte;

public interface CompteRepository extends JpaRepository<Compte, Integer> {
}
