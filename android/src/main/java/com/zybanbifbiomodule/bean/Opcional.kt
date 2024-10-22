package com.zybanbifbiomodule.bean

import androidx.annotation.Keep

@Keep
internal class Opcional {
  var tiDocumento: String = ""
  var nuDocumento:  String = ""
  var accessToken:  String = ""
  var bioPais:  String = ""
  var tiOperacion:  String = ""
  var urlSource:  String = ""
  lateinit var stepper: StepperRN
  lateinit var errores: ErroresPantallaRN
}

