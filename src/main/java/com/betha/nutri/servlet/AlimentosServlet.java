package com.betha.nutri.servlet;

import com.betha.nutri.dao.AlimentoDao;
import com.betha.nutri.model.Alimento;
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
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            
            final List<Alimento> alimentos = alimentoDao.listarTodos();
            
            if(alimentos != null && !alimentos.isEmpty()){
                StringBuilder response = new StringBuilder();
                response.append("[");
            
                for(Alimento alimento : alimentos){
                    response.append(alimento.toString());
                    response.append(",");
                };
                
                response.replace(response.lastIndexOf(","), response.length(), "");
                response.append("]");
                resp.getWriter().write(response.toString());
            } else {
                resp.getWriter().write("[]");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(AlimentosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
