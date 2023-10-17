package com.uady.sistemavotaciones.dao;

import java.util.List;

public interface DAO<T> {

    void registrar(T objeto);

    List<T> obtenerTodos();

    void actualizar(T objeto);

    void eliminar(T objeto);

}

