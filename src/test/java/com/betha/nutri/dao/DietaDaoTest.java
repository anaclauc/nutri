package com.betha.nutri.dao;

import com.betha.nutri.model.Dieta;
import org.junit.Assert;
import org.junit.Test;

public class DietaDaoTest {

    private DietaDao dao = new DietaDao();

    @Test
    public void dietaComNomeNulo() {
        Dieta dieta = new Dieta();

        try {
            dao.validar(dieta);
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("O nome da dieta nao pode ser nulo", ex.getMessage());
        }
    }

    @Test
    public void dietaComNomePreenchido() {
        Dieta dieta = new Dieta();
        dieta.setNome("Dieta de testes");
        Assert.assertEquals("Dieta de testes", dieta.getNome());
    }

}
