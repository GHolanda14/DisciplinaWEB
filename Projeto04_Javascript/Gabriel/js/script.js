let ids = 1;
let divLista = document.getElementById("lista");
const formulario = document.getElementById("formulario");

formulario.addEventListener("submit",(event)=>{
    event.preventDefault();
    formulario.reset();
});

formulario.addEventListener("keydown",(event)=>{
    if(event.key == 10){//Valor decimal do Enter
        formulario.onsubmit;
    }
});

function adicionarTarefa(){
    let tarefa = document.getElementById("novaTarefa").value;
    divLista.innerHTML += `<div onclick="completarTarefa(${ids});return false" id="${ids}" class="tarefa">
            <p>${tarefa}</p>
            <input type="button" onclick="excluirTarefa(${ids})" value="X">
        </div>`
    ids++;
}

function completarTarefa(id){
    let divEstilo = document.getElementById(id);
    divEstilo.style.backgroundColor = "#51df70"
    divEstilo.style.color = "#098e26";
    divEstilo.setAttribute("onclick",`desfazerTarefa(${id})`);
}

function desfazerTarefa(id){
    let divEstilo = document.getElementById(id);
    divEstilo.style.backgroundColor = "#4eb9cd"
    divEstilo.style.color = "#e9fafb";
    divEstilo.setAttribute("onclick",`completarTarefa(${id})`);
}

function excluirTarefa(id){
    window.event.stopPropagation();//Evitando que dê erro ao chamar o método da Div
    document.getElementById(id).remove();
}