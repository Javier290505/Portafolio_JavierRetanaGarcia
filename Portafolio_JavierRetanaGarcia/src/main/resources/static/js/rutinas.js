// funcion para hacer un preview de una imagen 
function mostrarImagen(input) {
    if (input.files && input.files[0]) {
        const imagen = input.files[0];
        const maximo = 512 * 1024; //Se limita el tamaño a 512 Kb las imágenes.
        if (imagen.size <= maximo) {
            var lector = new FileReader();
            lector.onload = function (e) {
                $('#blah').attr('src', e.target.result).height(200);
            };
            lector.readAsDataURL(input.files[0]);
        } else {
            alert("La imagen seleccionada es muy grande... no debe superar los 512 Kb!");
        }
    }
}
let idCategoriaSeleccionada = null;

// Cuando se abre el modal, guarda el ID de la categoría
document.addEventListener('DOMContentLoaded', function () {

    const confirmModal = document.getElementById('confirmModal');

    if (confirmModal) {

        confirmModal.addEventListener('show.bs.modal', function (event) {

            const button = event.relatedTarget;

            idCategoriaSeleccionada =
                    button.getAttribute('data-id');

            const descripcion =
                    button.getAttribute('data-descripcion');

            document.getElementById('modalDescripcion').textContent =
                    descripcion;

            console.log(
                    "Categoría seleccionada:",
                    idCategoriaSeleccionada
            );
        });
    }
});


// Se ejecuta al presionar Eliminar dentro del modal
function confirmarEliminacion() {

    if (idCategoriaSeleccionada !== null) {

        window.location.href =
                '/categoria/eliminar/' +
                idCategoriaSeleccionada;

    } else {

        alert("No se pudo obtener el ID de la categoría.");
    }
}

//Para quitar toast
setTimeout(() => {
    document.querySelectorAll('.toast').forEach(t => t.classList.remove('show'));
}, 5000);


let idProductoSeleccionado = null;

document.addEventListener('DOMContentLoaded', function () {

    const confirmModalProducto =
            document.getElementById('confirmModalProducto');

    if (confirmModalProducto) {

        confirmModalProducto.addEventListener(
                'show.bs.modal',
                function (event) {

                    const button = event.relatedTarget;

                    idProductoSeleccionado =
                            button.getAttribute('data-id');

                    const descripcion =
                            button.getAttribute('data-descripcion');

                    document.getElementById(
                            'modalDescripcionProducto'
                    ).textContent = descripcion;

                    console.log(
                            "Producto seleccionado:",
                            idProductoSeleccionado
                    );
                }
        );
    }
});


function confirmarEliminacionProducto() {

    if (idProductoSeleccionado) {

        window.location.href =
                '/producto/eliminar/' +
                idProductoSeleccionado;

    } else {

        alert(
            "No se pudo obtener el ID del producto."
        );
    }
}
