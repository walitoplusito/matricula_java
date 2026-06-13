# Plan de Trabajo - Sistema de Matrícula Académica

## Descripción del Proyecto
Sistema básico de gestión de matrícula para institución académica, desarrollado en Java con Swing como parte del curso de Algoritmos y Estructura de Datos.

---

## Fases del Proyecto

### Fase 1: Configuración y Estructura Base (25%) ✅ COMPLETADO
**Meta:** Tener el proyecto configurado en Eclipse con estructura completa
- [x] Crear estructura de carpetas del proyecto Eclipse
- [x] Definir plan de trabajo y bitácora
- [x] Configurar archivos de datos iniciales (TXT)
- [x] Implementar login básico (admin/admin)
- [x] Crear clases modelo base (Alumno, Curso, Matrícula, Retiro)
- [x] Implementar DAOs para Alumno, Curso y Matrícula
- [x] Crear utilidades para archivos y correlativos
- [x] Compilación exitosa del código

### Fase 2: Módulo de Mantenimiento (30%) 🔄 EN PROGRESO (70% completado)
**Meta:** CRUD completo de Alumnos y Cursos
- [x] Interfaz de registro de Alumnos (COMPLETO)
- [ ] Interfaz de registro de Cursos (DAO listo, UI pendiente)
- [x] Validaciones de datos básicas
- [x] Persistencia en archivos TXT
- [ ] Listado visual de alumnos y cursos (pendiente)

### Fase 3: Módulo de Registro (25%) ⏳ PENDIENTE
**Meta:** Gestión de matrículas y retiros
- [ ] Interfaz de matrícula de alumnos a cursos
- [ ] Validación de prerequisitos y cupos
- [ ] Interfaz de retiro de matrícula
- [ ] Generación de correlativos automáticos
- [ ] Persistencia de matrículas y retiros

### Fase 4: Módulo de Consulta y Reportes (20%) ⏳ PENDIENTE
**Meta:** Consultas y generación de reportes
- [ ] Consulta de alumnos matriculados
- [ ] Consulta de cursos con disponibilidad
- [ ] Reporte de matrículas por curso
- [ ] Reporte de retiros
- [ ] Menú principal integrado

---

## Cronograma Estimado

| Fase | Porcentaje | Estado | Fecha Inicio | Fecha Fin |
|------|------------|--------|--------------|-----------|
| 1 | 25% | En progreso | Hoy | Hoy |
| 2 | 30% | Pendiente | - | - |
| 3 | 25% | Pendiente | - | - |
| 4 | 20% | Pendiente | - | - |

---

## Entregables
1. Código fuente en Java (Eclipse IDE)
2. Archivos de datos TXT
3. Manual de usuario
4. Documentación técnica
5. Esta bitácora actualizada

---

## Notas Técnicas
- **IDE:** Eclipse
- **Lenguaje:** Java
- **UI:** Swing (JFrame, Absolute Layout)
- **Persistencia:** Archivos TXT planos
- **Convención de nombres:** Ver carpeta `referencia/estilo_java/`
- **Correlativos:**
  - Alumno: `202610001` (año + 5 dígitos)
  - Matrícula: `100001` en adelante
  - Retiro: `200001` en adelante
