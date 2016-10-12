package com.betha.nutri.servlet;

import com.betha.nutri.dao.AlimentoDao;
import com.betha.nutri.model.Alimento;
import com.betha.nutri.utils.Utils;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/alimentos")
public class AlimentosServlet extends HttpServlet {

    private final AlimentoDao alimentoDao = new AlimentoDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final String idAlimento = req.getParameter("id");

            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");

            if (Utils.isNotEmpty(idAlimento)) {
                resp.getWriter().write(alimentoDao.buscar(Long.parseLong(idAlimento)).toString());
            } else {
                ResponseBuilder builder = new ResponseBuilder();
                resp.getWriter().write(builder.buildFromList(alimentoDao.listarTodos()));
            }
        } catch (Exception ex) {
            Logger.getLogger(AlimentosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Alimento alimento = new Alimento();
        alimento.parse(Utils.parseMap(req));

        try {
            if (Utils.isNotEmpty(req.getParameter("id"))) {
                alimentoDao.atualizar(alimento);
            } else {
                alimentoDao.inserir(alimento);
            }
        } catch (Exception ex) {
            Logger.getLogger(AlimentosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
