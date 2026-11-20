package com.app.servlets;

import com.app.logica.ControladoraLogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "SvArtistaEliminar", urlPatterns = {"/SvArtistaEliminar"})
public class SvArtistaEliminar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ControladoraLogica control = new ControladoraLogica();
            control.eliminarArtista(id);
            response.sendRedirect("SvArtistaListar");
        } catch (Exception e) {
            String msg = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
            response.sendRedirect("SvArtistaListar?error=" + msg);
        }
    }
}