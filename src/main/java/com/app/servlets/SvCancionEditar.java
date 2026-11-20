package com.app.servlets;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@WebServlet(name = "SvCancionEditar", urlPatterns = {"/SvCancionEditar"})
public class SvCancionEditar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ControladoraLogica control = new ControladoraLogica();
            Cancion cancion = control.buscarUnaCancion(id);

            if (cancion != null) {
                request.setAttribute("cancion", cancion);
                request.getRequestDispatcher("/WEB-INF/vistas/editarCancion.jsp").forward(request, response);
            } else {
                String msg = URLEncoder.encode("La canción no existe", StandardCharsets.UTF_8);
                response.sendRedirect("SvCancionListar?error=" + msg);
            }
        } catch (Exception e) {
            String msg = URLEncoder.encode("ID inválido", StandardCharsets.UTF_8);
            response.sendRedirect("SvCancionListar?error=" + msg);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String titulo = request.getParameter("titulo");
            float duracion = Float.parseFloat(request.getParameter("duracion"));
            int anio = Integer.parseInt(request.getParameter("anio"));

            Cancion can = new Cancion(titulo, duracion, anio);
            can.setId(id);

            ControladoraLogica control = new ControladoraLogica();
            control.editarCancion(can);

            response.sendRedirect("SvCancionListar");
        } catch (Exception e) {
            String msg = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
            response.sendRedirect("SvCancionListar?error=" + msg);
        }
    }
}