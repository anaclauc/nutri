package com.betha.nutri.dao;

import com.betha.nutri.model.Dieta;
import com.betha.nutri.utils.Utils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DietaDao {

    private void validar(Dieta dieta) {
        if (Utils.isEmpty(dieta.getNome())) {
            throw new IllegalArgumentException("O nome da dieta nao pode ser nula");
        }
    }

    public Dieta inserir(Dieta dieta) throws IllegalArgumentException, SQLException {
        validar(dieta);
        PreparedStatement paramStm = Conexao.get().getParamStm("INSERT INTO public.dietas(nome, descricao) VALUES (?, ?) RETURNING id;");
        paramStm.setString(1, dieta.getNome());
        paramStm.setString(2, dieta.getDescricao());
        ResultSet rs = paramStm.executeQuery();

        if (rs != null) {
            rs.next();
            dieta.setId(rs.getLong("id"));
        }

        return dieta;
    }

    public Dieta atualizar(Dieta dieta) throws IllegalArgumentException, SQLException {
        validar(dieta);
        PreparedStatement stm = Conexao.get().getParamStm("UPDATE public.dietas  SET nome=?, descricao=? WHERE id=?;");

        stm.setString(1, dieta.getNome());
        stm.setString(2, dieta.getDescricao());
        stm.setLong(3, dieta.getId());
        stm.execute();

        return dieta;
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

    public List<Dieta> listarTodos() throws Exception {
        List<Dieta> dieta = new ArrayList<>();

        try {
            PreparedStatement stm = Conexao.get().getParamStm("SELECT * FROM dietas");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                dieta.add(lerRegistro(rs));
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar o registro", ex);
        }

        return dieta;
    }

    public Dieta buscar(Long id) throws Exception {
        if (id == null) {
            return null;
        } else {
            try {
                PreparedStatement stm = Conexao.get().getParamStm("SELECT * FROM dietas WHERE id = ?");
                stm.setLong(1, id);
                ResultSet rs = stm.executeQuery();
                rs.next();
                return lerRegistro(rs);
            } catch (SQLException ex) {
                throw new Exception("Erro ao buscar o registro", ex);
            }
        }
    }

    private Dieta lerRegistro(ResultSet rs) throws SQLException {
        Dieta d = new Dieta();
        d.setId(rs.getLong("id"));
        d.setNome(rs.getString("nome"));
        d.setDescricao(rs.getString("descricao"));
        return d;
    }

}
