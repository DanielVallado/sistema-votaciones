package com.uady.sistemavotaciones.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class VotacionDAO implements DAO<String> {

    private final String filePath;

    public VotacionDAO(String path) {
        this.filePath = path;
    }

    @Override
    public void registrar(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));) {
            writer.write(message);
            System.out.println("Texto escrito en el archivo exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al registrar.");
        }
    }

    @Override
    public List<String> obtenerTodos() {
        return null;
    }

    @Override
    public void actualizar(String objeto) {

    }

    @Override
    public void eliminar(String objeto) {

    }

}
