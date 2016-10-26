package com.betha.nutri.dao;

import com.betha.nutri.model.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class UsuarioDaoTest {

    private UsuarioDao dao = new UsuarioDao();

    @Test
    public void usuarioComNomeNulo() {
        Usuario usuario = new Usuario();

        try {
            dao.validar(usuario);
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("O nome do usuario nao pode ser nulo", ex.getMessage());
        }

    }

    @Test
    public void usuarioComEmailNulo() {
        Usuario usuario = new Usuario();
        usuario.setNome("Ana Claudia");

        try {
            dao.validar(usuario);
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("O email do usuario nao pode ser nulo", ex.getMessage());
        }

    }
    
}
