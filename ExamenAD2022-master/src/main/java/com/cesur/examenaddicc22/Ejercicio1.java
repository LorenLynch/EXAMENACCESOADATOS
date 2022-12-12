package com.cesur.examenaddicc22;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Ejercicio1 {

    /**
     * Enunciado:
     * 
     * Completar el método estadísticasDeArchivo de manera que lea el archivo
     * de texto que se le pasa como parámetro, lo analice y muestre por consola 
     * el número de caracteres, palabras y líneas total.
     * 
     * Modificar solo el código del método.
     * 
     */
    
    static void solucion() {

        estadísticasDeArchivo("pom.xml");
    }

    private static void estadísticasDeArchivo(String archivo) {
        
         try ( var fr = new FileReader(archivo)) {
            int c;
            int numCaracteres = 0;
            int numLineas = 0;
            int numPalabras = 0;
            String linea = "";
            BufferedReader bfr = new BufferedReader(fr);
            while((linea = bfr.readLine())!= null){
                System.out.println(linea);
                numCaracteres += linea.length();
                numLineas++;
                
                //Consideramos una palabra todo aquello que se separe por un carácter que no sea una letra
                for(int i=0 ; i<linea.length() ; i++){
                    if(linea.toUpperCase().charAt(i) >=65 && linea.toUpperCase().charAt(i) <= 90 ){}
                    else{
                        numPalabras++;
                    }
                }
            }
            System.out.println("Estadísticas de "+archivo);
            System.out.println("El número de caracteres que hay en el archivo: "+numCaracteres);
            System.out.println("El número de líneas que hay en el archivo: "+numLineas);
            System.out.println("El número de palabras que hay en el archivo: "+numPalabras);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
