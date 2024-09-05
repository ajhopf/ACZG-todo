import { renderTodosList, todos } from "../todos.js";

const status = [
  {value: 'TODO'},
  {value: 'DOING'},
  {value: 'DONE'}
]

const updateStatus = () => {
  const statusNavList = document.getElementById("status-nav-list");

  statusNavList.innerHTML = '';

  status.forEach(status => {
    const todosFiltered = todos.filter(todo => todo.status === status.value);

    statusNavList.innerHTML += `
      <li class="status-item">
        <p>${status.value}</p>
        <p>${todosFiltered.length}</p>
      </li>
    `
  })
}

const addEventListenerToStatusList = () => {
  const statusNavList = document.getElementById("status-nav-list");

  statusNavList.addEventListener("click", (event) => {
    const li = event.target.closest('.status-item');

    if (li) {
      const prioridade = li.firstElementChild.innerHTML;
      renderTodosList(null, null, prioridade);
    }
  });
}

export {updateStatus, addEventListenerToStatusList};