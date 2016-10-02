
var modelList = [
    { id: 1, nome: 'Arroz'},
    { id: 2, nome: 'Batata'},
    { id: 3, nome: 'Macarrão'}
]

var model;
var modelTable = $('#modelTable');
var modal = $("#modal");
var btnSalvar = $('#btnSalvar');
var btnNovo = $('#btnNovo');

function Alimento(id, nome) {
    this.id = id;
    this.nome = nome;
}

function Controller() {
    
    function findById(id) {
        return modelList.find(function(data){
            return data.id == id;
        });
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
        model = new Alimento();
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
    controller.preencherForm(model);
});

controller.carregar();