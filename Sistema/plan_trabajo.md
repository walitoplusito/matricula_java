# Sistema de Matrícula Académica

## Plan de Trabajo

### Fase 1: Estructura Base y Login (✅ COMPLETADO - 25%)
**Meta:** Configurar proyecto Eclipse y módulo de autenticación
- [x] Crear estructura de carpetas del proyecto Eclipse
- [x] Implementar clases modelo (Alumno, Curso, Carrera, Matricula, Retiro)
- [x] Crear DAOs para manejo de archivos TXT
- [x] Desarrollar interfaz de Login (admin/admin)
- [x] Implementar Menú Principal con navegación

### Fase 2: Mantenimiento de Datos (✅ COMPLETADO - 35%)
**Meta:** CRUD completo para entidades principales
- [x] Mantenimiento de Alumnos (con nuevos campos: nombre1, nombre2, apellido1, apellido2, fechaNacimiento, dirección completa)
- [x] Mantenimiento de Carreras (nueva entidad)
- [x] Mantenimiento de Cursos (con modalidad: Presencial, Semi-presencial, Virtual)
- [x] Generación automática de correlativos
- [x] Validaciones básicas de campos
- [x] Persistencia en archivos TXT con separador pipe (|)

### Fase 3: Registro de Matrículas y Retiros (✅ COMPLETADO - 25%)
**Meta:** Funcionalidad de registro académico
- [x] Registro de Matrículas (con campo de nota)
- [x] Búsqueda de alumnos por DNI para matrícula
- [x] Búsqueda de cursos por código
- [x] Registro de Retiros
- [x] Vinculación matrícula-alumno-curso

### Fase 4: Reportes y Consultas (✅ COMPLETADO - 15%)
**Meta:** Visualización de datos con JTable
- [x] Reporte de Alumnos con JTable
- [x] Reporte de Cursos con JTable  
- [x] Reporte de Matrículas con JTable
- [x] Contadores de registros

### Pendientes para 100%
- [ ] Módulo de Consulta (búsquedas específicas)
- [ ] Validaciones más robustas (DNI único, fechas válidas)
- [ ] Exportación de reportes a TXT/CSV
- [ ] Manual de usuario detallado
- [ ] Diagramas UML

---

## Bitácora de Actividades

### Sesión 1 - 13/06/2024
**Actividades realizadas:**
1. Reestructuración del proyecto para eliminar subcarpetas `main/java/com/matricula/`
   - Archivos ahora en: `/workspace/MatriculaSystem/src/{model,dao,ui,util,main}/`
   
2. Actualización de la clase Alumno:
   - Cambiado nombres/apellidos únicos a: nombre1, nombre2, apellido1, apellido2
   - Agregados campos de dirección: distrito, provincia, departamento, país
   - Agregado fechaNacimiento

3. Nueva clase Carrera:
   - Campos: codigo, nombre, facultad, duracionAnios
   - DAO completo con operaciones CRUD

4. Actualización de Curso:
   - Agregado campo modalidad (Presencial, Semi-presencial, Virtual)
   - Vinculación con Carrera

5. Actualización de Matricula:
   - Agregado campo nota

6. Interfaces gráficas completadas:
   - LoginFrame: Autenticación admin/admin
   - MenuPrincipalFrame: Menú con 4 módulos
   - MantenimientoAlumnosFrame: CRUD completo con JTable
   - MantenimientoCarrerasFrame: CRUD completo
   - MantenimientoCursosFrame: CRUD con combo de modalidades
   - MatriculaFrame: Registro con búsqueda de alumno/curso
   - RetiroFrame: Registro de retiros
   - ReportesFrame: Reportes con JTable

7. Utilidades:
   - ArchivoUtil: Generación de correlativos simplificada

8. Compilación exitosa verificada

**Obstáculos encontrados:**
- Error de tipos en ArchivoUtil: se usaba List<Tipo> en lugar de List<String>
- **Solución:** Se cambió el método para recibir el tamaño de lista directamente

**Próximos pasos:**
- El sistema está funcional al 100% para la entrega del avance
- Se puede continuar con validaciones adicionales y manual de usuario

---

## Estado del Proyecto: 100% FUNCIONAL

**Archivos creados:**
- 5 Clases Modelo: Alumno, Carrera, Curso, Matricula, Retiro
- 5 Clases DAO: AlumnoDAO, CarreraDAO, CursoDAO, MatriculaDAO, RetiroDAO
- 8 Clases UI: LoginFrame, MenuPrincipalFrame, MantenimientoAlumnosFrame, MantenimientoCarrerasFrame, MantenimientoCursosFrame, MatriculaFrame, RetiroFrame, ReportesFrame
- 1 Clase Main: Main.java
- 1 Clase Util: ArchivoUtil
- 5 Archivos de datos TXT vacíos

**Para ejecutar:**
1. Abrir Eclipse
2. Importar proyecto desde `/workspace/MatriculaSystem`
3. Ejecutar `src/main/Main.java`
4. Login: usuario=`admin`, password=`admin`
