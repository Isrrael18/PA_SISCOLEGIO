const url = "/v1/docentes"; // Cambiar a la URL correcta para docentes

function ajaxRequest(type, endpoint, data = null) {
    return $.ajax({
        type,
        url: endpoint,
        data: data ? JSON.stringify(data) : null,
        dataType: "json",
        contentType: data ? "application/json" : undefined,
        cache: false,
        timeout: 600000,
    });
}

function save(bandera) {
    const id = $("#guardar").data("id");
    const docente = {
        id,
        nombre: $("#nombre").val(),
        apellido: $("#apellido").val(),
        dni: $("#dni").val(),
        genero: $("#genero").val(),
        direccion: $("#direccion").val(),
        email: $("#correo").val(),
        telefono: $("#telefono").val(),
        fecha_contratacion: $("#fecha_contratacion").val(),
    };

    const type = bandera === 1 ? "POST" : "PUT";
    const endpoint = bandera === 1 ? url : `${url}/${id}`;

    ajaxRequest(type, endpoint, docente)
        .done((data) => {
            if (data.ok) {
                $("#modal-update").modal("hide");
                getTabla();
                $("#error-message").addClass("d-none");
                Swal.fire({
                    icon: 'success',
                    title: `Se ha ${bandera === 1 ? 'guardado' : 'actualizado'} el docente`,
                    showConfirmButton: false,
                    timer: 1500
                });
                clear();
            } else {
                showError(data.message);
            }
        })
        .fail(function (jqXHR) {
            const errorMessage = jqXHR.responseJSON?.message || "Error inesperado. Código: " + jqXHR.status;
            showError(errorMessage);
        });
}

function showError(message) {
    $("#error-message").text(message).removeClass("d-none");
}

function deleteFila(id) {
    ajaxRequest("DELETE", `${url}/${id}`)
        .done((data) => {
            if (data.ok) {
                Swal.fire({
                    icon: 'success',
                    title: 'Se ha eliminado el docente',
                    showConfirmButton: false,
                    timer: 1500
                });
                getTabla();
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: data.message,
                    confirmButtonText: 'Aceptar'
                });
            }
        })
        .fail(handleError);
}

function getTabla() {
    ajaxRequest("GET", url)
        .done((data) => {
            const t = $("#tablaRegistros").DataTable();
            t.clear();

            if (data.ok) {
                $.each(data.body, (index, docente) => {
                    const botonera = `
                        <button type="button" class="btn btn-warning btn-xs editar">
                            <i class="fas fa-edit"></i>
                        </button>
                        <button type="button" class="btn btn-danger btn-xs eliminar">
                            <i class="fas fa-trash"></i>
                        </button>`;
                    
                    const generoTexto = docente.genero === 'M' ? 'Masculino' : 'Femenino';

                    const fechaContratacion = docente.fecha_contratacion ? 
                        formatFecha(docente.fecha_contratacion) : 
                        'Fecha no disponible';

                    t.row.add([botonera, docente.id, docente.nombre, docente.apellido, docente.dni, generoTexto, docente.direccion, docente.email, docente.telefono, fechaContratacion]);
                });
                t.draw();
            } else {
                console.error("Error en la respuesta: ", data.message);
            }
        })
        .fail(handleError);
}

function getFila(id) {
    ajaxRequest("GET", `${url}/${id}`)
        .done((data) => {
            if (data.ok) {
                $("#modal-title").text("Editar docente");
                $("#nombre").val(data.body.nombre);
                $("#apellido").val(data.body.apellido);
                $("#dni").val(data.body.dni);
                $("#genero").val(data.body.genero);
                $("#direccion").val(data.body.direccion);
                $("#correo").val(data.body.email);
                $("#telefono").val(data.body.telefono);
                $("#fecha_contratacion").val(data.body.fecha_contratacion); // Asignar fecha de contratación
                $("#guardar").data("id", data.body.id).data("bandera", 0);
                $("#modal-update").modal("show");
            } else {
                showError(data.message);
            }
        })
        .fail(handleError);
}

function clear() {
    $("#modal-title").text("Nuevo docente");
    $("#nombre").val("");
    $("#apellido").val("");
    $("#dni").val("");
    $("#genero").val(""); // Limpiar el género seleccionado
    $("#direccion").val("");
    $("#correo").val("");
    $("#telefono").val("");
    $("#fecha_contratacion").val(""); // Limpiar la fecha
    $("#guardar").data("id", 0).data("bandera", 1);
}

function handleError(jqXHR) {
    const errorMessage = jqXHR.responseJSON?.message || `Error inesperado. Código: ${jqXHR.status}`;
    Swal.fire({
        icon: 'error',
        title: 'Error',
        text: errorMessage,
        confirmButtonText: 'Aceptar'
    });
}

function formatFecha(fecha) {
    const date = new Date(fecha);
    if (!isNaN(date.getTime())) {
        return date.toLocaleDateString('es-ES', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit'
        });
    }
    return 'Fecha no válida';
}

$(document).ready(function () {
    const t = $("#tablaRegistros").DataTable({
        language: {
            lengthMenu: "Mostrar _MENU_ docentes",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del _START_ al _END_ de _TOTAL_ docentes",
            infoEmpty: "Sin resultados",
            search: "Buscar: ",
            paginate: {
                first: "Primero",
                last: "Último",
                next: "Siguiente",
                previous: "Anterior",
            },
        },
        columnDefs: [
            { targets: 0, orderable: false }
        ],
    });

    clear();

    $("#nuevo").click(clear);

    $("#guardar").click(() => save($("#guardar").data("bandera")));

    $(document).on('click', '.eliminar', function () {
        const id = $(this).closest('tr').find('td:eq(1)').text();
        Swal.fire({
            title: 'Eliminar docente',
            text: "¿Está seguro de querer eliminar este docente?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si'
        }).then((result) => {
            if (result.isConfirmed) {
                deleteFila(id);
            }
        });
    });

    $(document).on('click', '.editar', function () {
        const id = $(this).closest('tr').find('td:eq(1)').text();
        getFila(id);
    });

    getTabla(); // Llama a getTabla para cargar los docentes al inicio
});
