import { updateCategories } from "./filters/categories.js";
import { populateForm, resetForm } from "../form/form.js";
import { updatePrioridade } from "./filters/priorities.js";
import { updateStatus } from "./filters/status.js";

const todos = [
  {
    id: 0, titulo: 'Título', descricao: 'Finalizar site', data: '2024-09-05', prioridade: '1', status: 'TODO', categoria: 'ACZG'
  },
  {
    id: 1, titulo: 'Finalizar TODOS', descricao: 'Essa descrição é maneira', data: '2024-09-05', prioridade: '3', status: 'TODO', categoria: 'Pessoal'
  }
];


const selectTodo = (id) => {
  const selectedTodo = todos.find(todo => todo.id === parseInt(id));

  const todoForm = document.getElementById('todo-form');
  document.getElementById("salvar-todo").innerText = "Editar"
  todoForm.setAttribute("data-is-editing", true);
  todoForm.setAttribute("data-todo-id", id);
  document.getElementById("delete-todo").classList.remove("hidden");

  populateForm(selectedTodo);
}

const renderTodoItem = (todo) => {
  return `
      <li class="todo-item" id=${todo.id}>
        <div class="todo-card rounded-border flex flex-col margin">
            <div class="flex justify-between">
                <h2 class="margin">${todo.titulo}</h2>
                <h3 class="margin-right">Categoria: ${todo.categoria}</h3>
            </div>
            <p class="margin">Descrição: ${todo.descricao}</p>
            <div class="todo-card-footer flex justify-around">
                <p>Prioridade: ${todo.prioridade}</p>
                <p>Data: ${new Date(todo.data).toLocaleDateString('pr-br',{ timeZone: 'UTC' })}</p>
                <p>Status: ${todo.status}</p>
            </div>
        </div>                          
      </li>
    `
}

const compareTodoDate = (a, b) => {
  return new Date(a.data) - new Date(b.data);
}

const compareTodoPriority = (a, b) => {
  return b.prioridade - a.prioridade;
}

const renderTodosList = (categoria, prioridade, status) => {
  const todoList = document.getElementById("todo-list");
  console.log(todos);

  todoList.innerHTML = '';

  let todosToRender = todos;

  if (categoria) {
    todosToRender = todosToRender.filter(todo => todo.categoria === categoria);
  } else if (prioridade) {
    todosToRender = todosToRender.filter(todo => todo.prioridade === prioridade);
  } else if (status) {
    todosToRender = todosToRender.filter(todo => todo.status === status);
  }

  todosToRender.sort(compareTodoDate);
  todosToRender.sort(compareTodoPriority);
  todosToRender.forEach(todo => todoList.innerHTML += `${renderTodoItem(todo)}`)
}

const addEventListenerToTodoList = () => {
  const todoList = document.getElementById("todo-list");

  todoList.addEventListener("click", (event) => {
    const li = event.target.closest('.todo-item');

    if (li) {
      const selectedTodoId = li.id;
      selectTodo(selectedTodoId);
    }
  });
}

const deleteTodo = () => {
  const todoForm = document.getElementById("todo-form");

  const todoId = todoForm.getAttribute("data-todo-id");

  const todoIndex = todos.findIndex(todo => todo.id === parseInt(todoId));

  console.log(todoIndex);

  todos.splice(todoIndex, 1);

  resetForm(todoForm);
  resetStatus();
}


const submitTodo = (event) => {
  event.preventDefault();

  const isEditing = event.target.getAttribute("data-is-editing")

  const formData = new FormData(event.target);
  const formProps = Object.fromEntries(formData);

  let id = 0;

  todos.forEach(todo => {
    if (todo.id > id) {
      id = todo.id + 1;
    }
  });

  if (isEditing === "true") {
    const id = parseInt(event.target.getAttribute("data-todo-id"));
    const index = todos.findIndex(todo => todo.id === id)

    todos[index] = {id: id, ...formProps}
  } else {
    todos.push({id: id, ...formProps});
  }

  resetForm(event.target);
  resetStatus();
}

const resetStatus = () => {
  updateCategories();
  updatePrioridade();
  updateStatus();
  renderTodosList();
}

export {renderTodosList, addEventListenerToTodoList, submitTodo, deleteTodo, todos};