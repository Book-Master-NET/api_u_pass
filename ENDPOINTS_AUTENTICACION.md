# Endpoints de Autenticación

Este documento describe los endpoints de autenticación implementados en el sistema de asistencia.

## Base URL
```
http://localhost:8080/api/auth
```

## Endpoints Disponibles

### 1. Login
**POST** `/api/auth/login`

Autentica un usuario y retorna un token JWT.

#### Request Body
```json
{
  "email": "usuario@ejemplo.com",
  "password": "password123"
}
```

#### Response (200 OK)
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tipo": "Bearer",
  "id": 1,
  "email": "usuario@ejemplo.com",
  "nombre": "Juan",
  "apellido": "Pérez",
  "roles": ["ESTUDIANTE"]
}
```

#### Códigos de Error
- `400 Bad Request`: Datos de entrada inválidos
- `401 Unauthorized`: Credenciales inválidas
- `404 Not Found`: Usuario no encontrado

### 2. Refresh Token
**POST** `/api/auth/refresh`

Renueva un token JWT válido.

#### Request Body
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

#### Response (200 OK)
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tipo": "Bearer",
  "id": 1,
  "email": "usuario@ejemplo.com",
  "nombre": "Juan",
  "apellido": "Pérez",
  "roles": ["ESTUDIANTE"]
}
```

#### Códigos de Error
- `400 Bad Request`: Token inválido
- `401 Unauthorized`: Token expirado o inválido

### 3. Logout
**POST** `/api/auth/logout`

Cierra la sesión del usuario.

#### Request Body
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

#### Response (200 OK)
```
(Respuesta vacía)
```

#### Códigos de Error
- `400 Bad Request`: Token inválido

## Uso de los Tokens

### Autenticación en Requests
Para acceder a endpoints protegidos, incluye el token en el header `Authorization`:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Configuración del Token
- **Expiración**: 24 horas (86400000 ms)
- **Algoritmo**: HS256
- **Formato**: JWT estándar

## Roles del Sistema

El sistema incluye los siguientes roles predefinidos:

- `ADMIN`: Administrador del sistema
- `PROFESOR`: Profesor
- `ESTUDIANTE`: Estudiante
- `PERSONAL`: Personal administrativo

## Documentación Swagger

La documentación interactiva de la API está disponible en:
```
http://localhost:8080/swagger-ui.html
```

## Variables de Entorno

Configura las siguientes variables de entorno:

```bash
# Base de datos
DATABASE_URL=jdbc:postgresql://localhost:5432/attendance_system
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=password

# JWT
JWT_SECRET=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
```

## Ejemplo de Uso con cURL

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@ejemplo.com",
    "password": "password123"
  }'
```

### Refresh Token
```bash
curl -X POST http://localhost:8080/api/auth/refresh \
  -H "Content-Type: application/json" \
  -d '{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }'
```

### Logout
```bash
curl -X POST http://localhost:8080/api/auth/logout \
  -H "Content-Type: application/json" \
  -d '{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }'
```

### Acceso a Endpoint Protegido
```bash
curl -X GET http://localhost:8080/api/protected-endpoint \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```
