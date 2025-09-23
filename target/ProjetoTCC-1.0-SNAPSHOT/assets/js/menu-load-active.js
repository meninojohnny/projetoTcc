/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

//Inicio
if (window.location.toString().includes("index")) {
    document.getElementById("menuInicio").className += " active";
}


//Prospeccao
if (window.location.toString().includes("prospeccao")) {
    document.getElementById("menuProspeccao").className += " active open";
    document.getElementById("itemProspeccaoCadastrar").className += " active";
}
if (window.location.toString().includes("pesquisarProspeccao")) {
    document.getElementById("menuProspeccao").className += " active open";
    document.getElementById("itemProspeccaoPesquisar").className += " active";
}
//----------------


////Processo
//if (window.location.toString().includes("processo")) {
//    document.getElementById("menuProcesso").className += " active open";
//    document.getElementById("itemProcessoCadastrar").className += " active";
//}
//if (window.location.toString().includes("pesquisarProcesso")) {
//    document.getElementById("menuProcesso").className += " active open";
//    document.getElementById("itemProcessoPesquisar").className += " active";
//}
//----------------



//Cliente
if (window.location.toString().includes("cliente")) {
    document.getElementById("menuCliente").className += " active open";
    document.getElementById("itemClienteCadastrar").className += " active";
}
if (window.location.toString().includes("pesquisarCliente")) {
    document.getElementById("menuCliente").className += " active open";
    document.getElementById("itemClientePesquisar").className += " active";
}
//----------------



//Modelo Documento
if (window.location.toString().includes("modeloDocumento")) {
    document.getElementById("menuModeloDocumento").className += " active open";
    document.getElementById("itemModeloDocumentoCadastrar").className += " active";
}
if (window.location.toString().includes("pesquisarModelo")) {
    document.getElementById("menuModeloDocumento").className += " active open";
    document.getElementById("itemModeloDocumentoPesquisar").className += " active";
}
//----------------



//Modelo Orçamento
if (window.location.toString().includes("modeloOrcamento")) {
    document.getElementById("menuModeloOrcamento").className += " active open";
    document.getElementById("itemModeloOrcamentoCadastrar").className += " active";
}
if (window.location.toString().includes("pesquisarModeloOrcamento")) {
    document.getElementById("menuModeloOrcamento").className += " active open";
    document.getElementById("itemModeloOrcamentoPesquisar").className += " active";
}
//----------------



//Petição
if (window.location.toString().includes("peticao")) {
    document.getElementById("menuPeticao").className += " active open";
    document.getElementById("itemPeticaoCadastrar").className += " active";
}
if (window.location.toString().includes("pesquisarPeticao")) {
    document.getElementById("menuPeticao").className += " active open";
    document.getElementById("itemPeticaoPesquisar").className += " active";
}
//----------------

//Processo
if (window.location.toString().includes("processoColetivo")) {
    document.getElementById("menuCadastroProcesso").className += " active open";
    document.getElementById("itemCadastrarProcessoColetivo").className += " active";
}
if (window.location.toString().includes("processos")) {
    document.getElementById("menuCadastroProcesso").className += " active open";
    document.getElementById("itemCadastrarProcessoIndividual").className += " active";
}
if (window.location.toString().includes("pesquisarProcesso")) {
    document.getElementById("itemPesquisarProcesso").className += " active";
}
if (window.location.toString().includes("consultarProcesso")) {
    document.getElementById("menuProcessoPje").className += " active open";
    document.getElementById("itemConsultarProcessoPje").className += " active";
}
//----------------


if (window.location.toString().includes("nucleo")) {
    document.getElementById("menuNucleo").className += " active open";
    document.getElementById("itemNucleoCadastrar").className += " active";
}

if (window.location.toString().includes("pesquisarNucleo")) {
    document.getElementById("menuNucleo").className += " active open";
    document.getElementById("itemNucleoPesquisar").className += " active";
}

if (window.location.toString().includes("resumo")) {
    document.getElementById("itemDiagnostico").className += " active";
}

if (window.location.toString().includes("analista")) {
    document.getElementById("itemAnalista").className += " active";
}
