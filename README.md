# react-native-zybanbifbio-module

Libreria la cual es encargada de verificación biométrica de personas peruanas.
Esta libreria esta diseñada especificamente para le empresa 11.


## Installation

```sh
npm install react-native-zybanbifbio-module
```

## Usage

```js
 import {  NativeModules } from 'react-native';

// ...

    NativeModules.ZyBanbifBioRn.validacionFacialOcr(
        "1", // tipo Documento : DNI: 1
        "0000000", // Numero de documento
        "abcdef123456", // token
        "PE", // Pais
        "FULL", // tipo operacion "FULL" o "FACIAL"
        "POC" //URL - POC , DEVX , SIGN
    )
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
