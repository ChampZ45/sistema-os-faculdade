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

$(function () {
   	
	var mascaraTelefone = new Easy.MaskPhoneNumber();
	mascaraTelefone.enable();
	
	
	var mascaraCnpjCpf = new Easy.MaskCnpjCpf();
	mascaraCnpjCpf.enable();
	
    
});