const API = 'http://localhost:8080/api';

// const btnPrueba = document.getElementById('btnPrueba');
const tablaCursos = document.getElementById('tablaCursos');
const tablaDocentes = document.getElementById('tablaDocentes');
const formCurso = document.getElementById('formCurso');
const formDocente = document.getElementById('formDocente');
const selectDocente = document.getElementById('cursoDocente');

let cursos = [];
let docentes = [];

const modalEliminar = new bootstrap.Modal(document.getElementById('modalEliminar'));
let eliminarUrl = '';

// btnPrueba.addEventListener('click', async () => {
//   try {
//     const res = await fetch(`${API}/prueba`);
//     const data = await res.json();
//     alert(`${data.mensaje}\nDocentes: ${data.docentes}\nCursos: ${data.cursos}`);
//   } catch (error) {
//     alert('No hay conexion con el backend');
//   }
// });

// Datos de prueba que use antes de conectar con la API
// const cursosMock = [
//   { nombre: 'Logica de programacion', descripcion: 'Algoritmos y diagramas de flujo', duracionSemanas: 6, precio: 450000, fechaInicio: '2026-10-05T18:00:00', docenteNombre: 'Andres Morales' },
//   { nombre: 'Java basico', descripcion: 'Sintaxis, clases y objetos', duracionSemanas: 10, precio: 800000, fechaInicio: '2026-10-19T08:00:00', docenteNombre: 'Andres Morales' },
//   { nombre: 'HTML y CSS', descripcion: 'Maquetacion de paginas web', duracionSemanas: 4, precio: 350000, fechaInicio: '2026-11-02T14:00:00', docenteNombre: 'Paula Restrepo' }
// ];
//
// const docentesMock = [
//   { nombre: 'Andres Morales', documento: '1020304050', correo: 'andres.morales@cesde.edu.co' },
//   { nombre: 'Paula Restrepo', documento: '43987654', correo: 'paula.restrepo@cesde.edu.co' }
// ];

async function cargarCursos(filtros = '') {
  const res = await fetch(`${API}/cursos${filtros}`);
  cursos = await res.json();
  console.log('cursos', cursos);

  tablaCursos.innerHTML = '';
  cursos.forEach(curso => {
    tablaCursos.innerHTML += `
      <tr>
        <td>${curso.nombre}</td>
        <td class="d-none d-lg-table-cell">${curso.descripcion || ''}</td>
        <td>${curso.duracionSemanas}</td>
        <td>$ ${Number(curso.precio).toLocaleString('es-CO')}</td>
        <td>${new Date(curso.fechaInicio).toLocaleString('es-CO')}</td>
        <td>${curso.docenteNombre}</td>
        <td class="text-nowrap">
          <button class="btn btn-warning btn-sm" onclick="editarCurso(${curso.id})">Editar</button>
          <button class="btn btn-danger btn-sm" onclick="eliminarCurso(${curso.id})">Eliminar</button>
        </td>
      </tr>`;
  });
}

async function cargarDocentes(filtros = '') {
  const res = await fetch(`${API}/docentes${filtros}`);
  docentes = await res.json();
  console.log('docentes', docentes);

  tablaDocentes.innerHTML = '';
  // El select de cursos siempre debe tener todos los docentes
  if (!filtros) {
    selectDocente.innerHTML = '<option value="">Seleccione el docente</option>';
  }
  docentes.forEach(docente => {
    tablaDocentes.innerHTML += `
      <tr>
        <td>${docente.nombre}</td>
        <td>${docente.documento}</td>
        <td>${docente.correo}</td>
        <td class="text-nowrap">
          <button class="btn btn-warning btn-sm" onclick="editarDocente(${docente.id})">Editar</button>
          <button class="btn btn-danger btn-sm" onclick="eliminarDocente(${docente.id})">Eliminar</button>
        </td>
      </tr>`;
    if (!filtros) {
      selectDocente.innerHTML += `<option value="${docente.id}">${docente.nombre}</option>`;
    }
  });
}

// Pasa los datos de la fila al formulario para editarlos
function editarDocente(id) {
  const docente = docentes.find(d => d.id === id);
  document.getElementById('docenteId').value = docente.id;
  document.getElementById('docenteNombre').value = docente.nombre;
  document.getElementById('docenteDocumento').value = docente.documento;
  document.getElementById('docenteCorreo').value = docente.correo;
  document.getElementById('tituloDocente').textContent = 'Editar docente';
}

