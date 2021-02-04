# proyectoBoletas

Proyecto aplicación POO		marzo 2021
Boletero de ferretería con salida por consola (Java)

- Planificación general Proyecto
- Diagrama UML en draw.io colaborativo
- Reparto de funcionalidades
- Código
- Diagrama UML en VPP

Ideas: Clases
- Clases candidatas
    - Cliente (Lado Vendedor)
     // TODO verificador de ruts
    - Producto
    - ControlInventario 
        - Inventario (Json)
    - Imprimible (Interfaz) (pensar aplicación pa otras clases)
    - Comprobante extends Imprimible (abstracta) (repensar nombre)
        - Boleta extends
        - Factura extends
    - HistorialClientes (Json) attributes: nombre, rut, comprobantesAsociados
    - Canasta (candidato a funcionalidad compra)
        - compra -> baja inventario, genera comprobante (imprime), limpia canasta
        - addProducto/delProducto
        - updateIngresos
    - Contabilidad 
        - // Reporta -> Ingresos (Json archivo)

// Ideas: funcionalidades
