package com.zybanbifbiomodule.bean

import androidx.annotation.Keep
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap

@Keep
class StepperRN  {
  var nuPasos: Int = 4
  var pasoActual: Int = 2

  constructor(var1: Int, var2: Int) {
    this.nuPasos = var1
    this.pasoActual = var2
  }

  // Método para convertir a WritableMap
  fun toWritableMap(): WritableMap {
    val map = Arguments.createMap()
    map.putInt("nuPasos", nuPasos)
    map.putInt("pasoActual", pasoActual)
    return map
  }
}
