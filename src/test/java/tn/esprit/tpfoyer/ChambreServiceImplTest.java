package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChambreServiceImplTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    private Chambre chambre1;
    private Chambre chambre2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialisation des objets Chambre
        chambre1 = new Chambre();
        chambre1.setIdChambre(1L);
        chambre1.setNumeroChambre(101L);
        chambre1.setTypeC(TypeChambre.SIMPLE);

        chambre2 = new Chambre();
        chambre2.setIdChambre(2L);
        chambre2.setNumeroChambre(102L);
        chambre2.setTypeC(TypeChambre.DOUBLE);
    }

    @Test
    void testRetrieveAllChambres() {
        // Simule la récupération de toutes les chambres
        when(chambreRepository.findAll()).thenReturn(Arrays.asList(chambre1, chambre2));

        // Appel du service
        List<Chambre> chambres = chambreService.retrieveAllChambres();

        // Vérifications
        assertNotNull(chambres, "La liste des chambres ne doit pas être nulle");
        assertEquals(2, chambres.size(), "Le nombre de chambres retournées n'est pas correct");
        verify(chambreRepository, times(1)).findAll(); // Vérifie que findAll a été appelé une fois
    }

    @Test
    void testRetrieveChambre() {
        // Simule la récupération d'une chambre par ID
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre1));

        // Appel du service
        Chambre foundChambre = chambreService.retrieveChambre(1L);

        // Vérifications
        assertNotNull(foundChambre, "La chambre trouvée ne doit pas être nulle");
        assertEquals(101L, foundChambre.getNumeroChambre(), "Le numéro de chambre trouvé est incorrect");
        verify(chambreRepository, times(1)).findById(1L); // Vérifie que findById a été appelé une fois
    }

    @Test
    void testAddChambre() {
        // Simule l'ajout d'une chambre
        when(chambreRepository.save(chambre1)).thenReturn(chambre1);

        // Appel du service
        Chambre savedChambre = chambreService.addChambre(chambre1);

        // Vérifications
        assertNotNull(savedChambre, "La chambre sauvegardée ne doit pas être nulle");
        assertEquals(101L, savedChambre.getNumeroChambre(), "Le numéro de chambre sauvé est incorrect");
        verify(chambreRepository, times(1)).save(chambre1); // Vérifie que save a été appelé une fois
    }

    @Test
    void testModifyChambre() {
        // Simule la modification d'une chambre
        when(chambreRepository.save(chambre1)).thenReturn(chambre1);

        // Appel du service
        Chambre updatedChambre = chambreService.modifyChambre(chambre1);

        // Vérifications
        assertNotNull(updatedChambre, "La chambre mise à jour ne doit pas être nulle");
        assertEquals(101L, updatedChambre.getNumeroChambre(), "Le numéro de chambre mis à jour est incorrect");
        verify(chambreRepository, times(1)).save(chambre1); // Vérifie que save a été appelé une fois
    }

    @Test
    void testRemoveChambre() {
        // Simule la suppression d'une chambre
        doNothing().when(chambreRepository).deleteById(1L);

        // Appel du service
        chambreService.removeChambre(1L);

        // Vérifications
        verify(chambreRepository, times(1)).deleteById(1L); // Vérifie que deleteById a été appelé une fois
    }

    @Test
    void testRecupererChambresSelonTyp() {
        // Simule la récupération des chambres selon le type
        when(chambreRepository.findAllByTypeC(TypeChambre.SIMPLE)).thenReturn(List.of(chambre1));

        // Appel du service
        List<Chambre> chambres = chambreService.recupererChambresSelonTyp(TypeChambre.SIMPLE);

        // Vérifications
        assertNotNull(chambres, "La liste des chambres selon le type ne doit pas être nulle");
        assertEquals(1, chambres.size(), "Le nombre de chambres selon le type SIMPLE n'est pas correct");
        assertEquals(TypeChambre.SIMPLE, chambres.get(0).getTypeC(), "Le type de chambre retourné n'est pas correct");
        verify(chambreRepository, times(1)).findAllByTypeC(TypeChambre.SIMPLE); // Vérifie que la méthode a été appelée une fois
    }

    @Test
    void testTrouverChambreSelonEtudiant() {
        // Simule la récupération de la chambre selon l'étudiant
        when(chambreRepository.trouverChselonEt(12345678L)).thenReturn(chambre1);

        // Appel du service
        Chambre chambre = chambreService.trouverchambreSelonEtudiant(12345678L);

        // Vérifications
        assertNotNull(chambre, "La chambre trouvée pour l'étudiant ne doit pas être nulle");
        assertEquals(101L, chambre.getNumeroChambre(), "Le numéro de chambre trouvé pour l'étudiant est incorrect");
        verify(chambreRepository, times(1)).trouverChselonEt(12345678L); // Vérifie que la méthode a été appelée une fois
    }
}
