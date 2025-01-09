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
        when(chambreRepository.findAll()).thenReturn(Arrays.asList(chambre1, chambre2));

        List<Chambre> chambres = chambreService.retrieveAllChambres();

        assertNotNull(chambres);
        assertEquals(2, chambres.size());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveChambre() {
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre1));

        Chambre foundChambre = chambreService.retrieveChambre(1L);

        assertNotNull(foundChambre);
        assertEquals(101L, foundChambre.getNumeroChambre());
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    void testAddChambre() {
        when(chambreRepository.save(chambre1)).thenReturn(chambre1);

        Chambre savedChambre = chambreService.addChambre(chambre1);

        assertNotNull(savedChambre);
        assertEquals(101L, savedChambre.getNumeroChambre());
        verify(chambreRepository, times(1)).save(chambre1);
    }

    @Test
    void testModifyChambre() {
        when(chambreRepository.save(chambre1)).thenReturn(chambre1);

        Chambre updatedChambre = chambreService.modifyChambre(chambre1);

        assertNotNull(updatedChambre);
        assertEquals(101L, updatedChambre.getNumeroChambre());
        verify(chambreRepository, times(1)).save(chambre1);
    }

    @Test
    void testRemoveChambre() {
        doNothing().when(chambreRepository).deleteById(1L);

        chambreService.removeChambre(1L);

        verify(chambreRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRecupererChambresSelonTyp() {
        when(chambreRepository.findAllByTypeC(TypeChambre.SIMPLE)).thenReturn(List.of(chambre1));

        List<Chambre> chambres = chambreService.recupererChambresSelonTyp(TypeChambre.SIMPLE);

        assertNotNull(chambres);
        assertEquals(1, chambres.size());
        assertEquals(TypeChambre.SIMPLE, chambres.get(0).getTypeC());
        verify(chambreRepository, times(1)).findAllByTypeC(TypeChambre.SIMPLE);
    }

    @Test
    void testTrouverChambreSelonEtudiant() {
        when(chambreRepository.trouverChselonEt(12345678L)).thenReturn(chambre1);

        Chambre chambre = chambreService.trouverchambreSelonEtudiant(12345678L);

        assertNotNull(chambre);
        assertEquals(101L, chambre.getNumeroChambre());
        verify(chambreRepository, times(1)).trouverChselonEt(12345678L);
    }
}
