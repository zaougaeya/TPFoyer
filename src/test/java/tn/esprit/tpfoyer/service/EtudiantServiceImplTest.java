package tn.esprit.tpfoyer.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EtudiantServiceImplTest {
    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Test
    void testRetrieveAllEtudiants() {
        List<Etudiant> mockEtudiants = List.of(
                Etudiant.builder().idEtudiant(1L).nomEtudiant("John").prenomEtudiant("Doe").cinEtudiant(12345678).build(),
                Etudiant.builder().idEtudiant(2L).nomEtudiant("Jane").prenomEtudiant("Smith").cinEtudiant(87654321).build()
        );

        // Mock the repository behavior
        when(etudiantRepository.findAll()).thenReturn(mockEtudiants);

        // Act: Call the service method
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Assert: Verify the results
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("John", result.get(0).getNomEtudiant());
        Assertions.assertEquals("Doe", result.get(0).getPrenomEtudiant());
        Assertions.assertEquals("Jane", result.get(1).getNomEtudiant());
        Assertions.assertEquals("Smith", result.get(1).getPrenomEtudiant());

        // Verify interactions with the repository
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveEtudiant() {
        Etudiant mockEtudiant = Etudiant.builder()
                .idEtudiant(1L)
                .nomEtudiant("John")
                .prenomEtudiant("Doe")
                .cinEtudiant(12345678)
                .build();

        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(mockEtudiant));

        Etudiant result = etudiantService.retrieveEtudiant(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("John", result.getNomEtudiant());
        Assertions.assertEquals("Doe", result.getPrenomEtudiant());
        Assertions.assertEquals(12345678, result.getCinEtudiant());

        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    void testAddEtudiant() {
        Etudiant mockEtudiant = Etudiant.builder()
                .idEtudiant(1L)
                .nomEtudiant("John")
                .prenomEtudiant("Doe")
                .cinEtudiant(12345678)
                .build();

        when(etudiantRepository.save(mockEtudiant)).thenReturn(mockEtudiant);

        Etudiant result = etudiantService.addEtudiant(mockEtudiant);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("John", result.getNomEtudiant());
        Assertions.assertEquals("Doe", result.getPrenomEtudiant());
    }

    @Test
    void testModifyEtudiant() {
        Etudiant mockEtudiant = Etudiant.builder()
                .idEtudiant(1L)
                .nomEtudiant("John")
                .prenomEtudiant("Doe")
                .cinEtudiant(12345678)
                .build();

        when(etudiantRepository.save(mockEtudiant)).thenReturn(mockEtudiant);

        Etudiant result = etudiantService.modifyEtudiant(mockEtudiant);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("John", result.getNomEtudiant());
        Assertions.assertEquals("Doe", result.getPrenomEtudiant());
    }

    @Test
    void testRecupererEtudiantParCin() {
        Etudiant mockEtudiant = Etudiant.builder()
                .idEtudiant(1L)
                .nomEtudiant("John")
                .prenomEtudiant("Doe")
                .cinEtudiant(12345678)
                .build();

        when(etudiantRepository.findEtudiantByCinEtudiant(12345678)).thenReturn(mockEtudiant);

        Etudiant result = etudiantService.recupererEtudiantParCin(12345678);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("John", result.getNomEtudiant());
        Assertions.assertEquals("Doe", result.getPrenomEtudiant());
        Assertions.assertEquals(12345678, result.getCinEtudiant());

        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(12345678);
    }
}