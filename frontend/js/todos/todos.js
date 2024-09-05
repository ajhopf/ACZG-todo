import { atualizarCategorias } from "./categories/categories.js";
import { resetForm } from "../form/form.js";

const todos = [{
  id: 0, titulo: 'Título', descricao: 'Finalizar site', data: '2024-09-05', prioridade: '1', status: 'TODO', categoria: 'ACZG'
}];


const selectTodo = (id) => {
  const selectedTodo = todos.find(todo => todo.id === parseInt(id));

  console.log(id);
  console.log(selectedTodo);

  const populateForm = (todo) => {
    const formTitle = document.getElementById("todo-form-title");

    formTitle.innerText = "Editando Todo"

    const titulo = document.querySelector('#titulo');
    const descricao = document.querySelector('#descricao');
    const data = document.querySelector('#data');
    const prioridade = document.querySelector('#prioridade');
    const status = document.querySelector('#status');
    const categoria = document.querySelector('#categoria');

    titulo.value = todo.titulo;
    descricao.value = todo.descricao;
    data.value = todo.data;
    prioridade.value = todo.prioridade;
    status.value = todo.status;
    categoria.value = todo.categoria;
  };

  const todoForm = document.getElementById('todo-form');
  document.getElementById("salvar-todo").innerText = "Editar"
  todoForm.setAttribute("data-is-editing", true);
  todoForm.setAttribute("data-todo-id", id);

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
                <p>Data: ${todo.data}</p>
                <p>Status: ${todo.status}</p>
            </div>
        </div>                          
      </li>
    `
}

const renderTodosList = () => {
  const todoList = document.getElementById("todo-list");

  todoList.innerHTML = '';

  todos.forEach(todo => todoList.innerHTML += `${renderTodoItem(todo)}`)
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

const submitTodo = (event) => {
  event.preventDefault();

  const isEditing = event.target.getAttribute("data-is-editing")

  const formData = new FormData(event.target);
  const formProps = Object.fromEntries(formData);

  const id = todos.length;

  if (isEditing === "true") {
    const id = parseInt(event.target.getAttribute("data-todo-id"));
    const index = todos.findIndex(todo => todo.id === id)
    console.log(index)
    todos[index] = {id: id, ...formProps}
  } else {
    todos.push({id: id, ...formProps});
  }

  resetForm(event.target);

  atualizarCategorias();
  renderTodosList();
}

export {renderTodosList, addEventListenerToTodoList, submitTodo, todos};