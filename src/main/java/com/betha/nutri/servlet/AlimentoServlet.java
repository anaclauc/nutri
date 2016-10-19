package com.betha.nutri.servlet;

import com.betha.nutri.dao.AlimentoDao;
import com.betha.nutri.model.Alimento;
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

@WebServlet("/api/alimento")
public class AlimentoServlet extends HttpServlet {

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
            Logger.getLogger(AlimentoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            
            resp.getWriter().write(alimento.toString());
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
                alimentoDao.excluir(Utils.parseLong(req.getParameter("id")));
            } catch (Exception ex) {
                Logger.getLogger(AlimentoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
