package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.control.ReservationRestController;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IReservationService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationRestControllerTest {

    @Mock
    private IReservationService reservationService;

    @InjectMocks
    private ReservationRestController reservationRestController;

    private Reservation reservation1;
    private Reservation reservation2;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        reservation1 = new Reservation("R1", new Date(), true, null);
        reservation2 = new Reservation("R2", new Date(), false, null);
    }

    @Test
    void testGetReservations() {
        // Arrange
        List<Reservation> reservations = Arrays.asList(reservation1, reservation2);
        when(reservationService.retrieveAllReservations()).thenReturn(reservations);

        // Act
        List<Reservation> result = reservationRestController.getReservations();

        // Assert
        assertEquals(2, result.size());
        assertEquals("R1", result.get(0).getIdReservation());
        assertEquals("R2", result.get(1).getIdReservation());
    }

    @Test
    void testRetrieveReservation() {
        // Arrange
        when(reservationService.retrieveReservation("R1")).thenReturn(reservation1);

        // Act
        Reservation result = reservationRestController.retrieveReservation("R1");

        // Assert
        assertNotNull(result);
        assertEquals("R1", result.getIdReservation());
    }

    @Test
    void testAddReservation() {
        // Arrange
        when(reservationService.addReservation(any(Reservation.class))).thenReturn(reservation1);

        // Act
        Reservation result = reservationRestController.addReservation(reservation1);

        // Assert
        assertNotNull(result);
        assertEquals("R1", result.getIdReservation());
    }

    @Test
    void testRemoveReservation() {
        // Arrange
        doNothing().when(reservationService).removeReservation("R1");

        // Act
        reservationRestController.removeReservation("R1");

        // Assert
        verify(reservationService, times(1)).removeReservation("R1");
    }

    @Test
    void testModifyReservation() {
        // Arrange
        when(reservationService.modifyReservation(any(Reservation.class))).thenReturn(reservation1);

        // Act
        Reservation result = reservationRestController.modifyReservation(reservation1);

        // Assert
        assertNotNull(result);
        assertEquals("R1", result.getIdReservation());
    }
}
