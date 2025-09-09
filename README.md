<h1>Mirar anotaciones<h1>

<p>En messages.proprties he establecido valores de errores unicos<p>
<p>Añadi un validador unico el cual he de acordarme de que se ha de pasar la clase como argumento para que pueda validar y ser de añoyo en el controlador del formulario<p>
<p>Se puede automatizar el controlador y no tener que añadirlo mediante validador.validate(usuario,result).. en la clase (mas el atributo de la calse validadora con su respectivo autowired)<p>
<p>Se han creado anotaciones para validar toma de valores<p>
<p>He establecido un select y tomado valores con get, luego he tomado varios de estos para pasarlo luego a otro html que muestra los valores del resultado. También , se ha automatizado más la accion de pasar los valores mediante la creacion de una clase llamada PaisServiceImpl, la cual tenia establecida su scope con una interfaz para respetar el DIP<p>
<p>He pasado la clase entera en vez de realizar una division por "-", tambien he usado un checkbox <p>
<p>Añadido un boton de radio para poder escoger genero<p>
<p>Establecimiento de un valor secreto con input type="hidden"<p>
<p>Trabajando en cambiar el metodo Post de la redireccion para mostrar el resultado del formulario al metodo get<p>
<p>Establecimiento de sessionattribute, aprendiendo como funciona todo al detalle <p>
<p>Añadiendo bootstrap<p>

<p>Añadiendo más valores de bootsrap , continuando el repaso de como funciona spring boot<p>

# Bootstrap 5 — Chuleta básica para formularios (README)

## 1 Inclusión y orden de CSS

**Carga Bootstrap primero y tu hoja de estilos después** (para poder sobrescribir).

    <!-- layout.html / formulario.html -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link th:href="@{/css/estiloFormulario.css}" rel="stylesheet"> <!-- tu CSS -->

**Estructura de ficheros estáticos en Spring Boot**

- `src/main/resources/static/css/estiloFormulario.css` → accesible como `/css/estiloFormulario.css`
- Enlaza con `th:href="@{/css/estiloFormulario.css}"` (conscious del context-path)

---

## 2 Contenedor y tipografía

- `.container` centra y limita el ancho.
- Utilidades de texto:
  - `fw-bold` / `fw-semibold` (peso)
  - `fs-6 … fs-1` (tamaño)
  - `d-block` (forzar bloque)
  - `text-nowrap` (evitar salto de línea)

---

## 3 Grid (fila/columnas)

**Regla de oro**: `.row` → hijos inmediatos `.col-*` (no metas elementos sueltos dentro de `.row`).

- Gutters (separación columnas): `g-0`, `g-1`, `g-2` …; solo horizontal `gx-*`, solo vertical `gy-*`.
- Alineación vertical en filas: `align-items-center`.

**Patrones:**

- **Label ancho fijo** + **input ocupa resto**:

    <div class="row g-2 align-items-center mb-3">
      <label for="email" class="col-sm-3 col-form-label">Email</label>
      <div class="col-sm-9">
        <input id="email" class="form-control">
      </div>
    </div>

- **Label al ancho del texto (más compacto)**:

    <div class="row gx-2 gy-1 align-items-center mb-2">
      <label for="nombre" class="col-auto col-form-label pe-2">Nombre</label>
      <div class="col">
        <input id="nombre" class="form-control">
      </div>
    </div>

---

## 4 Formularios (clases correctas)

- Campos de texto/número/fecha: `.form-control` (opcional `.form-control-sm`).
- Select: `.form-select` (no `.form-control`).
- Labels:
  - Vertical: `.form-label`
  - Horizontal (con grid): `.col-form-label` (+ `-sm` si compacto)
- Checkbox/Radio: estructura **obligatoria**:

    <div class="form-check">
      <input class="form-check-input" type="checkbox" id="rol1">
      <label class="form-check-label" for="rol1">Administrador</label>
    </div>

  - En línea: añadir `.form-check-inline` al wrapper.

**Patrón vertical simple (recomendado):**

    <div class="mb-3">
      <label for="campo" class="form-label">Etiqueta</label>
      <input id="campo" class="form-control">
      <div class="invalid-feedback">Mensaje de error</div>
    </div>

---

## 5 Validación visual (errores) + Thymeleaf

- Borde rojo: clase `is-invalid` en el control.
- Mensaje: `<div class="invalid-feedback">…</div>` **después** del input y con el **mismo padre**.

**Con Thymeleaf (binding + errores):**

    <!-- th:object="${usuario}" en el <form> -->
    <div class="mb-3">
      <label for="emailUsuario" class="form-label">Email</label>
      <input id="emailUsuario"
             class="form-control"
             th:field="*{emailUsuario}"
             th:errorclass="is-invalid">
      <div class="invalid-feedback" th:errors="*{emailUsuario}"></div>
    </div>

