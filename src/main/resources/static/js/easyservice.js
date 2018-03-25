var Easy = Easy || {};

Easy.MaskPhoneNumber = (function() {

	function MaskPhoneNumber() {
		this.inputPhoneNumber = $('.js-phone-number');
	}

	MaskPhoneNumber.prototype.enable = function() {

		var maskBehavior = function(val) {
			return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000'
					: '(00) 0000-00009';
		}, spOptions = {
			onKeyPress : function(val, e, field, options) {
				field.mask(maskBehavior.apply({}, arguments), options);
			}
		};

		this.inputPhoneNumber.mask(maskBehavior, spOptions);
	}

	return MaskPhoneNumber;

}());

Easy.MaskCnpjCpf = (function () {
	
	function MaskCnpjCpf() {
		this.labelCpfCnpj = $('.js-cpf');
		this.checkTipoPessoa = $('.js-tipo-pessoa');
	}
	
	MaskCnpjCpf.prototype.enable = function () {
		
		this.checkTipoPessoa.on('change',onCheckBoxFoiModificado.bind(this));
		
		var tipoPessoaSelecionada = this.checkTipoPessoa.filter(':checked')[0];

		if (tipoPessoaSelecionada) {
			inicializarTipoPessoa.call(this, $(tipoPessoaSelecionada));
		}
				
	};
	
	function onCheckBoxFoiModificado(evento) {
		var checkBox = $(evento.currentTarget);
		inicializarTipoPessoa.call(this,checkBox);
		
	}
	
	function inicializarTipoPessoa(tipoPessoaSelecionada) {
		
		var tipoDocumento = tipoPessoaSelecionada.data('documento');
		this.labelCpfCnpj.text(tipoDocumento);
		
		var inputCheckBox  =  $('.js-cpf-cnpj');
		
		if(tipoDocumento == 'CNPJ'){
			inputCheckBox.mask('00.000.000/0000-00');			
		}else{
			inputCheckBox.mask('000.000.000-00');
		}
		
		inputCheckBox.removeAttr('disabled');
	}
	
	return  MaskCnpjCpf;
		
}());

Easy.LimparCliente = (function() {
	
	function LimparCliente() {
		this.botaoLimpar = $('.js-limpar-cliente');
		this.inputPhoneNumber = $('#telefone');
		this.inputNome = $('#nome');
		this.inputCpfCnpj = $('#cnpjCpf');
//		this.checkTipoPessoa = $('.js-tipo-pessoa');
	}
	
	LimparCliente.prototype.enable = function() {
		this.botaoLimpar.on('click', onLimparFormularioCliente.bind(this));
	};
	
	function onLimparFormularioCliente() {
		this.inputPhoneNumber.val('');
		this.inputNome.val('');
		this.inputCpfCnpj.val('');
	}
	
	return LimparCliente;
}());

Easy.LimparProduto = (function() {
	
	function LimparProduto() {
		this.botaoLimpar = $('.js-limpar-produto');
		this.inputDescricao = $('#descricao');
		this.inputEstoque = $('#estoque');
		this.inputPreco = $('#preco');
//		this.checkTipoPessoa = $('.js-tipo-pessoa');
	}
	
	LimparProduto.prototype.enable = function() {
		this.botaoLimpar.on('click', onLimparFormularioProduto.bind(this));
	};
	
	function onLimparFormularioProduto() {
		this.inputDescricao.val('');
		this.inputEstoque.val('');
		this.inputPreco.val('');
	}
	
	return LimparProduto;
}());

$(function () {
   	
	var mascaraTelefone = new Easy.MaskPhoneNumber();
	mascaraTelefone.enable();
	
	
	var mascaraCnpjCpf = new Easy.MaskCnpjCpf();
	mascaraCnpjCpf.enable();
	
	var limparFormularioCliente = new Easy.LimparCliente();
	limparFormularioCliente.enable();
	
	var limparFormularioProduto = new Easy.LimparProduto();
	limparFormularioProduto.enable();
	
    
});