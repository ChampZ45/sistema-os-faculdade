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

Easy.LimparServico = (function() {
	
	function LimparServico() {
		this.botaoLimpar = $('.js-limpar-servico');
		this.inputDescricao = $('#descricao');
		this.inputNome = $('#nome');
		this.inputPreco = $('#preco');
//		this.checkTipoPessoa = $('.js-tipo-pessoa');
	}
	
	LimparServico.prototype.enable = function() {
		this.botaoLimpar.on('click', onLimparFormularioServico.bind(this));
	};
	
	function onLimparFormularioServico() {
		this.inputDescricao.val('');
		this.inputNome.val('');
		this.inputPreco.val('');
	}
	
	return LimparServico;
}());

Easy.MaskData = (function() {
	
	function MaskData() {
		this.inputData = $('.js-data');
	}
	
	MaskData.prototype.enable = function() {
		this.inputData.mask('00/00/0000');
	}
		
	return MaskData;
}());


Easy.AutoCompleteCliente = (function() {
	
	function AutoCompleteCliente() {
		this.inputCliente = $('.js-autocomplete-ciente');
	};
	
	AutoCompleteCliente.prototype.enable = function() {
		
		this.inputCliente.on('change',onlimparCliente.bind(this));
		
		var options = {
				url: function(nome) {
					return '/easyservice/cliente?nome=' + nome;
				},
				getValue: 'nome',
				minCharNumber: 3,
				ajaxSettings:{
					contentType: 'application/json'
				},
				list: {
					onChooseEvent: onItemSelecionado.bind(this)
				}

			};
		
		this.inputCliente.easyAutocomplete(options);
		
	};
	
	function onItemSelecionado() {

		var inputClienteId = $('#idCliente');
		inputClienteId.val(this.inputCliente.getSelectedItemData().id);
	
	}
	
	function onlimparCliente() {
		
		if(!this.inputCliente.val()){
			var inputClienteId = $('#idCliente');
			inputClienteId.val('');
		}
	}
	
	return AutoCompleteCliente;
	
}());

Easy.AutoCompleteUsuario = (function() {
	
	function AutoCompleteUsuario() {
		this.inputResponsavel = $('.js-autocomplete-usuario');
	};
	
	AutoCompleteUsuario.prototype.enable = function() {
		
		this.inputResponsavel.on('change',onlimparUsuario.bind(this));
		
		var options = {
				url: function(nome) {
					return '/easyservice/usuario?nome=' + nome;
				},
				getValue: 'nome',
				minCharNumber: 3,
				ajaxSettings:{
					contentType: 'application/json'
				},
				list: {
					onChooseEvent: onItemSelecionado.bind(this)
				}

			};
		
		this.inputResponsavel.easyAutocomplete(options);
		
	};
	
	function onItemSelecionado() {

		var inputResponsavelId = $('#idResponsavel');		
		inputResponsavelId.val(this.inputResponsavel.getSelectedItemData().id);
	
	}
	
	function onlimparUsuario() {
		
		if(!this.inputResponsavel.val()){
			var inputResponsavelId = $('#idResponsavel');
			inputResponsavelId.val('');
		}
	}
	
	return AutoCompleteUsuario;
	
}());

Easy.AutoCompleteProduto = (function() {
	
	function AutoCompleteProduto() {
		this.input = $('.js-autocomplete-produto');
	};
	
	AutoCompleteProduto.prototype.enable = function() {
		
		this.input.on('change',onlimpar.bind(this));
		
		var options = {
				url: function(descricao) {
					return '/easyservice/produto?descricao=' + descricao;
				},
				getValue: 'descricao',
//				minCharNumber: 3,
				ajaxSettings:{
					contentType: 'application/json'
				},
				list: {
					onChooseEvent: onItemSelecionado.bind(this)
				}

			};
		
		this.input.easyAutocomplete(options);
		
	};
	
	function onItemSelecionado() {

		var inputId = $('#idProduto');		
		inputId.val(this.input.getSelectedItemData().id);
	
	}
	
	
	function onlimpar() {
		
		if(!this.input.val()){
			var inputId = $('#idProduto');
			inputId.val('');
		}
	}
	
	return AutoCompleteProduto;
	
}());

Easy.AutoCompleteServico = (function() {
	
	function AutoCompleteServico() {
		this.input = $('.js-autocomplete-servico');
	};
	
	AutoCompleteServico.prototype.enable = function() {
				
		this.input.on('change',onlimpar.bind(this));
		
		var options = {
				url: function(nome) {
					return '/easyservice/servico?nome=' + nome;
				},
				getValue: 'nome',
//				minCharNumber: 3,
				ajaxSettings:{
					contentType: 'application/json'
				},
				list: {
					onChooseEvent: onItemSelecionado.bind(this)
				}

			};
		
		this.input.easyAutocomplete(options);
		
	};
	
	function onItemSelecionado() {

		var inputId = $('#idServico');		
		inputId.val(this.input.getSelectedItemData().id);
	
	}
	
	function onlimpar() {
		
		if(!this.input.val()){
			var inputId = $('#idServico');
			inputId.val('');
		}
	}
	
	return AutoCompleteServico;
	
}());

