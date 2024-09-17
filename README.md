# react-native-zybanbifbio-module

Libreria Biometrica BB --

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
                                    //this.setState({ coError: result.coError });
                                    //this.setState({ deError: result.deError });
                                    //this.setState({ coErrorButton: result.coErrorButton });
                                    //this.setState({ deErrorButton: result.deErrorButton });
                                    //this.setState({ idSolicitud: result.idSolicitud });
                                    
                       })


```


## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
