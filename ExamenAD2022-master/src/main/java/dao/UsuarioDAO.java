package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Usuario;

public class UsuarioDAO {
    
    private Connection connection;
    
    /* Completar consultas */
    static final String INSERT_QUERY ="INSERT INTO usuario (nombre, apellidos, dni) VALUES (?, ?, ?);";
    static final String LIST_QUERY="SELECT * FROM usuario";
    static final String GET_BY_DNI="SELECT * FROM usuario WHERE dni = ?;";
    
    
    public void connect(){
        var conf = new Properties();
        try {
                conf.load(new FileReader("config.properties"));

                this.connection = DriverManager.getConnection(conf.getProperty("url"), conf.getProperty("user"), conf.getProperty("password"));
                System.out.println("Conexión realizada con éxito");

            }catch (FileNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }catch(Exception ex){
                System.out.println("Error al conectar a la base de datos");
                System.out.println("ex");
        }     
    }
    
    public void close(){
        try {
            connection.close();
        } catch (Exception ex) {
            System.out.println("Error al cerrar la base de datos");     
        }
    }
    
    public void save(Usuario user){
        try(var pst = connection.prepareStatement(INSERT_QUERY, RETURN_GENERATED_KEYS)){
            
            pst.setString(1, user.getNombre());
            pst.setString(2, user.getApellidos());
            pst.setString(3, user.getDni());
            
            if (pst.executeUpdate() > 0){
                
                var keys = pst.getGeneratedKeys();
                keys.next();
                
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Método save completado");

    }

    public ArrayList<Usuario> list(){

        var salida = new ArrayList<Usuario>(0);
        
        try( var pst = connection.prepareStatement(LIST_QUERY)){
            
            ResultSet resultado = pst.executeQuery();
            
            while(resultado.next()){
                var usuario = new Usuario();
                usuario.setId(resultado.getLong("id") );
                usuario.setNombre( resultado.getString("nombre") );
                usuario.setApellidos(resultado.getString("apellidos") );
                usuario.setDni(resultado.getString("dni") );
                salida.add(usuario);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salida;
    }    
    
    public Usuario getByDNI(String dni){
        
        Usuario salida = new Usuario();
        
        try( var pst = connection.prepareStatement(GET_BY_DNI)){
            
            pst.setString(1, dni);
            ResultSet resultado = pst.executeQuery();
            
            while(resultado.next()){
                salida.setId(resultado.getLong("id") );
                salida.setNombre( resultado.getString("nombre") );
                salida.setApellidos(resultado.getString("apellidos") );
                salida.setDni(resultado.getString("dni") );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return salida;
    }    
}