**Checkbox / Radio con errores (mensaje de grupo):**

    <!-- Roles (checkbox) -->
    <div class="mb-3">
      <span class="d-block fw-bold mb-2">Rol</span>
      <div th:each="r : ${listaRoles}" class="form-check">
        <input class="form-check-input" type="checkbox"
               th:field="*{rolesUsuario}" th:value="${r.idRol}"
               th:errorclass="is-invalid">
        <label class="form-check-label"
               th:for="${#ids.prev('rolesUsuario')}"
               th:text="${r.nombreRol}"></label>
      </div>
      <div class="invalid-feedback d-block" th:errors="*{rolesUsuario}"></div>
    </div>

    <!-- Género (radios en línea) -->
    <div class="mb-3">
      <span class="d-block fw-bold mb-2">Género</span>
      <div th:each="g : ${listaGeneros}" class="form-check form-check-inline">
        <input class="form-check-input" type="radio"
               th:field="*{generoUsuario}" th:value="${g}"
               th:errorclass="is-invalid">
        <label class="form-check-label"
               th:for="${#ids.prev('generoUsuario')}"
               th:text="${g}"></label>
      </div>
      <div class="invalid-feedback d-block" th:errors="*{generoUsuario}"></div>
    </div>

> Requisitos: `@Valid` en el `@PostMapping`, `BindingResult` justo detrás, y devolver la vista del formulario cuando `hasErrors()`.

---

## 6 Espaciado y utilidades frecuentes

- Grupos de campos: `mb-3` (en v5 **no existe** `form-group`).
- Separar icono/checkbox del texto: `me-2` en el input.
- Botones a ancho completo: `w-100`.
- Márgenes/Paddings: `m*-*`/`p*-*` (ej. `mb-2`, `pe-2`).

---

## 7 Botones

    <button class="btn btn-primary">Enviar</button>
    <!-- Variantes: btn-outline-primary, btn-sm, w-100 -->

---

## 8 “No hacerlo así”

- No meter elementos sueltos dentro de `.row` (usa `.col-*`).
- No aplicar `col-*` directamente al `<input>` (van en el wrapper `.col-*`).
- No usar `form-group` (era de Bootstrap 4).
- No poner `.form-control` en checkbox/radio (usa `.form-check*`).

---

## 9 Mini–glosario de clases usadas

- **Layout**: `container`, `row`, `col-*`, `col`, `col-auto`, `g-*`, `gx-*`, `gy-*`, `align-items-center`
- **Form**: `form-label`, `col-form-label`, `form-control`, `form-control-sm`, `form-select`, `form-check`, `form-check-input`, `form-check-label`, `form-check-inline`
- **Validación**: `is-invalid`, `invalid-feedback`
- **Espaciado**: `mb-3`, `mb-2`, `me-2`, `pe-2`
- **Texto**: `fw-bold`, `fw-semibold`, `fs-*`, `d-block`, `text-nowrap`
- **Botones**: `btn`, `btn-primary`, `btn-outline-*`, `btn-sm`, `w-100`

---

## 10 Snippets listos para pegar

**Vertical simple:**

    <div class="mb-3">
      <label for="nombre" class="form-label">Nombre</label>
      <input id="nombre" class="form-control" th:field="*{nameUsuario}" th:errorclass="is-invalid">
      <div class="invalid-feedback" th:errors="*{nameUsuario}"></div>
    </div>

**Horizontal compacto:**

    <div class="row gx-2 gy-1 align-items-center mb-2">
      <label for="email" class="col-auto col-form-label">Email</label>
      <div class="col">
        <input id="email" class="form-control" th:field="*{emailUsuario}" th:errorclass="is-invalid">
        <div class="invalid-feedback" th:errors="*{emailUsuario}"></div>
      </div>
    </div>

**Select correcto:**

    <div class="mb-3">
      <label for="pais" class="form-label">País</label>
      <select id="pais" class="form-select" th:field="*{paisUsuario}" th:errorclass="is-invalid">
        <option value="">--Seleccione--</option>
        <option th:each="p : ${listaPaises}" th:value="${p.idPais}" th:text="${p.nombrePais}"></option>
      </select>
      <div class="invalid-feedback" th:errors="*{paisUsuario}"></div>
    </div>

**Checkbox (vertical) + mensaje de grupo:**

    <div class="mb-3">
      <span class="d-block fw-bold mb-2">Roles</span>
      <div th:each="r : ${listaRoles}" class="form-check">
        <input class="form-check-input" type="checkbox"
               th:field="*{rolesUsuario}" th:value="${r.idRol}" th:errorclass="is-invalid">
        <label class="form-check-label" th:for="${#ids.prev('rolesUsuario')}" th:text="${r.nombreRol}"></label>
      </div>
      <div class="invalid-feedback d-block" th:errors="*{rolesUsuario}"></div>
    </div>

**Radios en línea:**

    <div class="mb-3">
      <span class="d-block fw-bold mb-2">Género</span>
      <div th:each="g : ${listaGeneros}" class="form-check form-check-inline">
        <input class="form-check-input" type="radio"
               th:field="*{generoUsuario}" th:value="${g}" th:errorclass="is-invalid">
        <label class="form-check-label" th:for="${#ids.prev('generoUsuario')}" th:text="${g}"></label>
      </div>
      <div class="invalid-feedback d-block" th:errors="*{generoUsuario}"></div>
    </div>


<p>Aprendiendo que son los interceptores<p>
<p>Añadiendo el primer interceptor<p>