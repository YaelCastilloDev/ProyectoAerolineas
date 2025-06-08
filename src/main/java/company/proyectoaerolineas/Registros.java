package company.proyectoaerolineas;

import controladores.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import modelos.*;

public class Registros {
    public static void main(String[] args) {
        // 1. Instanciar todos los controladores necesarios
        AdministrativoControlador adminController = new AdministrativoControlador();
        AerolineaControlador aerolineaController = new AerolineaControlador();
        AvionControlador avionController = new AvionControlador();
        AzafataControlador azafataController = new AzafataControlador();
        ClaseControlador claseController = new ClaseControlador();
        ClienteControlador clienteController = new ClienteControlador();
        PilotoControlador pilotoController = new PilotoControlador();
        VueloControlador vueloController = new VueloControlador();
        BoletoControlador boletoController = new BoletoControlador();

        // 2. Creación de administrativos
        try {
            Administrativo admin1 = new Administrativo();
            admin1.setDeptoTrabajo("Operaciones");
            admin1.setHorarioEntrada(LocalTime.of(8, 0));
            admin1.setHorarioSalida(LocalTime.of(16, 0));
            admin1.setPuesto("Supervisor");
            admin1.setTipoContrato("TIEMPO_COMPLETO");
            admin1.setAnosExperiencia(5);
            admin1.setCorreoElectronico("admin1@aerolinea.com");
            admin1.setContrasena("Admin1234");
            adminController.crear(admin1);

            Administrativo admin2 = new Administrativo();
            admin2.setDeptoTrabajo("Recursos Humanos");
            admin2.setHorarioEntrada(LocalTime.of(9, 30));
            admin2.setHorarioSalida(LocalTime.of(17, 30));
            admin2.setPuesto("Reclutador");
            admin2.setTipoContrato("MEDIO_TIEMPO");
            admin2.setAnosExperiencia(2);
            admin2.setCorreoElectronico("rh@aerolinea.com");
            admin2.setContrasena("Rh123456");
            adminController.crear(admin2);

            Administrativo admin3 = new Administrativo();
            admin3.setDeptoTrabajo("Finanzas");
            admin3.setHorarioEntrada(LocalTime.of(7, 0));
            admin3.setHorarioSalida(LocalTime.of(15, 0));
            admin3.setPuesto("Contador");
            admin3.setTipoContrato("TIEMPO_COMPLETO");
            admin3.setAnosExperiencia(10);
            admin3.setCorreoElectronico("contabilidad@aerolinea.com");
            admin3.setContrasena("Conta2023");
            adminController.crear(admin3);
        } catch (IllegalArgumentException e) {
            System.err.println("Error creando administrativo: " + e.getMessage());
        }

        // 3. Creación de aerolíneas
        try {
            Aerolinea aero1 = new Aerolinea();
            aero1.setNombre("AeroMéxico");
            aero1.setPais("México");
            aero1.setCentroOperacionPrincipal("Ciudad de México");
            aero1.setBases(List.of("Monterrey", "Guadalajara", "Cancún"));
            aero1.setSitioOficial("https://www.aeromexico.com");
            aero1.setNombreContacto("Juan Pérez");
            aero1.setTelefono("+525512345678");
            aerolineaController.crear(aero1);

            Aerolinea aero2 = new Aerolinea();
            aero2.setNombre("Delta Airlines");
            aero2.setPais("Estados Unidos");
            aero2.setCentroOperacionPrincipal("Atlanta");
            aero2.setBases(List.of("Nueva York", "Los Ángeles", "Miami"));
            aero2.setSitioOficial("https://www.delta.com");
            aero2.setNombreContacto("John Smith");
            aero2.setTelefono("+18002212345");
            aerolineaController.crear(aero2);

            Aerolinea aero3 = new Aerolinea();
            aero3.setNombre("Lufthansa");
            aero3.setPais("Alemania");
            aero3.setCentroOperacionPrincipal("Fráncfort");
            aero3.setBases(List.of("Berlín", "Múnich", "Hamburgo"));
            aero3.setSitioOficial("https://www.lufthansa.com");
            aero3.setNombreContacto("Hans Müller");
            aero3.setTelefono("+4969123456");
            aerolineaController.crear(aero3);
        } catch (IllegalArgumentException e) {
            System.err.println("Error creando aerolínea: " + e.getMessage());
        }

        // 4. Creación de aviones
        try {
            avionController.crearAvion(
                "Boeing 787",
                300,
                "787-9",
                254000,
                "XA-MEX",
                "AeroMéxico"
            );

            avionController.crearAvion(
                "Airbus A350",
                350,
                "A350-900",
                280000,
                "N123DA",
                "Delta Airlines"
            );

            avionController.crearAvion(
                "Boeing 747",
                400,
                "747-8",
                447000,
                "D-ABYT",
                "Lufthansa"
            );
        } catch (IllegalArgumentException e) {
            System.err.println("Error creando avión: " + e.getMessage());
        }

        // 5. Creación de azafatas
        try {
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
            azafataController.crear(azafata1);

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
            azafataController.crear(azafata2);

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
            azafataController.crear(azafata3);

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
            azafataController.crear(azafata4);
        } catch (IllegalArgumentException e) {
            System.err.println("Error creando azafata: " + e.getMessage());
        }

        // 6. Creación de clases
        try {
            claseController.crearClase(
                "Económica",
                200,
                5000.00,
                1.0,
                Clase.TipoClase.ECONOMICA
            );

            claseController.crearClase(
                "Ejecutiva",
                50,
                12000.00,
                1.8,
                Clase.TipoClase.EJECUTIVA
            );

            claseController.crearClase(
                "Primera Clase",
                20,
                25000.00,
                3.2,
                Clase.TipoClase.PRIMERA_CLASE
            );
        } catch (IllegalArgumentException e) {
            System.err.println("Error creando clase: " + e.getMessage());
        }

        // 7. Creación de clientes
        try {
            Cliente cliente1 = new Cliente();
            cliente1.setNombre("Juan Pérez");
            cliente1.setNacionalidad("Mexicana");
            cliente1.setFechaNacimiento(LocalDate.of(1985, 7, 12));
            cliente1.setCorreoElectronico("juan.perez@email.com");
            cliente1.setTelefono("+525512345678");
            cliente1.setPasaportes(List.of("M12345678"));
            clienteController.crear(cliente1);

            Cliente cliente2 = new Cliente();
            cliente2.setNombre("Ana García");
            cliente2.setNacionalidad("Española");
            cliente2.setFechaNacimiento(LocalDate.of(1990, 3, 25));
            cliente2.setCorreoElectronico("ana.garcia@email.com");
            cliente2.setTelefono("+34678901234");
            cliente2.setPasaportes(List.of("E87654321", "US98765432"));
            clienteController.crear(cliente2);

            Cliente cliente3 = new Cliente();
            cliente3.setNombre("John Smith");
            cliente3.setNacionalidad("Estadounidense");
            cliente3.setFechaNacimiento(LocalDate.of(1978, 11, 8));
            cliente3.setCorreoElectronico("john.smith@email.com");
            cliente3.setTelefono("+15551234567");
            cliente3.setPasaportes(List.of("US12345678"));
            clienteController.crear(cliente3);
        } catch (IllegalArgumentException e) {
            System.err.println("Error creando cliente: " + e.getMessage());
        }

        // 8. Creación de pilotos
        try {
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
            pilotoController.crear(piloto1);

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
            pilotoController.crear(piloto2);

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
            pilotoController.crear(piloto3);
        } catch (IllegalArgumentException e) {
            System.err.println("Error creando piloto: " + e.getMessage());
        }

        // 9. Creación de vuelos
        try {
            // Obtener instancias necesarias
            Clase claseEconomica = claseController.buscarClase("Económica");
            Clase claseEjecutiva = claseController.buscarClase("Ejecutiva");
            Clase clasePrimera = claseController.buscarClase("Primera Clase");
            
            Avion avion1 = avionController.buscarAvion("XA-MEX");
            Avion avion2 = avionController.buscarAvion("N123DA");
            Avion avion3 = avionController.buscarAvion("D-ABYT");
            
            Piloto piloto1 = pilotoController.buscarPorId("roberto.m@aerolinea.com");
            Piloto piloto2 = pilotoController.buscarPorId("laura.d@aerolinea.com");
            Piloto piloto3 = pilotoController.buscarPorId("carlos.r@aerolinea.com");
            
            Azafata azafata1 = azafataController.buscarPorId("maria.g@aerolinea.com");
            Azafata azafata2 = azafataController.buscarPorId("ana.l@aerolinea.com");
            Azafata azafata3 = azafataController.buscarPorId("carlos.s@aerolinea.com");
            Azafata azafata4 = azafataController.buscarPorId("patricia.r@aerolinea.com");

            // Configurar fechas no solapadas
            LocalDate fechaVuelo1 = LocalDate.now().plusDays(5);
            LocalDate fechaVuelo2 = LocalDate.now().plusDays(7);
            LocalDate fechaVuelo3 = LocalDate.now().plusDays(10);

            // Vuelo 1: CDMX - Nueva York
            vueloController.crearVuelo(
                "Ciudad de México",
                "Nueva York",
                fechaVuelo1,
                fechaVuelo1,
                LocalTime.of(8, 30),
                LocalTime.of(14, 0),
                claseEconomica,
                avion1,
                List.of(piloto1, piloto2),
                List.of(azafata1, azafata2, azafata3, azafata4)
            );

            // Vuelo 2: Guadalajara - Los Ángeles
            vueloController.crearVuelo(
                "Guadalajara",
                "Los Ángeles",
                fechaVuelo2,
                fechaVuelo2,
                LocalTime.of(10, 0),
                LocalTime.of(12, 30),
                claseEjecutiva,
                avion2,
                List.of(piloto3, piloto1),
                List.of(azafata2, azafata3, azafata4, azafata1)
            );

            // Vuelo 3: Cancún - Miami
            vueloController.crearVuelo(
                "Cancún",
                "Miami",
                fechaVuelo3,
                fechaVuelo3,
                LocalTime.of(15, 0),
                LocalTime.of(17, 30),
                clasePrimera,
                avion3,
                List.of(piloto2, piloto3),
                List.of(azafata3, azafata4, azafata1, azafata2)
            );
        } catch (Exception e) {
            System.err.println("Error creando vuelo: " + e.getMessage());
            e.printStackTrace();
        }

        // 10. Creación de boletos
        try {
            // Obtener instancias necesarias
            Cliente cliente1 = clienteController.buscarPorId("juan.perez@email.com");
            Cliente cliente2 = clienteController.buscarPorId("ana.garcia@email.com");
            Cliente cliente3 = clienteController.buscarPorId("john.smith@email.com");
            
            Vuelo vuelo1 = vueloController.buscarVuelo("Ciudad de México", 
                LocalDate.now().plusDays(5), LocalTime.of(8, 30));
            Vuelo vuelo2 = vueloController.buscarVuelo("Guadalajara", 
                LocalDate.now().plusDays(7), LocalTime.of(10, 0));
            Vuelo vuelo3 = vueloController.buscarVuelo("Cancún", 
                LocalDate.now().plusDays(10), LocalTime.of(15, 0));
            
            Clase claseEconomica = claseController.buscarClase("Económica");
            Clase claseEjecutiva = claseController.buscarClase("Ejecutiva");
            Clase clasePrimera = claseController.buscarClase("Primera Clase");

            // Boleto 1
            Boleto boleto1 = boletoController.crearBoleto(
                cliente1,
                vuelo1,
                claseEconomica,
                800.00,
                "A12"
            );
            boleto1.pagar();

            // Boleto 2
            Boleto boleto2 = boletoController.crearBoleto(
                cliente2,
                vuelo2,
                claseEjecutiva,
                1800.00,
                "B7"
            );
            // Se queda en estado RESERVADO

            // Boleto 3
            Boleto boleto3 = boletoController.crearBoleto(
                cliente3,
                vuelo3,
                clasePrimera,
                3000.00,
                "C3"
            );
            boleto3.pagar();
            boleto3.utilizar();
        } catch (Exception e) {
            System.err.println("Error creando boleto: " + e.getMessage());
        }

        System.out.println("Todos los registros se crearon exitosamente!");
    }
}