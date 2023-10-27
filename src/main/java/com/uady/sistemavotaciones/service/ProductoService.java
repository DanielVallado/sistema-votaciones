package com.uady.sistemavotaciones.service;

import com.uady.sistemavotaciones.dao.VotacionDAO;
import com.uady.sistemavotaciones.model.Producto;
import com.uady.sistemavotaciones.util.MyFileReader;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProductoService {

    public List<Producto> obtenerProductos() {
        List<String> nombresProductos = MyFileReader.readFile("C:\\Users\\danie\\OneDrive - Universidad Autonoma de Yucatan\\LIS\\LIS - Quinto Semestre\\Aquitectura de Software\\ADA 8 - MVC Votaciones\\sistema-votaciones\\src\\main\\resources\\com\\uady\\sistemavotaciones\\productos.txt");
        if (nombresProductos == null){
            log.error("No hay productos");
            System.exit(0);
        }

        List<Producto> productos = new ArrayList<>();
        for (String nombre : nombresProductos) {
            String archivoPath = "C:\\Users\\danie\\OneDrive - Universidad Autonoma de Yucatan\\LIS\\LIS - Quinto Semestre\\Aquitectura de Software\\ADA 8 - MVC Votaciones\\sistema-votaciones\\src\\main\\resources\\com\\uady\\sistemavotaciones\\votaciones\\%s.txt".formatted(nombre);
            String imagenPath = "/com/uady/sistemavotaciones/images/%s.jpg".formatted(nombre);
            productos.add(new Producto(nombre, 0, archivoPath, imagenPath));
        }

        return productos;
    }

    public void votar(String filePath) {
        log.info("Voto registrado");
        String fechaHoraActual = obtenerHora();
        VotacionDAO votacionDAO = new VotacionDAO(filePath);
        votacionDAO.registrar(fechaHoraActual);
    }

    private String obtenerHora() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return fechaHoraActual.format(formatoFechaHora);
    }

    public int contarVotos(String filePath) {
        VotacionDAO votacionDAO = new VotacionDAO(filePath);
        return votacionDAO.obtenerTodos().size();
    }

}
