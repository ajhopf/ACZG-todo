import {todos} from "../todos.js";

const categorias = [];

const atualizarCategorias = () => {
  todos.forEach(todo => {
    const categoria = todo.categoria;

    if (!categorias.includes(categoria)) {
      categorias.push(categoria);
    }
  })
}

export {categorias, atualizarCategorias};