Easy.AdicionarProdutoOrdemServico = (function() {

	
	function AdicionarProdutoOrdemServico() {
		this.inputAdicionar = $('.js-produto-ordem');
		this.tabela = $('.js-tabela-produto');
		this.divValorTotal = $('.js-total');
		this.inputExcluir = $('.js-botao-excluir-produto');
		
	};
	
	AdicionarProdutoOrdemServico.prototype.enable = function() {
		this.inputAdicionar.on('click',onClickAdicionar.bind(this));
	};
	
	function onClickAdicionar(evento) {
		
		var inputId = $('#idProduto');	

		var resposta = $.ajax({
			data: {
				id : inputId.val()				
			},
			async: false,
		    'type': 'POST',
		    'url': '/easyservice/ordemServico/adicionarProduto',
		     });
		
			resposta.done(onItemAtualizadoNoServidor.bind(this));

			var respostaTotal = $.ajax({			
				'type': 'POST',
				'url': '/easyservice/ordemServico/valorTotal',
			});
			
			respostaTotal.done(atualizarValorTotal.bind(this));
			
	};
	
	function onItemAtualizadoNoServidor(html) {
		this.tabela.html(html);	
		
		onCallExcluir.call(this);
	}
	
	function onCallExcluir() {
		$('.js-botao-excluir-produto').on('click',onClickExcluir.bind(this));
	}
	

	function onClickExcluir(evento) {
		
		var botaoAtivouEvento = $(evento.target);
		
		var resposta = $.ajax({
			data: {
				id:  botaoAtivouEvento.data('id')
	
			},
			async: false,
		    'type': 'POST',
		    'url': 'excluirProduto',
		     });
		
			resposta.done(onItemAtualizadoNoServidor.bind(this));
			
			
			var respostaTotal = $.ajax({			
				'type': 'POST',
				'url': '/easyservice/ordemServico/valorTotal',
			});
			
			respostaTotal.done(atualizarValorTotal.bind(this));
						
	};
	
	function atualizarValorTotal(html) {
		this.divValorTotal.html(html);
	}
	
	return AdicionarProdutoOrdemServico;
	
}());


Easy.AdicionarServicoOrdemServico = (function() {

	
	function AdicionarServicoOrdemServico() {
		this.inputAdicionar = $('.js-servico-ordem');
		this.tabela = $('.js-tabela-servico');
		this.divValorTotal = $('.js-total');
	};
	
	AdicionarServicoOrdemServico.prototype.enable = function() {
		this.inputAdicionar.on('click',onClickAdicionar.bind(this));
	};
	
	function onClickAdicionar(evento) {
		
		var inputId = $('#idServico');	

		var resposta = $.ajax({
			data: {
				id : inputId.val()				
			},
			async: false,
		    'type': 'POST',
		    'url': '/easyservice/ordemServico/adicionarServico',
		     });
		
			resposta.done(onItemAtualizadoNoServidor.bind(this));
			
			var respostaTotal = $.ajax({			
				'type': 'POST',
				'url': '/easyservice/ordemServico/valorTotal',
			});
			
			respostaTotal.done(atualizarValorTotal.bind(this));
			
	};
		
	
	function onItemAtualizadoNoServidor(html) {
		this.tabela.html(html);	
		
		onCallExcluir.call(this);
	}
	
	function onCallExcluir() {
		$('.js-botao-excluir-servico').on('click',onClickExcluir.bind(this));
	}
	
	function onClickExcluir(evento) {
		
		var botaoAtivouEvento = $(evento.target);
		
		var resposta = $.ajax({
			data: {
				id:  botaoAtivouEvento.data('id')
	
			},
			async: false,
		    'type': 'POST',
		    'url': 'excluirServico',
		     });
		
			resposta.done(onItemAtualizadoNoServidor.bind(this));
			
			
			var respostaTotal = $.ajax({			
				'type': 'POST',
				'url': '/easyservice/ordemServico/valorTotal',
			});
			
			respostaTotal.done(atualizarValorTotal.bind(this));
						
	};
	
	function atualizarValorTotal(html) {
		this.divValorTotal.html(html);
	}
	
	return AdicionarServicoOrdemServico;
	
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
	
	var limparFormularioServico = new Easy.LimparServico();
	limparFormularioServico.enable();
	
	var mascaraData = new Easy.MaskData();
	mascaraData.enable();
	
	var autoCompleteCliente = new Easy.AutoCompleteCliente();
	autoCompleteCliente.enable();
	
	var autoCompleteUsuario = new Easy.AutoCompleteUsuario();
	autoCompleteUsuario.enable();
	
	var autoCompleteProduto = new Easy.AutoCompleteProduto();
	autoCompleteProduto.enable();
	
	var autoCompleteServico = new Easy.AutoCompleteServico();
	autoCompleteServico.enable();
	
	var adicionarProdutoOrdem = new Easy.AdicionarProdutoOrdemServico();
	adicionarProdutoOrdem.enable();
	
	var adicionarServicoOrdem = new Easy.AdicionarServicoOrdemServico();
	adicionarServicoOrdem.enable();
	
	
    
});