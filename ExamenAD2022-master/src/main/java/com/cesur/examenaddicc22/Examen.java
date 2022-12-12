package com.cesur.examenaddicc22;

/**
 *
 * @author AQUI VUESTROS DATOS
 */
public class Examen {

    /* Incluid aqui vuestros datos */
    
    static final String AUTOR = "LOREN";
    static final String DNI = "X8425931L";
    
    public static void main(String[] args) {
        System.out.println("-------------------------------------------");
        System.out.println("EXAMEN ACCESO A DATOS");
        System.out.println("Diciembre 2022");
        System.out.println("Alumno: " + AUTOR);
        System.out.println("DNI: "+DNI);
        System.out.println("-------------------------------------------\n");

        System.out.println("\nEJERCICIO 1: Archivos");
        System.out.println("-------------------------------------------\n");
        
        Ejercicio1.solucion();
        
        System.out.println("\nEJERCICIO 2: JDBC");
        System.out.println("-------------------------------------------\n");
        
        Ejercicio2.solucion();

        System.out.println("\nEJERCICIO 3: Hibernate");
        System.out.println("-------------------------------------------\n");
        
        Ejercicio3.solucion();

        
    }
    
}
