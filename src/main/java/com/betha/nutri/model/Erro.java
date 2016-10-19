package com.betha.nutri.model;

public class Erro {
    private String mensagem;

    public Erro(String mensagem) {
        this.mensagem = mensagem;
    }

    public Erro() {
    }
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "{ \"mensagem\": \"" + mensagem + "\"}";
    }
    
    
    
}
