var endpoint = 'http://localhost:8080/nutri-1.0-SNAPSHOT/api/alimento';
var model;
var modelTable = $('#modelTable');
var modal = $("#modal");
var btnSalvar = $('#btnSalvar');
var btnNovo = $('#btnNovo');

function Alimento(id, descricao) {
    this.id = id;
    this.descricao = descricao;
}

function Controller() {

    function renderRow(data) {
        return "<tr id='model-" + data.id + "'>" +
                "<td class='col-codigo'>" + data.id + "</td>" +
                "<td>" + data.descricao + "</td>" +
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
        model = new Alimento();
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
        $('input[name=descricao]').val(model.descricao);
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
    $('#descricao').focus();
    controller.preencherForm(model);
});

controller.carregar();