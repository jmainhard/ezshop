# Proyecto aplicación POO marzo 2021  

Boletero de ferretería con salida por consola (Java)  

---

[**LINK Presentación**](https://docs.google.com/presentation/d/1I5EQ86TiMiDpk-DpyJEOj4hgxOGv0--WW2y1cKNZm5g/edit?usp=sharing)  

   Reuniones:  
    - Lunes 8-febrero reunión, Reparto de funcionalidades, dependencias entorno de trabajo  
    - Miércoles 17-febrero reunión, cambios en la lógica del inventario del proyecto, ubicación de jsons y manejo de comprobantes.json en un archivo  
    - Domingo 7-marzo reunión, presentación
   Presentación:

---  

#### Hitos
   - Planificación general Proyecto ✅
   - Diagrama UML VPP ✅
   - Reparto de funcionalidades ✅
   - Código ✅
   - 2 Excepciones personalizadas (2 / 2 mínimo pauta) ✅
   - Tests (2 / 2 mínimo pauta) ✅
   - Revisión ✅
   - Presentación 🟨

---


#### Pauta Presentación 

Tiempo por grupo 12 min + 3 para preguntas
   - Presentación de integrantes y nombre aplicación
   - Contexto/Problema en que se desenvuelve la aplicación
   - Nombrar funcionalidades que su solución implementó
   - Diagrama de clases
   - Mostrar pruebas unitarias, Excepciones y algoritmos "funcionales" implementados
   - Demostración de la aplicación

#### Pauta Proyecto 
La solución debe contener al menos:
   - 1 Clase de herencia
   - 1 Clase Abstracta
   - 1 Interface
   - 1 Relación de Agregación
   - 1 Relación de Composición
   - 1 Relación de Dependencia   
  
Adicionalmente debe contener los siguientes contenidos:
   - Pruebas Unitarias para al menos 2 funcionalidades
   - Programación funcional cada vez que se requiera trabajar con listas.
   - Implementar en su solución al menos 2 excepciones.

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
