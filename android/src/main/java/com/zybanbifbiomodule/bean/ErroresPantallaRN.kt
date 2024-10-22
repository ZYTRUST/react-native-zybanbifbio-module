package com.zybanbifbiomodule.bean

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap

class ErroresPantallaRN {
  lateinit var reintentar: ScreenText
  lateinit var limite_tiempo_alcanzado: ScreenText
  lateinit var limite_intentos_alcanzado : ScreenText

  constructor(
    reintentar: ScreenText,
    limite_tiempo_alcanzado: ScreenText,
    limite_intentos_alcanzado: ScreenText
  ) {
    this.reintentar = reintentar
    this.limite_tiempo_alcanzado = limite_tiempo_alcanzado
    this.limite_intentos_alcanzado = limite_intentos_alcanzado
  }

  // Método para convertir a WritableMap
  fun toWritableMap(): WritableMap {
    val map = Arguments.createMap()
    map.putMap("reintentar", reintentar.toWritableMap())
    map.putMap("limite_tiempo_alcanzado", limite_tiempo_alcanzado.toWritableMap())
    map.putMap("limite_intentos_alcanzado", limite_intentos_alcanzado.toWritableMap())
    return map
  }

}
