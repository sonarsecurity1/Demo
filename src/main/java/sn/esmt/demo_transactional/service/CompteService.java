package sn.esmt.demo_transactional.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.esmt.demo_transactional.model.Compte;
import sn.esmt.demo_transactional.repository.CompteRepository;

import java.util.Optional;

@Service
public class CompteService {

    private final CompteRepository compteRepository;

    @Autowired
    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Transactional
    public void transferer(Integer idSource, Integer idDestination, double montant) {
        Optional<Compte> sourceCompteOpt = compteRepository.findById(idSource);
        Optional<Compte> destinationCompteOpt = compteRepository.findById(idDestination);

        if (!sourceCompteOpt.isPresent() || !destinationCompteOpt.isPresent()) {
            throw new IllegalArgumentException("L'un des comptes spécifiés est introuvable.");
        }

        Compte sourceCompte = sourceCompteOpt.get();
        Compte destinationCompte = destinationCompteOpt.get();

        if (sourceCompte.getSolde() < montant) {
            throw new IllegalArgumentException("Le montant à transférer est supérieur au solde du compte source.");
        }

        sourceCompte.setSolde(sourceCompte.getSolde() - montant);
        destinationCompte.setSolde(destinationCompte.getSolde() + montant);

        compteRepository.save(sourceCompte);
        compteRepository.save(destinationCompte);
    }
}
