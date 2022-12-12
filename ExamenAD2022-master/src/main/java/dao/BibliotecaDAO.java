package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.Ejemplar;
import models.Libro;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author FranciscoRomeroGuill
 */
public class BibliotecaDAO {
    
    private static SessionFactory sessionFactory;
    
    static{
        try{

            sessionFactory = new Configuration().configure().buildSessionFactory();
            
        }catch( HibernateException ex){
            throw new ExceptionInInitializerError(ex);
        }catch(Exception ex){
            System.out.println("Error iniciando Hibernate");
            System.out.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public void saveLibro( Libro e ){
        
        try ( var s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.save(e);
            t.commit();
        }
        
    }
  
    public HashSet<Libro> findByEstado(String estado){
        
        HashSet<Libro> salida = new HashSet<>();
        try ( var s = sessionFactory.openSession()) {
            var q = s.createQuery("FROM Ejemplar WHERE estado = :estado");
            q.setParameter("estado", estado);
        }
        return salida;
    }
    
    public void printInfo(){
        
        /* 
          Muestra por consola todos los libros de la biblioteca y el número
          de ejemplares disponibles de cada uno.
          
          Debe imprimirlo de esta manera:
        
          Biblioteca
          ----------
          Como aprender java en 24h. (3)
          Como ser buena persona (1)
          Aprobando exámenes fácilmente (5)
          ...
        
        */
        List<Libro> libros = new ArrayList();
        System.out.println("Biblioteca");
        System.out.println("----------");

        try ( var s = sessionFactory.openSession()) {
            var q = s.createQuery("FROM Libro l");

            libros = q.list();
            int numEjemplares = 0;
            for (int i = 0; i < libros.size(); i++) {
                numEjemplares = libros.get(i).getEjemplares().size();
                System.out.println(libros.get(i).getTitulo()+ " (" + numEjemplares+")");
                
            }
            System.out.println("...");
        
    }
    }
}

