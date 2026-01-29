# ComponentesAndroidDemo 📱

[![Platform](https://img.shields.io/badge/Platform-Android-brightgreen)](https://developer.android.com/)
[![Language](https://img.shields.io/badge/Language-Java-orange)](#)
[![UI](https://img.shields.io/badge/UI-XML%20Views-blue)](#)
[![Build](https://img.shields.io/badge/Build-Gradle-informational)](#)
[![Min SDK](https://img.shields.io/badge/minSdk-24-blueviolet)](#)
[![Target SDK](https://img.shields.io/badge/targetSdk-36-blueviolet)](#)
[![License](https://img.shields.io/badge/License-MIT-lightgrey)](#licencia)

Demo académica que implementa **Componentes Fundamentales de Android (Application Components)**:
- **Activity**
- **Service (Foreground Service)**
- **BroadcastReceiver**
- **Content Provider**
- **Intent (Explícito e Implícito)**

> Proyecto orientado a evidenciar arquitectura y funcionamiento interno del framework Android (no solo UI widgets).

---

## 🧩 Funcionalidades (5 implementaciones)

✅ **1) Intent explícito (Activity → DetailActivity)**  
Abre una segunda pantalla y muestra un ID recibido por `Intent extras`.

✅ **2) Foreground Service**  
Inicia un servicio con **notificación persistente** (útil para tareas largas).

✅ **3) BroadcastReceiver (batería baja)**  
Recibe el broadcast del sistema `ACTION_BATTERY_LOW` y muestra un `Toast`.

✅ **4) Content Provider (Contactos)**  
Consulta el `ContentProvider` de contactos con `ContentResolver` (requiere permiso).

✅ **5) Intent implícito (Compartir texto)**  
Abre el *chooser* para compartir con apps instaladas.

---

## Guía de verificación (Checklist)

1. **Activity**
   - Presionar botón **“Abrir DetailActivity”**
   - Debe mostrarse: `ID recibido: 101`

2. **Foreground Service**
   - Presionar botón **“Iniciar Foreground Service”**
   - Debe aparecer una **notificación** indicando que el servicio está activo

3. **Detener Service**
   - Presionar botón **“Detener Service”**
   - La notificación debe desaparecer (o confirmarse con `Toast`)

4. **BroadcastReceiver**
   - En emulador, simular batería baja (opcional con ADB):
     ```bash
     adb shell dumpsys battery set level 10
     adb shell dumpsys battery set status 2
     ```
   - Debe mostrarse: `¡Batería baja! Ahorra energía.`

5. **Content Provider (Contactos)**
   - Presionar botón **“Leer 1 contacto”**
   - Aceptar permiso
   - Debe mostrar el nombre del primer contacto (si el emulador tiene contactos)

6. **Intent implícito**
   - Presionar botón **“Compartir texto”**
   - Debe aparecer la lista de apps para compartir

---


##  Permisos utilizados

- `READ_CONTACTS` → consulta del Content Provider de contactos  
- `FOREGROUND_SERVICE` / `FOREGROUND_SERVICE_DATA_SYNC` → ejecución del servicio en primer plano  
- `POST_NOTIFICATIONS` (Android 13+) → mostrar notificación del Foreground Service

> Nota: En Android 13+ puede requerirse permiso de notificaciones para ver la notificación del servicio.

---

## Estructura del proyecto (principal)

````

app/src/main/
├─ java/com/example/componentesandroiddemo/
│   ├─ MainActivity.java
│   ├─ DetailActivity.java
│   ├─ MyForegroundService.java
│   └─ BatteryReceiver.java
├─ res/layout/
│   ├─ activity_main.xml
│   └─ activity_detail.xml
└─ AndroidManifest.xml

```

---

## Requisitos

- Android Studio (versión reciente)
- JDK 11+
- Gradle (incluido por Android Studio)

---

## Licencia
Este proyecto se distribuye bajo la licencia **MIT**.  


## Autores

- **Jorge Ortiz Ceballos** – https://github.com/ocjorge  
- **Marelin Rosal** – https://github.com/marelinrosal  
- **Hiram Reyes** – https://github.com/HiramReyes1  

```

### Extra: 

[![Stars](https://img.shields.io/github/stars/ocjorge/ComponentesAndroidDemo?style=for-the-badge)](https://github.com/ocjorge/ComponentesAndroidDemo)
[![Forks](https://img.shields.io/github/forks/ocjorge/ComponentesAndroidDemo?style=for-the-badge)](https://github.com/ocjorge/ComponentesAndroidDemo)
[![Issues](https://img.shields.io/github/issues/ocjorge/ComponentesAndroidDemo?style=for-the-badge)](https://github.com/ocjorge/ComponentesAndroidDemo/issues)
[![Last Commit](https://img.shields.io/github/last-commit/ocjorge/ComponentesAndroidDemo?style=for-the-badge)](https://github.com/ocjorge/ComponentesAndroidDemo)


