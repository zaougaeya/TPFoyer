package tn.esprit.tpfoyer;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UniversiteServiceTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    private Universite universite1;
    private Universite universite2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialisation des mocks
        universite1 = new Universite(1L, "Université de Paris", "Paris", null);
        universite2 = new Universite(2L, "Université de Lyon", "Lyon", null);
    }

    @Test
    void testRetrieveAllUniversites() {
        // Arrange
        when(universiteRepository.findAll()).thenReturn(Arrays.asList(universite1, universite2));

        // Act
        List<Universite> universites = universiteService.retrieveAllUniversites();

        // Assert
        assertNotNull(universites);
        assertEquals(2, universites.size());
        verify(universiteRepository, times(1)).findAll();  // Vérification du nombre d'appels
    }

    @Test
    void testRetrieveUniversiteById() {
        // Arrange
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite1));

        // Act
        Universite result = universiteService.retrieveUniversite(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Université de Paris", result.getNomUniversite());
        verify(universiteRepository, times(1)).findById(1L);  // Vérification du nombre d'appels
    }

    @Test
    void testRetrieveUniversiteNotFound() {
        // Arrange
        when(universiteRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> universiteService.retrieveUniversite(1L));
        verify(universiteRepository, times(1)).findById(1L);  // Vérification du nombre d'appels
    }

    @Test
    void testAddUniversite() {
        // Arrange
        when(universiteRepository.save(universite1)).thenReturn(universite1);

        // Act
        Universite result = universiteService.addUniversite(universite1);

        // Assert
        assertNotNull(result);
        assertEquals("Université de Paris", result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite1);  // Vérification du nombre d'appels
    }

    @Test
    void testModifyUniversite() {
        // Arrange
        when(universiteRepository.save(universite1)).thenReturn(universite1);
        when(universiteRepository.existsById(universite1.getIdUniversite())).thenReturn(true);

        // Act
        Universite result = universiteService.modifyUniversite(universite1);

        // Assert
        assertNotNull(result);
        assertEquals("Université de Paris", result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite1);  // Vérification du nombre d'appels
    }

    @Test
    void testModifyUniversiteNotFound() {
        // Arrange
        when(universiteRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> universiteService.modifyUniversite(universite1));
        verify(universiteRepository, times(1)).existsById(1L);  // Vérification du nombre d'appels
    }

    @Test
    void testRemoveUniversite() {
        // Arrange
        when(universiteRepository.existsById(1L)).thenReturn(false);  // Le mock indique que l'ID n'existe pas

        // Act & Assert
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> universiteService.removeUniversite(1L));
        assertEquals("Université not found with id: 1", thrown.getMessage());  // Vérification du message d'exception
        verify(universiteRepository, times(1)).existsById(1L);  // Vérification du nombre d'appels
    }

    @Test
    void testFindUniversiteByAdresse() {
        // Arrange
        when(universiteRepository.findByAdresse("Paris")).thenReturn(Arrays.asList(universite1));

        // Act
        List<Universite> result = universiteService.findUniversiteByAdresse("Paris");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Université de Paris", result.get(0).getNomUniversite());
        verify(universiteRepository, times(1)).findByAdresse("Paris");  // Vérification du nombre d'appels
    }
}
