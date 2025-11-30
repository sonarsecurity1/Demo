package sn.esmt.demo_transactional.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.esmt.demo_transactional.model.Compte;
import sn.esmt.demo_transactional.repository.CompteRepository;
import sn.esmt.demo_transactional.service.CompteService;

@RestController
public class CompteController {

    private final CompteRepository compteRepository;
    private final CompteService compteService;

    @Autowired
    public CompteController(CompteRepository compteRepository, CompteService compteService) {
        this.compteRepository = compteRepository;
        this.compteService = compteService;
    }

    @GetMapping("/")
    public String home(Compte compte, Model model) {
        model.addAttribute("comptes", compteRepository.findAll());
        return "home";
    }

    @PostMapping("/transfert")
    public String transfert(@RequestParam Integer idSource, @RequestParam Integer idDestination, @RequestParam Double montant, Model model) {
        try {
            compteService.transferer(idSource, idDestination, montant);
            model.addAttribute("message", "Transfert effectué avec succès !");
        } catch (RuntimeException e) {
            model.addAttribute("error", "Échec du transfert : " + e.getMessage());
        }
        model.addAttribute("comptes", compteRepository.findAll());
        return "comptes";
    }

    @GetMapping("/comptes")
    public String afficherComptes(Model model) {
        model.addAttribute("comptes", compteRepository.findAll());
        return "comptes";
    }

    @GetMapping("/comptes/nouveau")
    public String afficherFormulaireCreation(Model model) {
        model.addAttribute("compte", new Compte());
        return "form";
    }

    @PostMapping("/comptes")
    public String creerCompte(@ModelAttribute Compte compte) {
        compteRepository.save(compte); // Sauvegarde du nouveau compte
        return "redirect:/comptes"; // Redirection vers la page des comptes
    }
}
