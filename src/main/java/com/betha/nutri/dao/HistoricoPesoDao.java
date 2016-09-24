package com.betha.nutri.dao;

import com.betha.nutri.model.HistoricoPeso;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoricoPesoDao {

    public HistoricoPeso inserir(HistoricoPeso historicopeso) throws Exception {
        try {
            PreparedStatement paramStm = Conexao.get().getParamStm("INSERT INTO public.historico_pesos(id_usuario, data, peso, id)VALUES (?, ?, ?, ?)");
            paramStm.setLong(1, historicopeso.getIdUsuario());
            paramStm.setDate(2, historicopeso.getData());
            paramStm.setDouble(3, historicopeso.getPeso());
            paramStm.setLong(4, historicopeso.getId());
            paramStm.execute();

            // Recuperar o usuario com o ID
            return historicopeso;
        } catch (SQLException ex) {
            throw new Exception("Falha ao inserir o registro", ex);
        }

    }

    public HistoricoPeso atualizar(HistoricoPeso historicopeso) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("UPDATE public.historico_pesos SET id_usuario=?, data=?, peso=?, id=? WHERE id=?");
            stm.setLong(1, historicopeso.getIdUsuario());
            stm.setDate(2, historicopeso.getData());
            stm.setDouble(3, historicopeso.getPeso());
            stm.setLong(4, historicopeso.getId());
            stm.execute();

            return historicopeso;

        } catch (SQLException ex) {
            throw new Exception("Falha ao alterar o registro", ex);
        }

    }
        
    public void excluir(HistoricoPeso historicopeso) throws Exception {
        excluir(historicopeso.getId());
    }

    public void excluir(Long id) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("DELETE FROM public.historico_pesos WHERE id=?");
            stm.setLong(1, id);
            stm.execute();
        } catch (SQLException ex) {
            throw new Exception("Falha ao excluir o registro", ex);
        }
    }

}
