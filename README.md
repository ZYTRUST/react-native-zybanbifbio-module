# react-native-zybanbifbio-module

Libreria la cual es encargada de verificación biométrica de personas peruanas.
Esta libreria esta diseñada especificamente para le empresa 11 poc. 

## Anotaciones

### iOS: version para iOS fue compilada con XCODE 15.4, es decir que el preoyecto debe utilizar XCODE 15.4 o superior , ademas el tarjet minimo es ios 13.0
### Android: version minima de android es 22, version compilada con compileSDK 34, se recomienda usar compileSDK 34 o superior

## NOTAS:
```
La versión >0.5.3 versión productiva para Emps11
 
```


## Installation

```sh
npm install react-native-zybanbifbio-module
```

## Usage

### MÉTODO
```js
 import {  NativeModules } from 'react-native';

// ...

  const opciones = {
    tiDocumento: "1", // tipo Documento : DNI: 1 nuDocumento: documentNumber, // Numero de documento
    nuDocumento: "00112233", // Número de documento
    accessToken: "abcdfghi12345",  // Access token generado por ambiente
    bioPais: "PE", //Pais de verificacion (siempre PE)
    tiOperacion: "FULL", //Operacion puede ser FULL o FACIAL
    urlSource: "SIGN", // Ambiente a la conexion DEV2 , POC , DEVX , SIGN (PRODUCCION)
    stepper: {
      nuPasos: 3, // cuando nuPasos es 0 desaparece || si número de pasos es 0 los steppers se ocultan
      pasoActual: 2 // paso en el cual el circulo señalará 
    },
    errores: {
      reintentar: {
        titulo: "Reintentar",
        descripcion: "Ha ocurrido un error. Puedes intentar nuevamente presionando el botón a continuación.", 
        textoBoton: "Reintentar"
      },
      limite_tiempo_alcanzado: {
        titulo: "Límite de tiempo alcanzado",
        descripcion: "El tiempo máximo para realizar esta operación ha expirado. Por favor, intenta nuevamente.",
        textoBoton: "Entendido"
      },
      limite_intentos_alcanzado: {
        titulo: "Límite de intentos alcanzado",
        descripcion: "Has alcanzado el número máximo de intentos permitidos. Por favor, inténtalo más tarde.",
        textoBoton: "Entendido"
      }
    }
  };
    NativeModules.ZyBanbifBioRn.validacionFacialOcr(opciones)
    .then(result => {
    })

//..

//para mas información revisar el documento de especificación.
```

## Contributing

- Elaborado por Iván A. Cáceres Z.

- Compañia ZyTrust S.A. Perú


## License

ZyTrust S.A. Perú


## Contact

- Webpage: https://www.zytrust.com/

- Oficina: Av. Arenales 1912, oficina 1102 Lince. Lima, Perú.

- Teléfono: +51 1 321 4444 || (+51) 974 260 111

- Email: info@zytrust.com || support@zytrust.com || ztmobile@zytrust.com


---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
