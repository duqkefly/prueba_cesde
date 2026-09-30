const API = 'http://localhost:8080/api';

const btnPrueba = document.getElementById('btnPrueba');
const tablaCursos = document.getElementById('tablaCursos');
const tablaDocentes = document.getElementById('tablaDocentes');

btnPrueba.addEventListener('click', async () => {
  try {
    const res = await fetch(`${API}/prueba`);
    const data = await res.json();
    alert(`${data.mensaje}\nDocentes: ${data.docentes}\nCursos: ${data.cursos}`);
  } catch (error) {
    alert('No hay conexion con el backend');
  }
});

// Datos de prueba mientras conecto las tablas con la API
const cursosMock = [
  { nombre: 'Logica de programacion', descripcion: 'Algoritmos y diagramas de flujo', duracionSemanas: 6, precio: 450000, fechaInicio: '2026-10-05T18:00:00', docenteNombre: 'Andres Morales' },
  { nombre: 'Java basico', descripcion: 'Sintaxis, clases y objetos', duracionSemanas: 10, precio: 800000, fechaInicio: '2026-10-19T08:00:00', docenteNombre: 'Andres Morales' },
  { nombre: 'HTML y CSS', descripcion: 'Maquetacion de paginas web', duracionSemanas: 4, precio: 350000, fechaInicio: '2026-11-02T14:00:00', docenteNombre: 'Paula Restrepo' }
];

const docentesMock = [
  { nombre: 'Andres Morales', documento: '1020304050', correo: 'andres.morales@cesde.edu.co' },
  { nombre: 'Paula Restrepo', documento: '43987654', correo: 'paula.restrepo@cesde.edu.co' }
];

function cargarCursos() {
  const cursos = cursosMock;
  console.log('cursos', cursos);

  tablaCursos.innerHTML = '';
  cursos.forEach(curso => {
    tablaCursos.innerHTML += `
      <tr>
        <td>${curso.nombre}</td>
        <td>${curso.descripcion || ''}</td>
        <td>${curso.duracionSemanas}</td>
        <td>$ ${Number(curso.precio).toLocaleString('es-CO')}</td>
        <td>${new Date(curso.fechaInicio).toLocaleString('es-CO')}</td>
        <td>${curso.docenteNombre}</td>
      </tr>`;
  });
}

function cargarDocentes() {
  const docentes = docentesMock;
  console.log('docentes', docentes);

  tablaDocentes.innerHTML = '';
  docentes.forEach(docente => {
    tablaDocentes.innerHTML += `
      <tr>
        <td>${docente.nombre}</td>
        <td>${docente.documento}</td>
        <td>${docente.correo}</td>
      </tr>`;
  });
}

cargarCursos();
cargarDocentes();
