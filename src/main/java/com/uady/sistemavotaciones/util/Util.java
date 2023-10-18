package com.uady.sistemavotaciones.util;

import java.io.InputStream;
import java.net.URL;

public class Util {

    public static URL obtenerPath(String filePath) {
        URL path = Util.class.getResource(filePath);
        if (path == null) {
            System.out.printf("Archivo %s no encontrado.", filePath);
            System.exit(1);
        }
        return path;
    }

    public static InputStream obtenerImg(String filePath) {
        InputStream path = Util.class.getResourceAsStream(filePath);
        if (path == null) {
            System.out.printf("Imagen %s no encontrado.", filePath);
            System.exit(1);
        }
        return path;
    }

}
