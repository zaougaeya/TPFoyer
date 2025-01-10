package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UniversiteServiceTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    private Universite universite1;
    private Universite universite2;

    @BeforeEach
    void setUp() {
        // Initialisation des objets Université pour les tests
        universite1 = new Universite(1L, "Université de Paris", "Paris", null);
        universite2 = new Universite(2L, "Université de Lyon", "Lyon", null);
    }

    @Test
    void testRetrieveAllUniversites() {
        // Simulation du comportement du repository
        when(universiteRepository.findAll()).thenReturn(List.of(universite1, universite2));

        // Appel du service
        var universites = universiteService.retrieveAllUniversites();

        // Vérification du résultat
        assertNotNull(universites);
        assertEquals(2, universites.size());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveUniversiteById() {
        // Simulation du comportement du repository
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite1));

        // Appel du service
        var universite = universiteService.retrieveUniversite(1L);

        // Vérification du résultat
        assertNotNull(universite);
        assertEquals("Université de Paris", universite.getNomUniversite());  // Utilisation de getNomUniversite()
        verify(universiteRepository, times(1)).findById(1L);
    }

    @Test
    void testAddUniversite() {
        // Simulation du comportement du repository
        when(universiteRepository.save(universite1)).thenReturn(universite1);

        // Appel du service
        var savedUniversite = universiteService.addUniversite(universite1);

        // Vérification du résultat
        assertNotNull(savedUniversite);
        assertEquals("Université de Paris", savedUniversite.getNomUniversite());  // Utilisation de getNomUniversite()
        verify(universiteRepository, times(1)).save(universite1);
    }

    @Test
    void testModifyUniversite() {
        // Simulation de la modification via le repository
        when(universiteRepository.save(universite1)).thenReturn(universite1);

        // Appel du service pour modifier l'université
        var updatedUniversite = universiteService.modifyUniversite(universite1);

        // Vérification du résultat
        assertNotNull(updatedUniversite);
        assertEquals("Université de Paris", updatedUniversite.getNomUniversite());  // Utilisation de getNomUniversite()
        verify(universiteRepository, times(1)).save(universite1);
    }

    @Test
    void testRemoveUniversite() {
        // Simulation de la suppression via le repository
        doNothing().when(universiteRepository).deleteById(1L);

        // Appel du service pour supprimer l'université
        universiteService.removeUniversite(1L);

        // Vérification que la suppression a bien été effectuée
        verify(universiteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindUniversiteByAdresse() {
        // Simulation du comportement du repository
        when(universiteRepository.findByAdresse("Paris")).thenReturn(List.of(universite1));

        // Appel du service
        var universites = universiteService.findUniversiteByAdresse("Paris");

        // Vérification du résultat
        assertNotNull(universites);
        assertEquals(1, universites.size());
        assertEquals("Université de Paris", universites.get(0).getNomUniversite());  // Utilisation de getNomUniversite()
        verify(universiteRepository, times(1)).findByAdresse("Paris");
    }
}
