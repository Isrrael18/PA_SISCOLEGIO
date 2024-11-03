$(document).ready(function () {
    const urlDocentes = "/v1/docentes"; // Cambiar a la URL correcta para profesores
    const urlCursos = "/v1/cursos"; // URL para obtener cursos
    const urlAsignacion = "/v1/curso_docentes"; // Cambiar a la URL correcta para la asignación

    const cursosMap = {}; // Crear un mapa para almacenar cursos
    const docentesMap = {}; // Crear un mapa para almacenar docentes

    // Función para cargar docentes en el combo box
    function cargarDocentes() {
        $.ajax({
            url: urlDocentes,
            method: 'GET',
            success: function (docentes) {
                const docenteSelect = $('#docente');
                docenteSelect.empty(); // Limpiar el combo box antes de llenarlo
                docenteSelect.append($('<option>', {
                    value: '',
                    text: 'Seleccione un docente',
                    disabled: true,
                    selected: true
                }));
                if (docentes.ok) {
                    $.each(docentes.body, function (index, docente) {
                        docenteSelect.append($('<option>', {
                            value: docente.id,
                            text: `${docente.nombre} ${docente.apellido}` // Mostrar nombre y apellido
                        }));
                        // Guardar el nombre del docente en el mapa
                        docentesMap[docente.id] = `${docente.nombre} ${docente.apellido}`;
                    });
                } else {
                    console.error('Error al cargar docentes:', docentes.message);
                }
            },
            error: function (err) {
                console.error('Error al cargar docentes:', err);
            }
        });
    }

    // Función para cargar cursos en el combo box
    function cargarCursos() {
        $.ajax({
            url: urlCursos,
            method: 'GET',
            success: function (cursos) {
                const cursoSelect = $('#curso');
                cursoSelect.empty(); // Limpiar el combo box antes de llenarlo
                cursoSelect.append($('<option>', {
                    value: '',
                    text: 'Seleccione un curso',
                    disabled: true,
                    selected: true
                }));
                if (cursos.ok) {
                    $.each(cursos.body, function (index, curso) {
                        cursoSelect.append($('<option>', {
                            value: curso.id,
                            text: curso.nombre // Ajusta el campo según tu DTO
                        }));
                        // Guardar el nombre del curso en el mapa
                        cursosMap[curso.id] = curso.nombre;
                    });
                } else {
                    console.error('Error al cargar cursos:', cursos.message);
                }
            },
            error: function (err) {
                console.error('Error al cargar cursos:', err);
            }
        });
    }

    // Cargar docentes y cursos al inicio
    cargarDocentes();
    cargarCursos();

    // Asignar curso a docente
    $('#asignar').click(function () {
        const docenteId = $('#docente').val();
        const cursoId = $('#curso').val();

        if (!docenteId || !cursoId) {
            $('#error-message').text("Por favor, seleccione un docente y un curso.").removeClass("d-none");
            return;
        }

		$.ajax({
		    url: urlAsignacion,
		    method: 'POST',
		    contentType: 'application/json',
		    data: JSON.stringify({ docente_id: docenteId, curso_id: cursoId }),
		    success: function (response) {
		        if (response.ok) {
		            Swal.fire({
		                icon: 'success',
		                title: 'Asignación exitosa',
		                text: 'El curso ha sido asignado al docente.',
		            });
		            $('#docente').val('');
		            $('#curso').val('');
		        } else {
		            $('#error-message').text(response.message).removeClass("d-none");
		            console.error('Error en la respuesta del servidor:', response);
		        }
		    },
		    error: function (jqXHR) {
		        const errorMessage = jqXHR.responseJSON?.message || "Error inesperado. Código: " + jqXHR.status;
		        $('#error-message').text(errorMessage).removeClass("d-none");
		        console.error('Error al asignar curso:', jqXHR);
		    }
		});

    });
});
