package com.app.servlets;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "SvArtistaEditar", urlPatterns = {"/SvArtistaEditar"})
public class SvArtistaEditar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ControladoraLogica control = new ControladoraLogica();
            Artista artista = control.buscarUnArtista(id);

            if (artista != null) {
                request.setAttribute("artista", artista);
                request.getRequestDispatcher("/WEB-INF/vistas/editarArtista.jsp").forward(request, response);
            } else {
                String msg = URLEncoder.encode("El artista no existe", StandardCharsets.UTF_8);
                response.sendRedirect("SvArtistaListar?error=" + msg);
            }
        } catch (Exception e) {
            String msg = URLEncoder.encode("ID inválido", StandardCharsets.UTF_8);
            response.sendRedirect("SvArtistaListar?error=" + msg);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String genero = request.getParameter("generoMusical");
            String nacionalidad = request.getParameter("nacionalidad");
            int edad = Integer.parseInt(request.getParameter("edad"));

            // Recuperar fecha de nacimiento
            String fechaTexto = request.getParameter("fechaNacimiento");

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = formato.parse(fechaTexto);

            Artista art = new Artista(nombre, genero, nacionalidad, edad, fechaNacimiento);
            art.setId(id);

            ControladoraLogica control = new ControladoraLogica();
            control.editarArtista(art);

            response.sendRedirect("SvArtistaListar");

        } catch (NumberFormatException e) {
            String msg = URLEncoder.encode("Datos numéricos inválidos", StandardCharsets.UTF_8);
            response.sendRedirect("SvArtistaListar?error=" + msg);

        } catch (Exception e) {
            String msg = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
            response.sendRedirect("SvArtistaListar?error=" + msg);
        }
    }
}