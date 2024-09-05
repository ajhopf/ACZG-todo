import { renderTodosList, todos } from "../todos.js";

const prioridade = [
  {value: '1', text: 'Muito baixa'},
  {value: '2', text: 'Baixa'},
  {value: '3', text: 'Média'},
  {value: '4', text: 'Alta'},
  {value: '5', text: 'Muito Alta'},
]

const updatePrioridade = () => {
  const prioridadeNavList = document.getElementById("prioridade-nav-list");

  prioridadeNavList.innerHTML = '';

  prioridade.forEach(prioridade => {
    const todosFiltered = todos.filter(todo => todo.prioridade === prioridade.value.toString())

    prioridadeNavList.innerHTML += `
      <li class="prioridade-item" data-prioridade-number="${prioridade.value}">
        <p>${prioridade.text}</p>
        <p>${todosFiltered.length}</p>
      </li>
    `
  })
}

const addEventListenerToPrioridadeList = () => {
  const prioridadeList = document.getElementById("prioridade-nav-list");

  prioridadeList.addEventListener("click", (event) => {
    const li = event.target.closest('.prioridade-item');

    if (li) {
      const prioridade = li.getAttribute("data-prioridade-number");
      console.log(prioridade);

      renderTodosList(null, prioridade);
    }
  });
}

export {updatePrioridade, addEventListenerToPrioridadeList};