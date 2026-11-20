package com.app.servlets;

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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SvArtistaCrear", urlPatterns = {"/SvArtistaCrear"})
public class SvArtistaCrear extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String generoMusical = request.getParameter("generoMusical");
        String nacionalidad = request.getParameter("nacionalidad");

        try {
            int edad = Integer.parseInt(request.getParameter("edad"));

            // Obtener y convertir la fecha
            String fechaTexto = request.getParameter("fechaNacimiento");
            Date fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fechaTexto);

            Artista artista = new Artista(nombre, generoMusical, nacionalidad, edad, fechaNacimiento);

            ControladoraLogica control = new ControladoraLogica();

            control.crearArtista(artista);

            response.sendRedirect("SvArtistaListar");

        } catch (NumberFormatException e) {

            String msg = URLEncoder.encode(
                    "La edad debe ser un número válido",
                    StandardCharsets.UTF_8
            );

            response.sendRedirect("SvArtistaListar?error=" + msg);

        } catch (Exception e) {

            String msg = URLEncoder.encode(
                    e.getMessage(),
                    StandardCharsets.UTF_8
            );

            response.sendRedirect("SvArtistaListar?error=" + msg);
        }
    }
}