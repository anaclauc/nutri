package com.betha.nutri.model;

import com.betha.nutri.dao.UsuarioDao;
import java.sql.Date;
import java.util.Map;
import java.util.Objects;

public class Avaliacao implements Parseable {

    private Long id;
    private Long id_usuario;
    private Long id_dieta;
    private Date data;
    private double peso_atual;
    private double imc;
    private double taxa_basal;

    @Override
    public void parse(Map<String, String> values) {
        this.id = Long.parseLong(values.get("id"));
        this.id_usuario = Long.parseLong(values.get("id_usuario"));
        this.id_dieta = Long.parseLong(values.get("id_dieta"));
        this.data = Date.valueOf("data");
        this.peso_atual = Double.parseDouble("peso_atual");
        this.taxa_basal = Double.parseDouble("taxa_basal");
        this.imc = Double.parseDouble("imc");
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getPeso_atual() {
        return peso_atual;
    }

    public void setPeso_atual(double peso_atual) {
        this.peso_atual = peso_atual;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public double getTaxa_basal() {
        return taxa_basal;
    }

    public void setTaxa_basal(double taxa_basal) {
        this.taxa_basal = taxa_basal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.id_usuario);
        hash = 41 * hash + Objects.hashCode(this.id_dieta);
        hash = 41 * hash + Objects.hashCode(this.data);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.peso_atual) ^ (Double.doubleToLongBits(this.peso_atual) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.imc) ^ (Double.doubleToLongBits(this.imc) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.taxa_basal) ^ (Double.doubleToLongBits(this.taxa_basal) >>> 32));
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.id_usuario, other.id_usuario)) {
            return false;
        }
        if (!Objects.equals(this.id_dieta, other.id_dieta)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (Double.doubleToLongBits(this.peso_atual) != Double.doubleToLongBits(other.peso_atual)) {
            return false;
        }
        if (Double.doubleToLongBits(this.imc) != Double.doubleToLongBits(other.imc)) {
            return false;
        }
        if (Double.doubleToLongBits(this.taxa_basal) != Double.doubleToLongBits(other.taxa_basal)) {
            return false;
        }
        return true;
    }

    public void calcularImc() throws Exception {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.buscar(id_usuario);

        if (usuario != null) {
            imc = peso_atual / (usuario.getAltura()*2);
        }
    }
    
    public void calcularTaxaBasal() throws Exception {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.buscar(id_usuario);

        if (usuario != null) {
            if("M".equalsIgnoreCase(usuario.getSexo())) {
                taxa_basal = 66 + (13.7 * peso_atual) + (5 * usuario.getAltura()) - (6.8 * usuario.getIdade());
            } else {
                taxa_basal = 655 + (9.6 * peso_atual) + (1.8 * usuario.getAltura()) - (4.7 * usuario.getIdade());
            }
        }
        
    }
}
