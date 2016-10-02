package com.betha.nutri.dao;

import com.betha.nutri.model.Dieta;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DietaDao {

    public Dieta inserir(Dieta dieta) throws Exception {
        try {
            PreparedStatement paramStm = Conexao.get().getParamStm("INSERT INTO public.dietas(id, descricao, nome)VALUES (?, ?, ?);");
            paramStm.setString(1, dieta.getNome());
            paramStm.setString(2, dieta.getDescricao());
            paramStm.execute();

            return dieta;
        } catch (SQLException ex) {
            throw new Exception("Falha ao inserir o registro", ex);
        }
    }

    public Dieta atualizar(Dieta dieta) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("UPDATE public.dietas  SET id=?, descricao=?, nome=? WHERE id=?;");

            stm.setString(1, dieta.getNome());
            stm.setString(2, dieta.getDescricao());
            stm.execute();

            return dieta;
        } catch (SQLException ex) {
            throw new Exception("Falha ao alterar o registro", ex);
        }
    }
    
     public void excluir(Dieta dieta) throws Exception {
        excluir(dieta.getId());
    }

    public void excluir(Long id) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("DELETE FROM public.dietas WHERE id=?;");
            stm.setLong(1, id);
            stm.execute();
        } catch (SQLException ex) {
            throw new Exception("Falha ao excluir o registro", ex);
        }
    }
}
