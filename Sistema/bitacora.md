# Bitácora de Actividades - Sistema de Matrícula

## Sesión 1 - Inicio del Proyecto

### Fecha: Junio 2026
### Objetivo: Configuración inicial y estructura base (25% del proyecto)

---

### Actividades Realizadas

#### 1. Configuración del Proyecto ✅
- [x] Creada estructura de carpetas compatible con Eclipse IDE
- [x] Definidos paquetes: main, ui, dao, model, util
- [x] Creados directorios para resources, data y bin
- [x] Configurados archivos .project y .settings para Eclipse

#### 2. Documentación ✅
- [x] Plan de trabajo creado en `/Sistema/plan_trabajo.md`
- [x] Bitácora inicializada (este archivo)

#### 3. Archivos de Datos ✅
- [x] Creados archivos TXT iniciales: alumnos.txt, cursos.txt, matriculas.txt, retiros.txt
- [x] Definido formato de almacenamiento pipe-separated (|)

#### 4. Clases Modelo ✅
- [x] Alumno.java - con getters/setters, toString(), fromLine()
- [x] Curso.java - con gestión de vacantes y prerequisitos
- [x] Matricula.java - con estados ACTIVA/RETIRADA
- [x] Retiro.java - con motivo y estado

#### 5. Clases DAO ✅
- [x] AlumnoDAO.java - CRUD completo + búsqueda por DNI
- [x] CursoDAO.java - CRUD completo + búsqueda por nombre
- [x] MatriculaDAO.java - CRUD + búsquedas por alumno/curso

#### 6. Utilidades ✅
- [x] ArchivoUtil.java - Lectura/escritura de archivos TXT
- [x] CorrelativoUtil.java - Generación de códigos automáticos

#### 7. Interfaz Gráfica (Swing) ✅
- [x] LoginFrame.java - Login con admin/admin
- [x] MenuPrincipalFrame.java - Menú con 4 módulos
- [x] MantenimientoFrame.java - Registro de alumnos (completo) y cursos (pendiente)

#### 8. Clase Principal ✅
- [x] Main.java - Punto de entrada, inicializa archivos

#### 9. Compilación ✅
- [x] Código compilado exitosamente en /bin

---

### Obstáculos Encontrados

| Obstáculo | Solución | Estado |
|-----------|----------|--------|
| Ninguno | - | Resuelto |

---

### Próximos Pasos (Fase 2)
1. Completar interfaz de registro de Cursos
2. Implementar módulo de Registro (Matrículas)
3. Implementar módulo de Registro (Retiros)
4. Agregar validaciones de prerequisitos y cupos

---

### Notas Importantes
- **Credenciales:** admin / admin
- **Correlativos:**
  - Alumno: 202610001 (año + 5 dígitos)
  - Matrícula: 100001 en adelante
  - Retiro: 200001 en adelante
- **Archivos:** Se guardan en carpeta /data con separador pipe (|)
- **Layout:** Absolute (null layout) como requiere el curso

---

### Progreso Actual: 55% completado (Fase 1 completada + inicio Fase 2)

### Métricas del Avance
- **Clases Java:** 13 archivos creados
- **Módulos implementados:** 1 de 4 (Mantenimiento - Alumnos)
- **CRUDs completos:** Alumno (100%), Curso (DAO listo, UI pendiente)
- **Compilación:** Exitosa sin errores
