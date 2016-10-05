
var modelList = [
    { id: 1, nome: 'Perda de gordura Matheus'},
    { id: 2, nome: 'Ganho de massa Ana Claudia'}
]

var model;
var modelTable = $('#modelTable');
var modal = $("#modal");
var btnSalvar = $('#btnSalvar');
var btnNovo = $('#btnNovo');
var btnIncluirAlimento = $('#btnIncluirAlimento');
var alimentosTable = $('#alimentosTable')

function Dieta(id, nome, descricao) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
}

function Controller() {
    
    function findById(id) {
        return modelList.find(function(data){
            return data.id == id;
        });
    }

    function renderAlimentoRow(index) {
        return "<tr>" +
                    "<td>" +
                        "<select class='form-control' name='alimento[" + index + "].id'>" +
                            "<option>Selecione...</option>" +
                        "</select>" +
                    "</td>" +
                    "<td>" +
                        "<input type='text' name='alimento[" + index + "].qtde' class='form-control' placeholder='Quantidade' />" +
                    "</td>" +
                    "<td>" +
                        "<select class='form-control' name='alimento[" + index + "].tipo'>" +
                            "<option>Selecione...</option>" + 
                            "<option value='0'>Unidades</option>" +
                            "<option value='1'>Gramas</option>" + 
                        "</select>" +
                    "</td>" +
                "</tr>";
    }

    function renderRow(data) {
        return "<tr id='model-" + data.id + "'>" +
                "<td class='col-codigo'>" + data.id + "</td>" +
                "<td>" + data.nome + "</td>" +
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
        // TODO: Carregar do servidor
        modelList.forEach(function(data){
            appendRow(data);
        });
    }

    function novo() {
        model = new Dieta();
        modal.modal('show');
    }

    function editar(element) {
        id = $(element).attr('model-id');
        // TODO: Deve carregar do servidor
        model = findById(id);
        modal.modal('show');
    }

    function excluir(element) {
        if (confirm('Tem certeza que deseja excluir o registro?')) {
            id = $(element).attr('model-id');
            // TODO: Deve enviar a requisição para o servidor
            $('#model-' + id).remove();
        }
    }

    function preencherForm(model) {
        $('input[name=id]').val(model.id);
        $('input[name=nome]').val(model.nome);
        $('textarea[name=descricao]').val(model.descricao);
    }

    function salvar() {
        data = $('#form').serializeObject();

        if (data.id) {
            // Deve atualizar no servidor
            replaceRow(data);
        } else {
            // Deve inserir no servidor
            modelList.push(data);
            appendRow(data);
        }
  
        modal.modal('hide');
    }

    return {
        carregar: carregar,
        novo: novo,
        salvar: salvar,
        editar: editar,
        excluir: excluir,
        preencherForm: preencherForm,
        renderAlimentoRow: renderAlimentoRow
    }
}

var controller = new Controller();

btnSalvar.click(function(){
    controller.salvar();
});

btnNovo.click(function(){
    controller.novo();
});

btnIncluirAlimento.click(function(){
    index = alimentosTable.children().length;
    alimentosTable.append(controller.renderAlimentoRow(index));
});

modal.on('show.bs.modal', function(e){
    controller.preencherForm(model);
});

controller.carregar();