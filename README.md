# Proyecto aplicación POO marzo 2021  

Boletero de ferretería con salida por consola (Java)  

---

**Lunes (8-febrero) reunión, Reparto de funcionalidades, dependencias entorno de trabajo**
**Miércoles (17-febrero) reunión, cambios en la lógica del inventario del proyecto, ubicación de jsons y manejo de comprobantes.json en un archivo**

- Planificación general Proyecto ✅
- Diagrama UML VPP ✅ (Base lista, abierto a cambios)
- Reparto de funcionalidades ✅
- Código (Escribir testeos si surgen)
- 2 Excepciones personalizadas (Idea para una: StockInsuficienteException)
- Tests
- Revisión
- Presentación

- Clases Modelo
    - Cliente (Lado Vendedor) Funcionalidad compra  
        - // POSIBLE verificador de ruts  
        - compra -> Baja Stock -> (InventarioController), genera comprobante, ? -> imprime y guarda  
        - updateIngresos -> (ContabilidadController)  
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
        - Reporta -> Ingresos.json (Json archivo) x mes, lista de meses formato yyyy-mm (2021-02) con sus ingresos  
        - Idea: podría ser una lista de objetos de una Clase nombrada  Mes/Contabilidad, de atributos añoMes : String e ingresos : double  

    - ClientesController  
        - clientes.json (Json) attributes: nombre, rut, Lista comprobantesAsociados (por id) (ex HistorialClientes)  
        - Idea json: Una lista de objetos de tipo RegistroCliente, de atributos nombre, rut y Lista comprobantesAsociados (para ignorar canasta)  

    - Main (POR PLANIFICAR)  
        (algunas ideas preliminares -Jorge, abierto a colaboración)  
        - Menús, vista de inventario, vista de boletas y vista de clientes, Nueva compra, Salir
        - Nueva compra -> datos cliente, adición de productos a canasta (muestra inv, pregunta id), eliminación si desea (muestra canasta, pregunta id), generación de comprobante, imprimie comprobante (consola) guarda comprobante (json) ->  pregunta si desea comprar nuevamente, reinicia el ciclo


