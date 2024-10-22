package com.zybanbifbiomodule.bean

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap

class ScreenText {
  var titulo: String = ""
  var descripcion: String = ""
  var textoBoton: String = ""


  constructor(textoBoton: String, descripcion: String, titulo: String) {
    this.textoBoton = textoBoton
    this.descripcion = descripcion
    this.titulo = titulo
  }

  // Método para convertir a WritableMap
  fun toWritableMap(): WritableMap {
    val map = Arguments.createMap()
    map.putString("titulo", titulo)
    map.putString("descripcion", descripcion)
    map.putString("textoBoton", textoBoton)
    return map
  }

}
