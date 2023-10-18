package com.uady.sistemavotaciones.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VotacionDAO implements DAO<String> {

    private final String filePath;

    public VotacionDAO(String path) {
        this.filePath = path;
    }

    @Override
    public void registrar(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al registrar.\n" + e.getMessage());
        }
    }

    @Override
    public List<String> obtenerTodos() {
        List<String> lineas = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al registrar.\n" + e.getMessage());

        }

        return lineas;
    }

    @Override
    public void actualizar(String objeto) {

    }

    @Override
    public void eliminar(String objeto) {

    }

}
