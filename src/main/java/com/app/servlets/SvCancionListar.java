package com.app.servlets;

import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SvCancionListar", urlPatterns = {"/SvCancionListar"})
public class SvCancionListar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ControladoraLogica control = new ControladoraLogica();
        List<Cancion> listaCanciones = control.listarCanciones();

        // Guardamos la lista en el atributo para el JSP
        request.setAttribute("canciones", listaCanciones);

        // Forward a la vista
        request.getRequestDispatcher("/WEB-INF/vistas/canciones.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}