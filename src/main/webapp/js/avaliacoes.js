var endpoint = 'http://localhost:8080/nutri-1.0-SNAPSHOT/api/avaliacao';
var endpointUsuarios = 'http://localhost:8080/nutri-1.0-SNAPSHOT/api/usuario';
var endpointDietas = 'http://localhost:8080/nutri-1.0-SNAPSHOT/api/dieta';

var model;
var modelTable = $('#modelTable');
var modal = $("#modal");
var btnSalvar = $('#btnSalvar');
var btnNovo = $('#btnNovo');

function Avaliacao(id, id_usuario, id_dieta, usuario, dieta, data, peso, imc, taxa_basal) {
    this.id = id;
    this.id_usuario = id_usuario;
    this.id_dieta = id_dieta;
    this.usuario = usuario;
    this.dieta = dieta;
    this.data = data;
    this.peso = peso;
    this.imc = imc;
    this.taxaBasal = taxa_basal;
}

function Controller() {

    function renderRow(data) {
        
        return "<tr id='model-" + data.id + "'>" +
                "<td class='col-codigo'>" + data.id + "</td>" +
                "<td>" + data.usuario.nome + "</td>" +
                "<td text-align='center'>" + data.peso + "</td>" +
                "<td text-align='center'>" + data.data + "</td>" +
                "<td text-align='center'>" + data.dieta.nome + "</td>" +
                "<td text-align='center'>" + data.imc + "</td>" +
                "<td text-align='center'>" + data.taxa_basal + "</td>" +
                "<td class='col-actions'>" +
                "<a href='#' model-id='" + data.id + "' onClick='controller.editar(this)' class='btn btn-default btn-xs' role='button'>" +
                "<span class='glyphicon glyphicon-pencil'></span>" +
                "</a>" + 
                "<a href='#' model-id='" + data.id + "' onClick='controller.excluir(this)' class='btn btn-default btn-xs' role='button'>" +
                "<span class='glyphicon glyphicon-trash'></span>" +
                "</a>" +
                "</td></tr>";
    }

    function appendRow(data) {
        modelTable.append(renderRow(data));
    }

    function replaceRow(data) {
        $('#model-' + data.id).replaceWith(renderRow(data));
    }

    function carregar() {
        $.get(endpoint, function(data) {
            data.forEach(function(alimento){
                appendRow(alimento);
            });
        });
    }

    function novo() {
        model = new Avaliacao();
        modal.modal('show');
    }

    function editar(element) {
        id = $(element).attr('model-id');
        
        $.get(endpoint + '?id=' + id, function(data) {
            model = data;
            modal.modal('show');
        });
    }

    function excluir(element) {
        if (confirm('Tem certeza que deseja excluir o registro?')) {
            id = $(element).attr('model-id');
            
            $.ajax({
                url: endpoint + '?id=' + id,
                method: 'DELETE'
            }).done(function(data) {
                $('#model-' + id).remove();
            });
        }
    }

    function preencherForm(model) {
        $('input[name=id]').val(model.id);
        
        $.get(endpointUsuarios, function(data) {
            data.forEach(function(usuario){
                if(model.id_usuario === usuario.id) {
                    $("#input-usuario").append('<option value="' + usuario.id + '" selected="true">' + usuario.nome + '</option>');
                } else {
                    $("#input-usuario").append('<option value="' + usuario.id + '">' + usuario.nome + '</option>');
                }
                
            });
        });
        
        $.get(endpointDietas, function(data) {
            data.forEach(function(dieta){
                if(model.id_dieta === dieta.id) {
                    $("#input-dieta").append('<option value="' + dieta.id + '" selected="true">' + dieta.nome + '</option>');
                } else {
                    $("#input-dieta").append('<option value="' + dieta.id + '">' + dieta.nome + '</option>');
                }
            });
        });
        
        $('input[name=peso]').val(model.peso);
        $('input[name=data]').val(model.data);
    }

    function salvar() {
        data = $('#form').serializeObject();

        $.ajax({
            url: endpoint,
            method: 'POST',
            data: data,
            error: function(response) {
                response = $.parseJSON(response.responseText);
                $("#error-container").html(response.mensagem).show();
            },
            success: function(response) {
                response = $.parseJSON(response);
                
                if(data.id) {
                    replaceRow(response);
                } else {
                    appendRow(response);
                }

                modal.modal('hide');
            }
        });
    }

    return {
        carregar: carregar,
        novo: novo,
        salvar: salvar,
        editar: editar,
        excluir: excluir,
        preencherForm: preencherForm
    }
}

var controller = new Controller();

btnSalvar.click(function(){
    controller.salvar();
});

btnNovo.click(function(){
    controller.novo();
});

modal.on('show.bs.modal', function(e){
    $('#error-container').hide();
    $('#input-usuario').empty();
    $('#input-dieta').empty();
    $('#input-usuario').focus();
    controller.preencherForm(model);
});

controller.carregar();