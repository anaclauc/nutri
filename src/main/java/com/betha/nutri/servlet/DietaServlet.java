package com.betha.nutri.servlet;

import com.betha.nutri.dao.DietaDao;
import com.betha.nutri.model.Dieta;
import com.betha.nutri.model.Erro;
import com.betha.nutri.utils.Utils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/dieta")
public class DietaServlet extends HttpServlet {

    private final DietaDao dietaDao = new DietaDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final String idDieta = req.getParameter("id");
            final String nome = req.getParameter("nome");

            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");

            if (Utils.isNotEmpty(nome)) {
                ResponseBuilder builder = new ResponseBuilder();
                resp.getWriter().write(builder.buildFromList(dietaDao.buscar(nome)));
            } else if (Utils.isNotEmpty(idDieta)) {
                resp.getWriter().write(dietaDao.buscar(Long.parseLong(idDieta)).toString());
            } else {
                ResponseBuilder builder = new ResponseBuilder();
                resp.getWriter().write(builder.buildFromList(dietaDao.listarTodos()));
            }
        } catch (Exception ex) {
            Logger.getLogger(AlimentoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dieta dieta = new Dieta();
        dieta.parse(Utils.parseMap(req));

        try {
            if (Utils.isNotEmpty(req.getParameter("id"))) {
                dietaDao.atualizar(dieta);
            } else {
                dietaDao.inserir(dieta);
            }

            resp.getWriter().write(dieta.toString());
        } catch (IllegalArgumentException ex) {
            resp.setStatus(400);
            resp.getWriter().write(new Erro(ex.getMessage()).toString());
        } catch (SQLException ex) {
            resp.setStatus(500);
            resp.getWriter().write(new Erro("Erro interno ao salvar o registro").toString());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            resp.sendError(406, "Propriedade ID obrigatória");
        } else { // realizar o delete
            try {
                dietaDao.excluir(Utils.parseLong(req.getParameter("id")));
            } catch (Exception ex) {
                Logger.getLogger(AlimentoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
