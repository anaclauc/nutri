package com.betha.nutri.servlet;

import com.betha.nutri.dao.UsuarioDao;
import com.betha.nutri.model.Usuario;
import com.betha.nutri.utils.Utils;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/usuario")
public class UsuarioServlet extends HttpServlet {

    private final UsuarioDao usuarioDao = new UsuarioDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final String idUsuario = req.getParameter("id");

            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");

            if (Utils.isNotEmpty(idUsuario)) {
                resp.getWriter().write(usuarioDao.buscar(Long.parseLong(idUsuario)).toString());
            } else {
                ResponseBuilder builder = new ResponseBuilder();
                resp.getWriter().write(builder.buildFromList(usuarioDao.listarTodos()));
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = new Usuario();
        usuario.parse(Utils.parseMap(req));

        try {
            if (Utils.isNotEmpty(req.getParameter("id"))) {
                usuarioDao.atualizar(usuario);
            } else {
                usuarioDao.inserir(usuario);
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            resp.sendError(406, "Propriedade ID obrigatória");
        } else { // realizar o delete
            try {
                usuarioDao.excluir(Utils.parseLong(req.getParameter("id")));
            } catch (Exception ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
