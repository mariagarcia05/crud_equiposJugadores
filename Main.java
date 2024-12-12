package gestionEquipos;
import jakarta.persistence.*;
import java.util.List;

public class Main {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("miUnidadPersistencia");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {

        // Insertar equipos y jugadores
        insertarDatos();

        // Leer equipos y jugadores
        leerDatos();

        // Actualizar un equipo y un jugador
        actualizarDatos();

        // Eliminar un equipo y un jugador
        eliminarDatos();

        // Ordenar equipos por ID y nombre
        ordenarEquipos();
    }

    // Insertar equipos y jugadores
    public static void insertarDatos() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Equipo equipo1 = new Equipo("Real Madrid", "Santiago Bernabéu");
        Equipo equipo2 = new Equipo("FC Barcelona", "Camp Nou");

        entityManager.persist(equipo1);
        entityManager.persist(equipo2);

        Jugador jugador1 = new Jugador("Karim Benzema", 1.85, 81, equipo1);
        Jugador jugador2 = new Jugador("Lionel Messi", 1.70, 72, equipo2);

        entityManager.persist(jugador1);
        entityManager.persist(jugador2);

        transaction.commit();
    }

    // Leer equipos y jugadores
    public static void leerDatos() {
        System.out.println("Equipos y jugadores:");

        List<Equipo> equipos = entityManager.createQuery("SELECT e FROM Equipo e", Equipo.class).getResultList();
        for (Equipo equipo : equipos) {
            System.out.println(equipo);
            for (Jugador jugador : equipo.getJugadores()) {
                System.out.println(jugador);
            }
        }
    }

    // Actualizar un equipo y un jugador
    public static void actualizarDatos() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Equipo equipo = entityManager.find(Equipo.class, 1);
        equipo.setNombre("Real Madrid CF");
        entityManager.merge(equipo);

        Jugador jugador = entityManager.find(Jugador.class, 1);
        jugador.setPeso(82F);
        entityManager.merge(jugador);

        transaction.commit();
    }

    // Eliminar un equipo y un jugador
    public static void eliminarDatos() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Jugador jugador = entityManager.find(Jugador.class, 2);
        entityManager.remove(jugador);

        Equipo equipo = entityManager.find(Equipo.class, 2);
        entityManager.remove(equipo);

        transaction.commit();
    }

    // Ordenar equipos por su ID o nombre
    public static void ordenarEquipos() {
        System.out.println("Equipos ordenados por nombre:");

        List<Equipo> equiposOrdenados = entityManager.createQuery("SELECT e FROM Equipo e ORDER BY e.nombre", Equipo.class).getResultList();
        for (Equipo equipo : equiposOrdenados) {
            System.out.println(equipo);
        }
    }

}
