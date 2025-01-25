package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant1;
    private Etudiant etudiant2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialisation des objets Etudiant
        etudiant1 = new Etudiant();
        etudiant1.setIdEtudiant(1L);
        etudiant1.setNomEtudiant("Ali");
        etudiant1.setPrenomEtudiant("Ahmed");
        etudiant1.setCinEtudiant(12345678L);

        etudiant2 = new Etudiant();
        etudiant2.setIdEtudiant(2L);
        etudiant2.setNomEtudiant("Hana");
        etudiant2.setPrenomEtudiant("Ben Ali");
        etudiant2.setCinEtudiant(87654321L);
    }

    @Test
    void testRetrieveAllEtudiants() {
        // Simule la récupération de tous les étudiants
        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(etudiant1, etudiant2));

        // Appel du service
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        // Vérifications
        assertNotNull(etudiants, "La liste des étudiants ne doit pas être nulle");
        assertEquals(2, etudiants.size(), "Le nombre d'étudiants retournés n'est pas correct");
        verify(etudiantRepository, times(1)).findAll(); // Vérifie que findAll a été appelé une fois
    }

    @Test
    void testRetrieveEtudiant() {
        // Simule la récupération d'un étudiant par ID
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant1));

        // Appel du service
        Etudiant foundEtudiant = etudiantService.retrieveEtudiant(1L);

        // Vérifications
        assertNotNull(foundEtudiant, "L'étudiant trouvé ne doit pas être nul");
        assertEquals("Ali", foundEtudiant.getNomEtudiant(), "Le nom de l'étudiant trouvé est incorrect");
        verify(etudiantRepository, times(1)).findById(1L); // Vérifie que findById a été appelé une fois
    }

    @Test
    void testAddEtudiant() {
        // Simule l'ajout d'un étudiant
        when(etudiantRepository.save(etudiant1)).thenReturn(etudiant1);

        // Appel du service
        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant1);

        // Vérifications
        assertNotNull(savedEtudiant, "L'étudiant sauvegardé ne doit pas être nul");
        assertEquals("Ali", savedEtudiant.getNomEtudiant(), "Le nom de l'étudiant sauvé est incorrect");
        verify(etudiantRepository, times(1)).save(etudiant1); // Vérifie que save a été appelé une fois
    }

    @Test
    void testModifyEtudiant() {
        // Simule la modification d'un étudiant
        when(etudiantRepository.save(etudiant1)).thenReturn(etudiant1);

        // Appel du service
        Etudiant updatedEtudiant = etudiantService.modifyEtudiant(etudiant1);

        // Vérifications
        assertNotNull(updatedEtudiant, "L'étudiant mis à jour ne doit pas être nul");
        assertEquals("Ali", updatedEtudiant.getNomEtudiant(), "Le nom de l'étudiant mis à jour est incorrect");
        verify(etudiantRepository, times(1)).save(etudiant1); // Vérifie que save a été appelé une fois
    }

    @Test
    void testRemoveEtudiant() {
        // Simule la suppression d'un étudiant
        doNothing().when(etudiantRepository).deleteById(1L);

        // Appel du service
        etudiantService.removeEtudiant(1L);

        // Vérifications
        verify(etudiantRepository, times(1)).deleteById(1L); // Vérifie que deleteById a été appelé une fois
    }

    @Test
    void testRecupererEtudiantParCin() {
        // Simule la récupération d'un étudiant par CIN
        when(etudiantRepository.findEtudiantByCinEtudiant(12345678L)).thenReturn(etudiant1);

        // Appel du service
        Etudiant etudiant = etudiantService.recupererEtudiantParCin(12345678L);

        // Vérifications
        assertNotNull(etudiant, "L'étudiant trouvé par CIN ne doit pas être nul");
        assertEquals(12345678L, etudiant.getCinEtudiant(), "Le CIN de l'étudiant trouvé est incorrect");
        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(12345678L); // Vérifie que la méthode a été appelée une fois
    }
}

