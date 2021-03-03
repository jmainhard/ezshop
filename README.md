# Proyecto aplicación POO marzo 2021  

Boletero de ferretería con salida por consola (Java)  

---

**Lunes (8-febrero) reunión, Reparto de funcionalidades, dependencias entorno de trabajo**  
**Miércoles (17-febrero) reunión, cambios en la lógica del inventario del proyecto, ubicación de jsons y manejo de comprobantes.json en un archivo**

- Planificación general Proyecto ✅
- Diagrama UML VPP ✅ (Constante modificación)
- Reparto de funcionalidades ✅
- Código (Escribir testeos si surgen)
- 2 Excepciones personalizadas (Idea para una: StockInsuficienteException) (1 / 2 mínimo pauta)
- Tests (1 / 2 mínimo pauta)
- Revisión
- Presentación

- Clases Modelo
    - Cliente (Lado Vendedor) Funcionalidad venta  
        - // POSIBLE verificador de ruts  
        - updateInventario() -> al vender -> Baja Stock -> (InventarioController), genera comprobante, ? -> imprime y guarda  
        - updateClientes -> (ClientesController)
    - Producto  
    - Stock, atributos -> pdcto : Producto, cantidad : int
    - Imprimible (Interfaz) (pensar aplicación para otras clases)  
    - abstract Comprobante extends Imprimible  
        - Boleta extends  
        - Factura extends  
    - Canasta  
        - addProducto/removeProducto  


- Clases Controller
    - InventarioController  
        - inventario.json (Json), almacena objetos de tipo Stock  

    - ContabilidadController  
        - Reporta -> ingresos.json (Json archivo) x mes, lista de meses formato yyyy-mm (2021-02) con sus ingresos  
        - Idea: podría ser una lista de objetos de una Clase nombrada  Mes/Contabilidad, de atributos añoMes : String e ingresos : double  

    - ClientesController  
        - clientes.json (Json), almacena objetos de tipo HistorialCliente

    - Main (POR PLANIFICAR)  
        (algunas ideas preliminares -Jorge, abierto a colaboración)  
        - (Menús) vista de inventario, vista de boletas y vista de clientes, Nueva Venta, Salir
        - Nueva venta -> datos cliente, adición de productos a canasta (muestra inv, pregunta id), eliminación si desea (muestra canasta, pregunta id), generación de comprobante, imprimie comprobante (consola) guarda comprobante (json) ->  pregunta si desea comprar nuevamente, reinicia el ciclo


