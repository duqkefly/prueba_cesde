const API = 'http://localhost:8080/api';

const btnPrueba = document.getElementById('btnPrueba');

btnPrueba.addEventListener('click', async () => {
  try {
    const res = await fetch(`${API}/prueba`);
    const data = await res.json();
    alert(`${data.mensaje}\nDocentes: ${data.docentes}\nCursos: ${data.cursos}`);
  } catch (error) {
    alert('No hay conexion con el backend');
  }
});
