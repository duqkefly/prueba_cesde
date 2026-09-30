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

async function cargarCursos() {
  const res = await fetch(`${API}/cursos`);
  const cursos = await res.json();
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

async function cargarDocentes() {
  const res = await fetch(`${API}/docentes`);
  const docentes = await res.json();
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
