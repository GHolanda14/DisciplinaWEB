let ids = 1;
let divLista = document.getElementById("lista");
const formulario = document.getElementById("formulario");

formulario.addEventListener("submit",(event)=>{ /*Evento para evitar atualização da página ao enviar o formulário*/
    event.preventDefault();
    formulario.reset();
});

formulario.addEventListener("keydown",(event)=>{/*Evento para enviar o formulário apertando a tecla Enter*/
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
    /* divEstilo.firstElementChild.style.textDecoration = "line-through"; /* Caso fosse só para riscar a tarefa */
    /*divEstilo.getElementsByTagName("p")[0].style.color = "red";
    /*Outra forma de acessar o elemento p */
}

function desfazerTarefa(id){
    let divEstilo = document.getElementById(id);
    divEstilo.style.backgroundColor = "#4eb9cd"
    divEstilo.style.color = "#e9fafb";
    divEstilo.setAttribute("onclick",`completarTarefa(${id})`);
    /* divEstilo.firstElementChild.style.textDecoration = "none"; */
}

function excluirTarefa(id){
    window.event.stopPropagation();//Evitando que dê erro ao chamar o método da Div
    document.getElementById(id).remove();
}