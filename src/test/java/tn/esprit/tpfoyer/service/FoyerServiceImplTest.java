package tn.esprit.tpfoyer.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoyerServiceImplTest {

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    @Test
    void testRetrieveAllFoyers() {
        List<Foyer> mockFoyers = List.of(
                new Foyer(1L, "Foyer A", 100, null, null),
                new Foyer(2L, "Foyer B", 200, null, null)
        );

        // Mock the repository behavior
        when(foyerRepository.findAll()).thenReturn(mockFoyers);

        // Act
        List<Foyer> result = foyerService.retrieveAllFoyers();

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Foyer A", result.get(0).getNomFoyer());
        Assertions.assertEquals(100, result.get(0).getCapaciteFoyer());
        Assertions.assertEquals("Foyer B", result.get(1).getNomFoyer());
        Assertions.assertEquals(200, result.get(1).getCapaciteFoyer());

        // Verify repository interaction
        verify(foyerRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveFoyer() {
        Foyer mockFoyer = new Foyer(1L, "Foyer A", 100, null, null);

        when(foyerRepository.findById(1L)).thenReturn(Optional.of(mockFoyer));

        Foyer result = foyerService.retrieveFoyer(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Foyer A", result.getNomFoyer());
        Assertions.assertEquals(100, result.getCapaciteFoyer());

        verify(foyerRepository, times(1)).findById(1L);
    }

    @Test
    void testAddFoyer() {
        Foyer mockFoyer = new Foyer(null, "Foyer A", 100, null, null);

        when(foyerRepository.save(mockFoyer)).thenReturn(new Foyer(1L, "Foyer A", 100, null, null));

        Foyer result = foyerService.addFoyer(mockFoyer);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getIdFoyer());
        Assertions.assertEquals("Foyer A", result.getNomFoyer());
        Assertions.assertEquals(100, result.getCapaciteFoyer());
    }

    @Test
    void testModifyFoyer() {
        Foyer mockFoyer = new Foyer(1L, "Foyer Updated", 150, null, null);

        when(foyerRepository.save(mockFoyer)).thenReturn(mockFoyer);

        Foyer result = foyerService.modifyFoyer(mockFoyer);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getIdFoyer());
        Assertions.assertEquals("Foyer Updated", result.getNomFoyer());
        Assertions.assertEquals(150, result.getCapaciteFoyer());
    }

    @Test
    void testRemoveFoyer() {
        Long foyerId = 1L;

        // Act
        foyerService.removeFoyer(foyerId);

        // Verify repository interaction
        verify(foyerRepository, times(1)).deleteById(foyerId);
    }
}

