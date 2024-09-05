import { renderTodosList, todos } from "../todos.js";

const categories = [];

const updateCategories = () => {
  todos.forEach(todo => {
    const category = todo.categoria;

    if (!categories.includes(category)) {
      categories.push(category)
    }
  })

  const categoriesNavList = document.getElementById("categorias-nav-list");

  categoriesNavList.innerHTML = '';

  categories.forEach((categoria) => {
    const numberOfTodos = todos.filter(todo => todo.categoria === categoria);

    if (numberOfTodos.length === 0) {
      return;
    }

    categoriesNavList.innerHTML += `
      <li class="categoria-item">
        <p>${categoria}</p>
        <p>${numberOfTodos.length}</p>
      </li>
    `
  })
}

const addEventListenerToCategoriaList = () => {
  const categoriaList = document.getElementById("categorias-nav-list");

  categoriaList.addEventListener("click", (event) => {
    const li = event.target.closest('.categoria-item');

    if (li) {
      const categoryName = li.firstElementChild.innerHTML
      console.log(categoryName);
      renderTodosList(categoryName, null);
    }
  });
}

export {categories, updateCategories, addEventListenerToCategoriaList};