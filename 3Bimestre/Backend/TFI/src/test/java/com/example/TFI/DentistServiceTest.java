package com.example.TFI;

import com.example.TFI.Models.Dentist;
import com.example.TFI.Services.IDentistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DentistServiceTest {
    @Autowired
    private IDentistService dentistService;
//    private final DentistService dentistService = new DentistService(H2Database.getInMemoryConnection(), new DentistRepositoryJDBC(H2Database.getInMemoryConnection()), new UserRepositoryJDBC(H2Database.getInMemoryConnection()));

    @Test
    public void createDentist() throws SQLException {
        Dentist dentist = new Dentist("test_dentist_1", "hola1234",3785, "Ian", "Vaernet");
        Dentist createdDentist = dentistService.createDentist(dentist);
        assertNotNull(createdDentist);
        assertTrue(createdDentist.getId() > 0);
    }

    @Test
    public void getDentist() throws SQLException {
        Dentist dentist = new Dentist("test_dentist_2", "hola1234",3785, "Ian", "Vaernet");
        dentist = dentistService.createDentist(dentist);
        Dentist readedDentist = dentistService.getDentist(dentist.getId());
        assertNotNull(readedDentist);
    }

    @Test
    public void updateDentist() throws SQLException {
        Dentist dentist = new Dentist("test_dentist_3", "hola1234",3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        dentist.setLicenseNumber(1234);
        dentist.setFirstName("Ian Alexis");
        dentist.setLastName("Vaernet Pochon");
        Dentist updatedDentist = dentistService.updateDentist(dentist.getId(), dentist);
        assertEquals(1234, updatedDentist.getLicenseNumber());
        assertEquals("Ian Alexis", updatedDentist.getFirstName());
        assertEquals("Vaernet Pochon", updatedDentist.getLastName());
    }

    @Test
    public void deleteDentist() throws SQLException {
        Dentist dentist = new Dentist("test_dentist_4", "hola1234",3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        boolean deleted = dentistService.deleteDentist(dentist.getId());
        assertTrue(deleted);
        assertNull(dentistService.getDentist(dentist.getId()));
    }

    @Test
    public void listDentists() throws SQLException {
        Dentist dentist = new Dentist("test_dentist_5", "hola1234",3785, "Ian", "Vaernet");
        dentistService.createDentist(dentist);
        List<Dentist> dentists = dentistService.listDentists();
        assertTrue(dentists.size() > 0);
    }
}
