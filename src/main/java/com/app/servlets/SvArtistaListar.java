package com.app.servlets;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SvArtistaListar", urlPatterns = {"/SvArtistaListar"})
public class SvArtistaListar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ControladoraLogica control = new ControladoraLogica();
        List<Artista> listaArtistas = control.listarArtistas();

        // Guardamos la lista en el atributo que va a leer el JSP
        request.setAttribute("artistas", listaArtistas);

        // Forward a la vista (carpeta protegida)
        request.getRequestDispatcher("/WEB-INF/vistas/artistas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}