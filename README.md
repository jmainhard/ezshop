# Proyecto aplicación POO marzo 2021  

Boletero de ferretería con salida por consola (Java)  

---

   Reuniones:  
    - Lunes 8-febrero reunión, Reparto de funcionalidades, dependencias entorno de trabajo  
    - Miércoles 17-febrero reunión, cambios en la lógica del inventario del proyecto, ubicación de jsons y manejo de comprobantes.json en un archivo  
    - Domingo 7-marzo reunión, presentación  

---  

- Planificación general Proyecto ✅
- Diagrama UML VPP 🟨
- Reparto de funcionalidades ✅
- Código (Escribir testeos si surgen) 🟨
- 2 Excepciones personalizadas (2 / 2 mínimo pauta) ✅
- Tests (1 / 2 mínimo pauta) 🟨
- Revisión
- Presentación

- Clases Modelo
    - Cliente (Lado Vendedor) Funcionalidad venta  
        - // POSIBLE verificador de ruts  
        - updateInventario() -> al vender -> confirma -> genera comprobante -> imprime y guarda  
        - updateClientes -> (ClientesController)
    - Producto  
        - Stock extends
    - Imprimible (Interfaz) 
    - abstract Comprobante extends Imprimible  
        - Boleta extends  
        - Factura extends  
    - Canasta  
        - addProducto/removeProducto  


- Clases Controller
    - InventarioController  
        - inventario.json (Json), almacena objetos de tipo Stock  

    - Contabilidad
        - Reporta -> ingresos.json (Json archivo) 

    - ClientesController  
        - clientes.json (Json), almacena objetos de tipo HistorialCliente

    - Main  
        - Menú Principal
            - Nueva venta ✅
            - Buscar comprobante ✅
            - Reporte de ingresos ✅
            - Vista de Clientes ✅
            - Salir ✅


