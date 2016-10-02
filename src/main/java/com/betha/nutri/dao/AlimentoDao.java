package com.betha.nutri.dao;

import com.betha.nutri.model.Alimento;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlimentoDao {

    public Alimento inserir(Alimento alimento) throws Exception {
        try {
            PreparedStatement paramStm = Conexao.get().getParamStm("INSERT INTO public.alimentos(id, descricao) VALUES (?, ?);");
            paramStm.setString(1, alimento.getDescricao());
            // Recuperar o usuario com o ID

            return alimento;
        } catch (SQLException ex) {
            throw new Exception("Falha ao inserir o registro", ex);
        }
    }

    public Alimento atualizar(Alimento alimento) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("UPDATE public.alimentos SET id=?, descricao=? WHERE id=?;");

            stm.setString(1, alimento.getDescricao());
            stm.execute();

            return alimento;
        } catch (SQLException ex) {
            throw new Exception("Falha ao alterar o registro", ex);
        }

    }

    public void excluir(Alimento alimento) throws Exception {
        excluir(alimento.getId());
    }

    public void excluir(Long id) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("DELETE FROM public.usuarios WHERE id=?;");
            stm.setLong(1, id);
            stm.execute();
        } catch (SQLException ex) {
            throw new Exception("Falha ao excluir o registro", ex);
        }
    }

}
