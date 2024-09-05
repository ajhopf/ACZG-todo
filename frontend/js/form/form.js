const inputs = [
  { title: 'Título', id: 'titulo', type: 'text'},
  { title: 'Descrição', id:'descricao', type: 'text'},
  { title: 'Data de Término', id: 'data', type: 'date'},
  { title: 'Categoria', id: 'categoria', type: 'text'},
  {
    title: 'Prioridade',
    id: 'prioridade',
    type: 'select',
    options: [
      {value: '1', title: "Muito Baixa"},
      {value: '2', title: "Baixa"},
      {value: '3', title: "Média"},
      {value: '4', title: "Alta"},
      {value: '5', title: "Muito Alta"}
    ]
  },
  {
    title: 'Status',
    id: 'status',
    type: 'select',
    options: [
      {value: 'TODO', title: "TODO"},
      {value: 'DOING', title: "DOING"},
      {value: 'DONE', title: "DONE"},
    ]
  }
];


const textInputBuilder = (title, id, type) => {
  return `
    <div class="form-section">
        <label for=${id}>${title}</label>
        <input type=${type} id=${id} name=${id} required>
    </div>
   `
};

const selectInputBuilder = (title, id, options) => {
  return `
    <div class="form-section">
        <label for=${id}>${title}</label>
        <select id=${id} name=${id} required>
            ${options.map(option => `<option value=${option.value}>${option.title}</option>`)}
        </select>
    </div>
  `
}

const setCurrentDay = () => {
  const dateInput = document.getElementById('data');

  if (dateInput) {
    const currentDate = new Date().toISOString().substring(0, 10);
    dateInput.value = currentDate;
  }
}

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

const resetForm = (form) => {
  form.reset();
  form.setAttribute("data-is-editing", "false");
  form.setAttribute("data-todo-id", null)
  document.getElementById("delete-todo").classList.add("hidden");
  document.getElementById("todo-form-title").innerText = "Adicionar Todo";
  document.getElementById("salvar-todo").innerText = "Salvar"
}

const buildForm = () => {
  const inputContainer = document.getElementById("inputs-container");

  inputs.forEach((input, index) => {
    if (input.type !== "select") {
      inputContainer.innerHTML += textInputBuilder(input.title, input.id, input.type);
    } else {
      inputContainer.innerHTML += selectInputBuilder(input.title, input.id, input.options)
    }
  })

  setCurrentDay();
}

export { buildForm, resetForm, populateForm };