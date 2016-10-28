
package com.betha.nutri.dao;

import com.betha.nutri.model.Alimento;
import org.junit.Assert;
import org.junit.Test;


public class AlimentoDaoTest {
    
    private AlimentoDao dao  = new AlimentoDao();
            
    @Test
    public void AlimentoComDescricaoNula(){
        Alimento alimento  = new Alimento();
        
         try {
            dao.validar(alimento);
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("A descricao do alimento nao pode ser nula", ex.getMessage());
        }
        
    }
}
    
    
    

