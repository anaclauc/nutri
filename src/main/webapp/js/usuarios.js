var modelList = [
    { id: 1, nome: 'Matheus Nunes', email: 'maths.nunes@gmail.com', sexo: 'M', idade: 26, peso: 84.3, altura: 1.83},
    { id: 2, nome: 'Ana Claudia Casagrande Patricio', email: 'anaclaudiacpatricio@gmail.com', sexo: 'F', idade: 26, peso: 62, altura: 1.67}
]

var model;
var modelTable = $('#modelTable');
var modal = $("#modal");
var btnSalvar = $('#btnSalvar');
var btnNovo = $('#btnNovo');

function Usuario(id, nome, email, sexo, idade, peso, altura) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.sexo = sexo;
    this.idade = idade;
    this.peso = peso;
    this.altura = altura;
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
        model = new Usuario();
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
        $('input[name=email]').val(model.email);
        $('input[name=sexo]').val(model.sexo);
        $('input[name=idade]').val(model.idade);
        $('input[name=peso]').val(model.peso);
        $('input[name=altura]').val(model.altura);
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