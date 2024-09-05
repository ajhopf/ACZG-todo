import { buildForm, resetForm } from "./form/form.js";
import { addEventListenerToTodoList, deleteTodo, renderTodosList, submitTodo } from "./todos/todos.js";
import { addEventListenerToCategoriaList, updateCategories } from "./todos/filters/categories.js";
import { addEventListenerToPrioridadeList, updatePrioridade } from "./todos/filters/priorities.js";
import { addEventListenerToStatusList, updateStatus } from "./todos/filters/status.js";

const initApp = () => {
  buildForm();

  const newTodoForm = document.getElementById("todo-form");
  newTodoForm.addEventListener("submit", submitTodo);
  const cancelForm = document.getElementById("cancelar-todo-form");
  cancelForm.addEventListener("click", resetForm.bind(null, newTodoForm))
  const deleteBtn = document.getElementById("delete-todo");
  deleteBtn.addEventListener("click", deleteTodo);

  addEventListenerToCategoriaList();
  addEventListenerToTodoList();
  addEventListenerToPrioridadeList();
  addEventListenerToStatusList();
  document.getElementById("show-all-btn").addEventListener("click", renderTodosList.bind(null, null, null, null));

  renderTodosList();
  updateCategories();
  updatePrioridade();
  updateStatus();
}



initApp();
