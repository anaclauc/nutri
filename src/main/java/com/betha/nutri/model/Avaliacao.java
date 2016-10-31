package com.betha.nutri.model;

import com.betha.nutri.dao.DietaDao;
import com.betha.nutri.dao.UsuarioDao;
import com.betha.nutri.utils.Utils;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Avaliacao implements Parseable {

    private Date data = new java.util.Date();
    private Long id;
    private Long id_usuario;
    private Long id_dieta;
    private Double peso_atual;
    private Double taxa_atividade;
    private Double imc;
    private Double taxa_basal;

    @Override
    public void parse(Map<String, String> values) {
        this.id = Utils.parseLong(values.get("id"));
        this.id_usuario = Utils.parseLong(values.get("id_usuario"));
        this.id_dieta = Utils.parseLong(values.get("id_dieta"));
        this.peso_atual = Utils.parseDouble(values.get("peso"));
        this.taxa_atividade = Utils.parseDouble(values.get("taxa_atividade"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getId_dieta() {
        return id_dieta;
    }

    public void setId_dieta(Long id_dieta) {
        this.id_dieta = id_dieta;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public Double getPeso_atual() {
        return peso_atual;
    }

    public void setPeso_atual(Double peso_atual) {
        this.peso_atual = peso_atual;
    }

    public Double getTaxa_atividade() {
        return taxa_atividade;
    }

    public void setTaxa_atividade(Double taxa_atividade) {
        this.taxa_atividade = taxa_atividade;
    }
    
    public Double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public Double getTaxa_basal() {
        return taxa_basal;
    }

    public void setTaxa_basal(Double taxa_basal) {
        this.taxa_basal = taxa_basal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.data);
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.id_usuario);
        hash = 43 * hash + Objects.hashCode(this.id_dieta);
        hash = 43 * hash + Objects.hashCode(this.peso_atual);
        hash = 43 * hash + Objects.hashCode(this.taxa_atividade);
        hash = 43 * hash + Objects.hashCode(this.imc);
        hash = 43 * hash + Objects.hashCode(this.taxa_basal);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Avaliacao other = (Avaliacao) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.id_usuario, other.id_usuario)) {
            return false;
        }
        if (!Objects.equals(this.id_dieta, other.id_dieta)) {
            return false;
        }
        if (!Objects.equals(this.peso_atual, other.peso_atual)) {
            return false;
        }
        if (!Objects.equals(this.taxa_atividade, other.taxa_atividade)) {
            return false;
        }
        if (!Objects.equals(this.imc, other.imc)) {
            return false;
        }
        if (!Objects.equals(this.taxa_basal, other.taxa_basal)) {
            return false;
        }
        return true;
    }

    public void calcularImc() throws SQLException {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.buscar(id_usuario);

        if (usuario != null) {
            imc = peso_atual / (usuario.getAltura() * 2);
        }
    }

    public void calcularTaxaBasal() throws SQLException {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.buscar(id_usuario);

        if (usuario != null) {
            if ("M".equalsIgnoreCase(usuario.getSexo())) {
                taxa_basal = taxa_atividade * (66 + (13.7 * peso_atual) + (5 * usuario.getAltura()) - (6.8 * usuario.getIdade()));
            } else {
                taxa_basal = taxa_atividade * (655 + (9.6 * peso_atual) + (1.8 * usuario.getAltura()) - (4.7 * usuario.getIdade()));
            }
        }

    }

    @Override
    public String toString() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Dieta dieta = new Dieta();
            
            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuario = usuarioDao.buscar(id_usuario);

            if (id_dieta != 0) {
                DietaDao dietaDao = new DietaDao();
                dieta = dietaDao.buscar(id_dieta);
            }

            return String.format("{\"id\":\"%s\", \"id_usuario\":\"%s\", \"id_dieta\":\"%s\", \"usuario\": %s, \"dieta\": %s, \"data\":\"%s\", \"peso\":\"%s\", \"taxa_atividade\":\"%s\",  \"imc\":\"%.2f\", \"taxa_basal\":\"%.2f\" }", id, id_usuario, id_dieta, usuario.toString(), dieta.toString(), dateFormat.format(data), peso_atual, taxa_atividade, imc, taxa_basal);
        } catch (Exception ex) {
            Logger.getLogger(Avaliacao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "{}";
    }
}
