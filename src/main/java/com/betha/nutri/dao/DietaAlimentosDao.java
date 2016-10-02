package com.betha.nutri.dao;

import com.betha.nutri.model.DietaAlimentos;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DietaAlimentosDao {

    public DietaAlimentos inserir(DietaAlimentos dietaalimentos) throws Exception {
        try {
            PreparedStatement paramStm = Conexao.get().getParamStm("INSERT INTO public.dieta_alimentos(id, id_dieta, id_alimento, quantidade, tipo, )VALUES (?, ?, ?, ?, ?);");
            paramStm.setLong(1, dietaalimentos.getId_dieta());
            paramStm.setLong(2, dietaalimentos.getId_alimento());
            paramStm.setDouble(3, dietaalimentos.getQuantidade());
            paramStm.setInt(4, dietaalimentos.getTipo());
            paramStm.execute();

            return dietaalimentos;
        } catch (SQLException ex) {
            throw new Exception("Falha ao inserir o registro", ex);
        }
    }

    public DietaAlimentos atualizar(DietaAlimentos dietaalimentos) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("UPDATE public.dieta_alimentos SET id_dieta=?, id_alimento=?, quantidade=?, tipo=?  WHERE id=?;");
            stm.setLong(1, dietaalimentos.getId_dieta());
            stm.setLong(2, dietaalimentos.getId_alimento());
            stm.setDouble(3, dietaalimentos.getQuantidade());
            stm.setInt(4, dietaalimentos.getTipo());
            stm.execute();

            return dietaalimentos;
        } catch (SQLException ex) {
            throw new Exception("Falha ao alterar o registro", ex);
        }

    }

    public void excluir(DietaAlimentos dietaalimentos) throws Exception {
        excluir(dietaalimentos.getId());
    }

    public void excluir(Long id) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("DELETE FROM public.dieta_alimentos WHERE id=?;");
            stm.setLong(1, id);
            stm.execute();
        } catch (SQLException ex) {
            throw new Exception("Falha ao excluir o registro", ex);
        }
    }

}