function editarCurso(id) {
  const curso = cursos.find(c => c.id === id);
  document.getElementById('cursoId').value = curso.id;
  document.getElementById('cursoNombre').value = curso.nombre;
  document.getElementById('cursoDescripcion').value = curso.descripcion;
  document.getElementById('cursoDuracion').value = curso.duracionSemanas;
  document.getElementById('cursoPrecio').value = curso.precio;
  document.getElementById('cursoFecha').value = curso.fechaInicio.slice(0, 16);
  selectDocente.value = curso.docenteId;
  document.getElementById('tituloCurso').textContent = 'Editar curso';
}

// Abre el modal y guarda que se va a eliminar
function eliminarCurso(id) {
  eliminarUrl = `${API}/cursos/${id}`;
  document.getElementById('textoEliminar').textContent = '¿Seguro de eliminar el curso?';
  modalEliminar.show();
}

function eliminarDocente(id) {
  eliminarUrl = `${API}/docentes/${id}`;
  document.getElementById('textoEliminar').textContent = '¿Seguro de eliminar el docente?';
  modalEliminar.show();
}

document.getElementById('btnConfirmarEliminar').addEventListener('click', async () => {
  const res = await fetch(eliminarUrl, { method: 'DELETE' });
  modalEliminar.hide();

  if (!res.ok) {
    const error = await res.json();
    alert(error.message);
    return;
  }

  cargarCursos();
  cargarDocentes();
});

formDocente.addEventListener('submit', async (e) => {
  e.preventDefault();

  const docente = {
    nombre: document.getElementById('docenteNombre').value,
    documento: document.getElementById('docenteDocumento').value,
    correo: document.getElementById('docenteCorreo').value
  };

  // Si hay id es edicion, si no es uno nuevo
  const id = document.getElementById('docenteId').value;
  const url = id ? `${API}/docentes/${id}` : `${API}/docentes`;

  const res = await fetch(url, {
    method: id ? 'PUT' : 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(docente)
  });

  if (!res.ok) {
    const error = await res.json();
    alert(error.message);
    return;
  }

  formDocente.reset();
  document.getElementById('docenteId').value = '';
  document.getElementById('tituloDocente').textContent = 'Nuevo docente';
  cargarDocentes();
  cargarCursos();
});

formCurso.addEventListener('submit', async (e) => {
  e.preventDefault();

  const curso = {
    nombre: document.getElementById('cursoNombre').value,
    descripcion: document.getElementById('cursoDescripcion').value,
    duracionSemanas: document.getElementById('cursoDuracion').value,
    precio: document.getElementById('cursoPrecio').value,
    fechaInicio: document.getElementById('cursoFecha').value,
    docenteId: selectDocente.value
  };

  const id = document.getElementById('cursoId').value;
  const url = id ? `${API}/cursos/${id}` : `${API}/cursos`;

  const res = await fetch(url, {
    method: id ? 'PUT' : 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(curso)
  });

  if (!res.ok) {
    const error = await res.json();
    alert(error.message);
    return;
  }

  formCurso.reset();
  document.getElementById('cursoId').value = '';
  document.getElementById('tituloCurso').textContent = 'Nuevo curso';
  cargarCursos();
});

// Filtros
document.getElementById('filtroCursos').addEventListener('submit', (e) => {
  e.preventDefault();
  const nombre = document.getElementById('filtroCursoNombre').value;
  const desde = document.getElementById('filtroDesde').value;
  const hasta = document.getElementById('filtroHasta').value;

  if (nombre) {
    cargarCursos(`?nombre=${nombre}`);
  } else if (desde && hasta) {
    cargarCursos(`?desde=${desde}&hasta=${hasta}`);
  } else {
    cargarCursos();
  }
});

document.getElementById('limpiarCursos').addEventListener('click', () => {
  document.getElementById('filtroCursos').reset();
  cargarCursos();
});

document.getElementById('filtroDocentes').addEventListener('submit', (e) => {
  e.preventDefault();
  const nombre = document.getElementById('filtroDocenteNombre').value;
  cargarDocentes(nombre ? `?nombre=${nombre}` : '');
});

document.getElementById('limpiarDocentes').addEventListener('click', () => {
  document.getElementById('filtroDocentes').reset();
  cargarDocentes();
});

cargarCursos();
cargarDocentes();
