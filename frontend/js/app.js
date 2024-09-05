import { buildForm, resetForm } from "./form/form.js";
import { addEventListenerToTodoList, renderTodosList, submitTodo } from "./todos/todos.js";

const initApp = () => {
  buildForm();

  const newTodoForm = document.getElementById("todo-form");
  newTodoForm.addEventListener("submit", submitTodo);

  const cancelForm = document.getElementById("cancelar-todo-form");
  cancelForm.addEventListener("click", resetForm.bind(null, newTodoForm))

  renderTodosList();

  addEventListenerToTodoList();
}

initApp();
