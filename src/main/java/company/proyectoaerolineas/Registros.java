package company.proyectoaerolineas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import modelos.Administrativo;
import modelos.Aerolinea;
import modelos.Avion;
import modelos.Azafata;
import modelos.Boleto;
import modelos.Clase;
import modelos.Cliente;
import modelos.Piloto;
import modelos.Vuelo;

public class Registros{
    public static void main(String[] args){
        //Administrativo
        // Registro 1
        Administrativo admin1 = new Administrativo();
        admin1.setDeptoTrabajo("Operaciones");
        admin1.setHorarioEntrada(LocalTime.of(8, 0));
        admin1.setHorarioSalida(LocalTime.of(16, 0));
        admin1.setPuesto("Supervisor");
        admin1.setTipoContrato("TIEMPO_COMPLETO");
        admin1.setAnosExperiencia(5);
        admin1.setCorreoElectronico("admin1@aerolinea.com");
        admin1.setContrasena("Admin1234");

        // Registro 2
        Administrativo admin2 = new Administrativo();
        admin2.setDeptoTrabajo("Recursos Humanos");
        admin2.setHorarioEntrada(LocalTime.of(9, 30));
        admin2.setHorarioSalida(LocalTime.of(17, 30));
        admin2.setPuesto("Reclutador");
        admin2.setTipoContrato("MEDIO_TIEMPO");
        admin2.setAnosExperiencia(2);
        admin2.setCorreoElectronico("rh@aerolinea.com");
        admin2.setContrasena("Rh123456");

        // Registro 3
        Administrativo admin3 = new Administrativo();
        admin3.setDeptoTrabajo("Finanzas");
        admin3.setHorarioEntrada(LocalTime.of(7, 0));
        admin3.setHorarioSalida(LocalTime.of(15, 0));
        admin3.setPuesto("Contador");
        admin3.setTipoContrato("TIEMPO_COMPLETO");
        admin3.setAnosExperiencia(10);
        admin3.setCorreoElectronico("contabilidad@aerolinea.com");
        admin3.setContrasena("Conta2023");
        
        //Aerolinea
        // Registro 1
        Aerolinea aero1 = new Aerolinea();
        aero1.setNombre("AeroMéxico");
        aero1.setPais("México");
        aero1.setCentroOperacionPrincipal("Ciudad de México");
        aero1.setBases(List.of("Monterrey", "Guadalajara", "Cancún"));
        aero1.setSitioOficial("https://www.aeromexico.com");
        aero1.setNombreContacto("Juan Pérez");
        aero1.setTelefono("+525512345678");

        // Registro 2
        Aerolinea aero2 = new Aerolinea();
        aero2.setNombre("Delta Airlines");
        aero2.setPais("Estados Unidos");
        aero2.setCentroOperacionPrincipal("Atlanta");
        aero2.setBases(List.of("Nueva York", "Los Ángeles", "Miami"));
        aero2.setSitioOficial("https://www.delta.com");
        aero2.setNombreContacto("John Smith");
        aero2.setTelefono("+18002212345");

        // Registro 3
        Aerolinea aero3 = new Aerolinea();
        aero3.setNombre("Lufthansa");
        aero3.setPais("Alemania");
        aero3.setCentroOperacionPrincipal("Fráncfort");
        aero3.setBases(List.of("Berlín", "Múnich", "Hamburgo"));
        aero3.setSitioOficial("https://www.lufthansa.com");
        aero3.setNombreContacto("Hans Müller");
        aero3.setTelefono("+4969123456");
        
        //Avion
        // Registro 1
        Avion avion1 = new Avion();
        avion1.setNombre("Boeing 787");
        avion1.setCapacidad(300);
        avion1.setModelo("787-9");
        avion1.setPeso(254000);
        avion1.setMatricula("XA-MEX");
        avion1.setAerolineaPropietaria("AeroMéxico");

        // Registro 2
        Avion avion2 = new Avion();
        avion2.setNombre("Airbus A350");
        avion2.setCapacidad(350);
        avion2.setModelo("A350-900");
        avion2.setPeso(280000);
        avion2.setMatricula("N123DA");
        avion2.setAerolineaPropietaria("Delta Airlines");

        // Registro 3
        Avion avion3 = new Avion();
        avion3.setNombre("Boeing 747");
        avion3.setCapacidad(400);
        avion3.setModelo("747-8");
        avion3.setPeso(447000);
        avion3.setMatricula("D-ABYT");
        avion3.setAerolineaPropietaria("Lufthansa");
        
        //Azafata
        // Registro 1
        Azafata azafata1 = new Azafata();
        azafata1.setNombre("María González");
        azafata1.setDireccion("Av. Reforma 123, CDMX");
        azafata1.setFechaNacimiento(LocalDate.of(1990, 5, 15));
        azafata1.setGenero("Femenino");
        azafata1.setSalario(25000.00);
        azafata1.setCorreoElectronico("maria.g@aerolinea.com");
        azafata1.setContrasena("Azafata123");
        azafata1.setNumIdiomas(3);
        azafata1.setAnoInicio(LocalDate.of(2015, 3, 10));

        // Registro 2
        Azafata azafata2 = new Azafata();
        azafata2.setNombre("Ana López");
        azafata2.setDireccion("Calle Falsa 123, GDL");
        azafata2.setFechaNacimiento(LocalDate.of(1988, 8, 22));
        azafata2.setGenero("Femenino");
        azafata2.setSalario(28000.00);
        azafata2.setCorreoElectronico("ana.l@aerolinea.com");
        azafata2.setContrasena("Azafata456");
        azafata2.setNumIdiomas(4);
        azafata2.setAnoInicio(LocalDate.of(2018, 6, 15));

        // Registro 3
        Azafata azafata3 = new Azafata();
        azafata3.setNombre("Carlos Sánchez");
        azafata3.setDireccion("Boulevard Kukulcan Km 12, Cancún");
        azafata3.setFechaNacimiento(LocalDate.of(1992, 11, 5));
        azafata3.setGenero("Masculino");
        azafata3.setSalario(27000.00);
        azafata3.setCorreoElectronico("carlos.s@aerolinea.com");
        azafata3.setContrasena("Azafata789");
        azafata3.setNumIdiomas(2);
        azafata3.setAnoInicio(LocalDate.of(2020, 1, 20));
        
        //Registro 4
        Azafata azafata4 = new Azafata();
        azafata4.setNombre("Patricia Ramírez");
        azafata4.setDireccion("Av. Universidad 456, MTY");
        azafata4.setFechaNacimiento(LocalDate.of(1991, 7, 30));
        azafata4.setGenero("Femenino");
        azafata4.setSalario(26000.00);
        azafata4.setCorreoElectronico("patricia.r@aerolinea.com");
        azafata4.setContrasena("Azafata012");
        azafata4.setNumIdiomas(3);
        azafata4.setAnoInicio(LocalDate.of(2019, 5, 12));
        
        //Clase
        // Registro 1
        Clase clase1 = new Clase();
        clase1.setNombre("Económica");
        clase1.setCapacidad(200);
        clase1.setPrecioBase(5000.00);
        clase1.setMultiplicadorPrecio(1.0);
        clase1.setTipo(Clase.TipoClase.ECONOMICA);

        // Registro 2
        Clase clase2 = new Clase();
        clase2.setNombre("Ejecutiva");
        clase2.setCapacidad(50);
        clase2.setPrecioBase(12000.00);
        clase2.setMultiplicadorPrecio(1.8);
        clase2.setTipo(Clase.TipoClase.EJECUTIVA);

        // Registro 3
        Clase clase3 = new Clase();
        clase3.setNombre("Primera Clase");
        clase3.setCapacidad(20);
        clase3.setPrecioBase(25000.00);
        clase3.setMultiplicadorPrecio(3.2);
        clase3.setTipo(Clase.TipoClase.PRIMERA_CLASE);
        
        //Cliente
        // Registro 1
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Juan Pérez");
        cliente1.setNacionalidad("Mexicana");
        cliente1.setFechaNacimiento(LocalDate.of(1985, 7, 12));
        cliente1.setCorreoElectronico("juan.perez@email.com");
        cliente1.setTelefono("+525512345678");
        cliente1.setPasaportes(List.of("M12345678"));

        // Registro 2
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Ana García");
        cliente2.setNacionalidad("Española");
        cliente2.setFechaNacimiento(LocalDate.of(1990, 3, 25));
        cliente2.setCorreoElectronico("ana.garcia@email.com");
        cliente2.setTelefono("+34678901234");
        cliente2.setPasaportes(List.of("E87654321", "US98765432"));

        // Registro 3
        Cliente cliente3 = new Cliente();
        cliente3.setNombre("John Smith");
        cliente3.setNacionalidad("Estadounidense");
        cliente3.setFechaNacimiento(LocalDate.of(1978, 11, 8));
        cliente3.setCorreoElectronico("john.smith@email.com");
        cliente3.setTelefono("+15551234567");
        cliente3.setPasaportes(List.of("US12345678"));
        
        //Piloto
        // Registro 1
        Piloto piloto1 = new Piloto();
        piloto1.setNombre("Roberto Martínez");
        piloto1.setDireccion("Av. Insurgentes 456, CDMX");
        piloto1.setFechaNacimiento(LocalDate.of(1980, 4, 18));
        piloto1.setGenero("Masculino");
        piloto1.setSalario(85000.00);
        piloto1.setCorreoElectronico("roberto.m@aerolinea.com");
        piloto1.setContrasena("Piloto123");
        piloto1.setAnoInicio(LocalDate.of(2005, 6, 15));
        piloto1.setTipoLicencia("ATP");

        // Registro 2
        Piloto piloto2 = new Piloto();
        piloto2.setNombre("Laura Díaz");
        piloto2.setDireccion("Calle Central 789, MTY");
        piloto2.setFechaNacimiento(LocalDate.of(1985, 9, 22));
        piloto2.setGenero("Femenino");
        piloto2.setSalario(90000.00);
        piloto2.setCorreoElectronico("laura.d@aerolinea.com");
        piloto2.setContrasena("Piloto456");
        piloto2.setAnoInicio(LocalDate.of(2010, 3, 10));
        piloto2.setTipoLicencia("ATP");

        // Registro 3
        Piloto piloto3 = new Piloto();
        piloto3.setNombre("Carlos Ramírez");
        piloto3.setDireccion("Paseo de la Reforma 1010, CDMX");
        piloto3.setFechaNacimiento(LocalDate.of(1975, 12, 5));
        piloto3.setGenero("Masculino");
        piloto3.setSalario(95000.00);
        piloto3.setCorreoElectronico("carlos.r@aerolinea.com");
        piloto3.setContrasena("Piloto789");
        piloto3.setAnoInicio(LocalDate.of(1998, 8, 20));
        piloto3.setTipoLicencia("ATP");
        
        //Vuelo
        // Configuramos fechas que no se solapen
        LocalDate fechaVuelo1 = LocalDate.now().plusDays(5);
        LocalDate fechaVuelo2 = LocalDate.now().plusDays(7); // 2 días después que vuelo1
        LocalDate fechaVuelo3 = LocalDate.now().plusDays(10); // 3 días después que vuelo2
        
        // Vuelo 1: CDMX - Nueva York (usa avion1)
        Vuelo vuelo1 = new Vuelo();
        vuelo1.setCiudadSalida("Ciudad de México");
        vuelo1.setCiudadLlegada("Nueva York");
        vuelo1.setFechaSalida(fechaVuelo1);
        vuelo1.setFechaLlegada(fechaVuelo1);
        vuelo1.setHoraSalida(LocalTime.of(8, 30));
        vuelo1.setHoraLlegada(LocalTime.of(14, 0));
        vuelo1.setClase(clase1);
        vuelo1.setAvion(avion1);
        vuelo1.setPilotos(List.of(piloto1, piloto2)); // Asignamos pilotos 1 y 2
        vuelo1.setAzafatas(List.of(azafata1, azafata2, azafata3, azafata4)); // 4 azafatas
        vuelo1.setCostoBoleto(6500.00);

        // Vuelo 2: Guadalajara - Los Ángeles (usa avion2) - 2 días después
        Vuelo vuelo2 = new Vuelo();
        vuelo2.setCiudadSalida("Guadalajara");
        vuelo2.setCiudadLlegada("Los Ángeles");
        vuelo2.setFechaSalida(fechaVuelo2);
        vuelo2.setFechaLlegada(fechaVuelo2);
        vuelo2.setHoraSalida(LocalTime.of(10, 0));
        vuelo2.setHoraLlegada(LocalTime.of(12, 30));
        vuelo2.setClase(clase2);
        vuelo2.setAvion(avion2);
        vuelo2.setPilotos(List.of(piloto3, piloto1)); // Piloto3 y piloto1 (piloto2 ya voló hace 2 días)
        vuelo2.setAzafatas(List.of(azafata2, azafata3, azafata4, azafata1)); // Rotamos azafatas
        vuelo2.setCostoBoleto(12000.00);

        // Vuelo 3: Cancún - Miami (usa avion3) - 3 días después del vuelo2
        Vuelo vuelo3 = new Vuelo();
        vuelo3.setCiudadSalida("Cancún");
        vuelo3.setCiudadLlegada("Miami");
        vuelo3.setFechaSalida(fechaVuelo3);
        vuelo3.setFechaLlegada(fechaVuelo3);
        vuelo3.setHoraSalida(LocalTime.of(15, 0));
        vuelo3.setHoraLlegada(LocalTime.of(17, 30));
        vuelo3.setClase(clase3);
        vuelo3.setAvion(avion3);
        vuelo3.setPilotos(List.of(piloto2, piloto3)); // Piloto2 y piloto3 (piloto1 ya voló hace 3 días)
        vuelo3.setAzafatas(List.of(azafata3, azafata4, azafata1, azafata2)); // Rotamos azafatas
        vuelo3.setCostoBoleto(25000.00);
        
        
        //Boleto
        // Registro 1
        Boleto boleto1 = new Boleto();
        boleto1.setCliente(cliente1);
        boleto1.setVuelo(vuelo1);
        boleto1.setClase(clase1);
        boleto1.setCosto(800.00);
        boleto1.setAsiento("A12");
        boleto1.pagar(); // Cambia el estado a PAGADO

        // Registro 2
        Boleto boleto2 = new Boleto();
        boleto2.setCliente(cliente2);
        boleto2.setVuelo(vuelo2);
        boleto2.setClase(clase2);
        boleto2.setCosto(1800.00);
        boleto2.setAsiento("B7");
        // Se queda en estado RESERVADO

        // Registro 3
        Boleto boleto3 = new Boleto();
        boleto3.setCliente(cliente3);
        boleto3.setVuelo(vuelo3);
        boleto3.setClase(clase3);
        boleto3.setCosto(3000.00);
        boleto3.setAsiento("C3");
        boleto3.pagar();
        boleto3.utilizar(); // Cambia el estado a UTILIZADO
    }
}