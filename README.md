# Proyecto Boletas aplicación POO		marzo 2021  

##### Boletero de ferretería con salida por consola (Java)  

---

**TODO: Aprender Json**
**Próximo Lunes (8-febrero) reunión, Hora: Por ver, apróx 17**

- Planificación general Proyecto ✅
- Diagrama UML VPP ✅ (Base lista, abierto a cambios)
- TODO Reparto de funcionalidades 
- Código
- Revisión
- Presentación

Ideas: Clases
- Clases candidatas
    - Cliente (Lado Vendedor)
     // POSIBLE verificador de ruts
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
    - Contabilidad // ControladorIngresos
        - // Reporta -> Ingresos (Json archivo) x mes

