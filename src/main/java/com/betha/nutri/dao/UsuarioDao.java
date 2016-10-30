package com.betha.nutri.dao;

import com.betha.nutri.model.Usuario;
import com.betha.nutri.utils.Utils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    public void validar(Usuario usuario) {
        if (Utils.isEmpty(usuario.getNome())) {
            throw new IllegalArgumentException("O nome do usuario nao pode ser nulo");
        } else if (Utils.isEmpty(usuario.getEmail())) {
            throw new IllegalArgumentException("O email do usuario nao pode ser nulo");
        } else if (usuario.getIdade() == null || usuario.getIdade() <= 0) {
            throw new IllegalArgumentException("A idade deve ser superior a 0");
        } else if (usuario.getPeso() == null || usuario.getPeso() <= 0) {
            throw new IllegalArgumentException("O peso deve ser superior a 0");
        } else if (usuario.getAltura() == null || usuario.getAltura() <= 0) {
            throw new IllegalArgumentException("A altura deve ser superior a 0");
        } else {
            try {
                if (buscar(usuario) != null) {
                    throw new IllegalArgumentException("O email informado ja esta em uso");
                }

            } catch (SQLException ex) {
                throw new IllegalArgumentException("Nao foi possivel validar o email informado");
            }
        }
    }

    public Usuario inserir(Usuario usuario) throws IllegalArgumentException, SQLException {
        validar(usuario);
        PreparedStatement paramStm = Conexao.get().getParamStm("INSERT INTO public.usuarios(nome, email, sexo, idade, peso, altura) VALUES (?, ?, ?, ?, ?, ?) RETURNING id;");
        paramStm.setString(1, usuario.getNome());
        paramStm.setString(2, usuario.getEmail());
        paramStm.setString(3, usuario.getSexo());
        paramStm.setInt(4, usuario.getIdade());
        paramStm.setDouble(5, usuario.getPeso());
        paramStm.setDouble(6, usuario.getAltura());
        ResultSet rs = paramStm.executeQuery();

        if (rs != null) {
            rs.next();
            long id = rs.getLong("id");
            usuario.setId(id);
        }

        return usuario;
    }

    public Usuario atualizar(Usuario usuario) throws IllegalArgumentException, SQLException {
        validar(usuario);
        PreparedStatement stm = Conexao.get().getParamStm("UPDATE public.usuarios SET nome=?, email=?, sexo=?, idade=?, peso=?, altura=? WHERE id=?;");

        stm.setString(1, usuario.getNome());
        stm.setString(2, usuario.getEmail());
        stm.setString(3, usuario.getSexo());
        stm.setInt(4, usuario.getIdade());
        stm.setDouble(5, usuario.getPeso());
        stm.setDouble(6, usuario.getAltura());
        stm.setLong(7, usuario.getId());
        stm.execute();

        return usuario;
    }

    public void excluir(Usuario usuario) throws Exception {
        excluir(usuario.getId());
    }

    public void excluir(Long id) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("DELETE FROM usuarios WHERE id=?;");
            stm.setLong(1, id);
            stm.execute();
        } catch (SQLException ex) {
            throw new Exception("Falha ao excluir o registro", ex);
        }
    }

    public List<Usuario> listarTodos() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            PreparedStatement stm = Conexao.get().getParamStm("SELECT * FROM usuarios");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                usuarios.add(lerRegistro(rs));
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar o registro", ex);
        }

        return usuarios;
    }

    public Usuario buscar(Long id) throws SQLException {
        if (id != null) {
            PreparedStatement stm = Conexao.get().getParamStm("SELECT * FROM usuarios WHERE id = ?");
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return lerRegistro(rs);
            }
        }

        return null;
    }

    public Usuario buscar(Usuario usuario) throws IllegalArgumentException, SQLException {
        PreparedStatement stm = null;
        
        if (usuario.getId() == null) {
            stm = Conexao.get().getParamStm("SELECT * FROM usuarios WHERE email = ?");
            stm.setString(1, usuario.getEmail());
        } else {
            stm = Conexao.get().getParamStm("SELECT * FROM usuarios WHERE id != ? AND email = ?");
            stm.setLong(1, usuario.getId());
            stm.setString(2, usuario.getEmail());
        }

        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            return lerRegistro(rs);
        }

        return null;
    }

    public List<Usuario> buscar(String nome) throws Exception {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            PreparedStatement stm = Conexao.get().getParamStm("SELECT * FROM usuarios WHERE nome LIKE '%" + nome + "%'");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                usuarios.add(lerRegistro(rs));
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar o registro", ex);
        }

        return usuarios;
    }

    private Usuario lerRegistro(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getLong("id"));
        u.setNome(rs.getString("nome"));
        u.setEmail(rs.getString("email"));
        u.setSexo(rs.getString("sexo"));
        u.setIdade(rs.getInt("idade"));
        u.setAltura(rs.getDouble("altura"));
        u.setPeso(rs.getDouble("peso"));
        return u;
    }
}
