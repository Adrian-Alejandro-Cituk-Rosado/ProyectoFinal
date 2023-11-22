package net.adriancituk.joseaguilar.utill;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {
	public static String guardarArchivo(MultipartFile multiPart, String ruta) {
	    String nombreOriginal = multiPart.getOriginalFilename();
	    nombreOriginal = nombreOriginal.replace(" ", "-"); // Corregir: se debe asignar el valor a nombreOriginal
	    String nombreFinal = randomAlphaNumeric(8) + nombreOriginal; // Utilizar nombreFinal
	    
	   
	try {
	        // Formamos el nombre del archivo para guardarlo en el disco duro.
	        File imageFile = new File(ruta + nombreFinal); // Utilizar nombreFinal
	        System.out.println("Archivo: " + imageFile.getAbsolutePath());
	        // Guardamos físicamente el archivo en el disco duro.
	        multiPart.transferTo(imageFile);
	        
	       return nombreFinal; // Corregir: se debe devolver el nombreFinal
	    } catch (IOException e) {
	        System.out.println(
	       
	"Error: " + e.getMessage());
	        return null;
	    }
	}

	public static String randomAlphaNumeric(int count) {
	    String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; // Corregir caracteres
	    StringBuilder builder = new StringBuilder();
	    while (count-- != 0) {
	        int character = (int) (Math.random() * CARACTERES.length());
	        builder.append(CARACTERES.charAt(character));
	    }
	    return builder.toString();
	}
}
