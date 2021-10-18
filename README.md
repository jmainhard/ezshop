# Ezshop

Boletero de ferretería con salida por consola (Java)

Este proyecto es una continuación de ProyectoBoletas  
[>> Repositorio disponible en GitHub <<](https://github.com/Esteb4nx/proyectoBoletas)

---

### Este repo:
La continuación del proyecto tiene como fin aplicar teoría de TDD, refactoring, calidad del código y documentación.

No se descarta la incorporación de nuevas funcionalidades aunque no es objetivo prioritario

---

#### Características del proyecto
La solución contiene al menos:
- 1 Clase de herencia
- 1 Clase Abstracta
- 1 Interface
- 1 Relación de Agregación
- 1 Relación de Composición
- 1 Relación de Dependencia   
  
Adicionalmente cumple con los siguientes características:
- Pruebas Unitarias para al menos 2 funcionalidades
- Programación funcional cada vez que se requiere trabajar con listas.
- Al menos 2 excepciones.

---  

#### Clases

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

---
Colaboradores originales:
- Maximiliano Campos
- Esteban Esparza
- Jorge Mainhard

Continuación del proyecto por: Esteban Esparza y Jorge Mainhard
retomado: 07 oct. 2021
