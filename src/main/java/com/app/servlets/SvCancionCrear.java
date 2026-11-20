package com.app.servlets;

import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "SvCancionCrear", urlPatterns = {"/SvCancionCrear"})
public class SvCancionCrear extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titulo = request.getParameter("titulo");

        try {
            float duracion = Float.parseFloat(request.getParameter("duracion"));
            int anio = Integer.parseInt(request.getParameter("anio"));

            Cancion cancion = new Cancion(titulo, duracion, anio);
            ControladoraLogica control = new ControladoraLogica();

            control.crearCancion(cancion);

            response.sendRedirect("SvCancionListar");

        } catch (NumberFormatException e) {
            String msg = URLEncoder.encode("La duración y el año deben ser números válidos", StandardCharsets.UTF_8);
            response.sendRedirect("SvCancionListar?error=" + msg);
        } catch (Exception e) {
            String msg = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
            response.sendRedirect("SvCancionListar?error=" + msg);
        }
    }
}