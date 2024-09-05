import { buildForm, resetForm } from "./form/form.js";
import { addEventListenerToTodoList, renderTodosList, submitTodo } from "./todos/todos.js";
import { addEventListenerToCategoriaList, updateCategories } from "./todos/filters/categories.js";
import { addEventListenerToPrioridadeList, updatePrioridade } from "./todos/filters/priorities.js";
import { addEventListenerToStatusList, updateStatus } from "./todos/filters/status.js";

const initApp = () => {
  buildForm();

  const newTodoForm = document.getElementById("todo-form");
  newTodoForm.addEventListener("submit", submitTodo);

  const cancelForm = document.getElementById("cancelar-todo-form");
  cancelForm.addEventListener("click", resetForm.bind(null, newTodoForm))

  renderTodosList();
  updateCategories();
  updateStatus();

  addEventListenerToCategoriaList();
  addEventListenerToTodoList();
  addEventListenerToPrioridadeList();
  addEventListenerToStatusList();
  document.getElementById("show-all-btn").addEventListener("click", renderTodosList.bind(null, null, null, null));

  updatePrioridade();
}



initApp();
