# Ex Proyecto aplicación POO marzo 2021

Boletero de ferretería con salida por consola (Java)  
Colaboradores originales: 
   - Maximiliano Campos
   - Esteban Esparza
   - Jorge Mainhard

Este proyecto fue iniciado y desarrollado durante gran parte enero de 2021 como parte de una
evaluación académica.  
[>> Repositorio disponible en GitHub <<](https://github.com/Esteb4nx/proyectoBoletas)

Continuación del proyecto por: Jorge Mainhard   
retomado: 25-04-2021  

---

### Hitos alcanzados
   - Planificación general Proyecto ✅
   - Diagrama UML VPP ✅
   - Reparto de funcionalidades ✅
   - Código ✅
   - 2 Excepciones personalizadas (2 / 2 mínimo pauta) ✅
   - Tests (2 / 2 mínimo pauta) ✅
   - Revisión ✅
   - Presentación ✅

---

### MIGRANDO: incluir pautas, detalles del proyecto en su continuación, detalles importantes para la colaboración, etc.

---

#### Características mínimas
La solución contiene al menos:
   - 1 Clase de herencia
   - 1 Clase Abstracta
   - 1 Interface
   - 1 Relación de Agregación
   - 1 Relación de Composición
   - 1 Relación de Dependencia   
  
Adicionalmente cumple con los siguientes contenidos:
   - Pruebas Unitarias para al menos 2 funcionalidades
   - Programación funcional cada vez que se requiere trabajar con listas.
   - Al menos 2 excepciones.

---  

#### Planificación clases

- **Clases Modelo**
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
    - Contabilidad
        - Reporta -> ingresos.json (Json archivo) 


- **Clases Controller**
    - InventarioController  
        - inventario.json (Json), almacena objetos de tipo Stock  

    - ClientesController  
        - clientes.json (Json), almacena objetos de tipo HistorialCliente

    - Main  
        - Menú Principal
            - Nueva venta ✅
            - Buscar comprobante ✅
            - Reporte de ingresos ✅
            - Vista de Clientes ✅
            - Salir ✅