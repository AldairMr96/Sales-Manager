package com.company.bazar.serviceTest;
import com.company.bazar.model.Client;
import com.company.bazar.repository.IClientRepository;
import com.company.bazar.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class ClientServiceTest {

    @Mock
    private IClientRepository clientRepository;
    @InjectMocks
    private ClientService clientService;


    @BeforeEach

    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }
    @Test
    public  void createClientTest() {

        Client client = new Client(1L, "Aldair", "Martinez", "1234576", new ArrayList<>());

        when(clientRepository.save(any())).thenReturn(client);

        Client result = clientService.createCLient(client);


        verify(clientRepository, times(1)).save(client);
        assertTrue(result.getSales().isEmpty());
        assertEquals(result.getIdClient(), client.getIdClient());
        assertEquals(result.getNameClient(), client.getNameClient());
        assertEquals(result.getLastnameClient(), client.getLastnameClient());
        assertEquals(result.getDni(), client.getDni());
    }
    @Test
    public void getClientErrorTest (){
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());
        List<Client> clients = clientService.getClients();
        assertEquals(0, clients.size());
        assertTrue(clients.isEmpty());
    }
    @Test
    public void getClientTest(){
        List<Client> clients = List.of(
                new Client(1L, "Aldair", "Martinez", "123456789", new ArrayList<>()),
                new Client(2L, "Lina ", "Bermudez", "123456879", new ArrayList<>())
        );

        when(clientRepository.findAll()).thenReturn(clients);
        List<Client> result = clientService.getClients();

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public  void editClientErrorTest(){

       Client clientUpdated = new Client();
       when(clientRepository.findById(any())).thenThrow(new EntityNotFoundException("Cliente not found "));

       assertThrows(EntityNotFoundException.class, ()-> clientService.editClient(clientUpdated));
       verify(clientRepository,times(1)).findById(any());
       verify(clientRepository, times(0)).save(clientUpdated);

    }
    @Test
    public void editClienteSuccessTest(){
        Long idClientTest =1L;
        Client clientExisting =  new Client(idClientTest, "Aldair", "Martinez", "123456789", new ArrayList<>());
        Client clientUpdated = new Client(idClientTest,"Aldair", "Martinez", "123456790", new ArrayList<>() );

        when(clientRepository.findById(idClientTest)).thenReturn(Optional.of(clientExisting));
        when(clientRepository.save(clientExisting)).thenReturn(clientUpdated);

        Client result = clientService.editClient(clientExisting);

        verify(clientRepository, times(1)).findById(idClientTest);
        verify(clientRepository,times(1)).save(clientExisting);
        assertEquals(result.getIdClient(), clientUpdated.getIdClient());
        assertEquals(result.getNameClient(), clientUpdated.getNameClient());
        assertEquals(result.getLastnameClient(), clientUpdated.getLastnameClient());
        assertEquals(result.getDni(), clientUpdated.getDni());
        assertTrue(result.getSales().isEmpty());


    }

    @Test
    public  void findClientErrorTest(){
        when(clientRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows( EntityNotFoundException.class, ()-> clientService.findClient(1L));
    }
    @Test
    public  void findClientTest(){
        Client client = new Client(1L, "Aldair", "Martinez", "1234576", new ArrayList<>());
        when(clientRepository.findById(any())).thenReturn(Optional.of(client));
        Client result = clientService.findClient(1L);

        assertEquals("Aldair", result.getNameClient());
        assertInstanceOf( Long.class, result.getIdClient());
        assertTrue(result.getSales().isEmpty());
    }
    @Test
    public  void deleteClientErrorTest (){
        when(clientRepository.existsById(any())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, ()->clientService.deleteClient(1L));
        verify(clientRepository, never()).deleteById(1L);
    }
    @Test
    public void deleteClientTest(){
        Client client = new Client(1L, "Aldair", "Martinez", "1234576", new ArrayList<>());

        when(clientRepository.existsById(any())).thenReturn(true);
        clientService.deleteClient(client.getIdClient());
        verify(clientRepository, times(1)).deleteById(client.getIdClient());

    }
}
