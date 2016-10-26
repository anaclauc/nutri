package com.betha.nutri.model;

import org.junit.Assert;
import org.junit.Test;

public class UsuarioTest {

    public UsuarioTest() {
    }

    @Test
    public void validarJson() {
        // Cenário
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        usuario.setNome("Ana Claudia");
        
        // Resultado que é esperado
        String resultadoEsperado = "{\"id\":\"1\", \"nome\":\"Ana Claudia\", \"email\":\"null\", \"idade\":\"null\", \"altura\":\"null\", \"peso\":\"null\", \"sexo\":\"null\" }";
        
        // Comparação
        Assert.assertEquals(resultadoEsperado, usuario.toString());
    }
}
