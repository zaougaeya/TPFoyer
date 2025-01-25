package tn.esprit.tpfoyer.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements IUniversiteService {

    private final UniversiteRepository universiteRepository;

    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite retrieveUniversite(Long universiteId) {
        Optional<Universite> universite = universiteRepository.findById(universiteId);
        return universite.orElseThrow(() -> new EntityNotFoundException("Université not found with id: " + universiteId));
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite modifyUniversite(Universite universite) {
        if (universiteRepository.existsById(universite.getIdUniversite())) {
            return universiteRepository.save(universite);
        } else {
            throw new EntityNotFoundException("Université not found with id: " + universite.getIdUniversite());
        }
    }

    @Override
    public void removeUniversite(Long universiteId) {
        if (universiteRepository.existsById(universiteId)) {
            universiteRepository.deleteById(universiteId);
        } else {
            throw new EntityNotFoundException("Université not found with id: " + universiteId);
        }
    }

    @Override
    public List<Universite> findUniversiteByAdresse(String adresse) {
        return universiteRepository.findByAdresse(adresse);
    }
}
