package com.zybanbifbiomodule

import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.WritableNativeMap
import com.google.gson.Gson
import com.zy.banbif.android.lib.sdk.validacionfacial.api.IZyApiCapturaFacial
import com.zy.banbif.android.lib.sdk.validacionfacial.api.ZyApiCapturaFacial
import com.zy.banbif.android.lib.sdk.validacionfacial.api.ZyRequest
import com.zy.banbif.android.lib.sdk.validacionfacial.api.ZyResponse
import com.zy.banbif.android.lib.sdk.validacionfacial.api.bean.visual.ScreenEnum
import com.zy.banbif.android.lib.sdk.validacionfacial.api.bean.visual.Stepper
import com.zy.banbif.android.lib.sdk.validacionfacial.api.bean.visual.VisualScreenError


class ZyBioBanbifReactNativeModuleModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {
  /**
   * @return the name of this module. This will be the name used to `require()` this module
   * from javascript.
   */
  override fun getName(): String {
    return "ZyBanbifBioRn"
  }

  @ReactMethod
  fun validacionFacialOcr(opcional: ReadableMap,
                          promise: Promise) {
    val gson = Gson()
    val opcionalJson = gson.toJson(opcional.toHashMap())
    val opcionalData = gson.fromJson(opcionalJson, com.zybanbifbiomodule.bean.Opcional::class.java)
    val zyRequestApi: ZyRequest = ZyRequest()

    zyRequestApi.bioTiDoc = opcionalData.tiDocumento
    zyRequestApi.bioNuDoc = opcionalData.nuDocumento
    zyRequestApi.token = opcionalData.accessToken
    zyRequestApi.bioPais = opcionalData.bioPais
    zyRequestApi.bioOperacion = opcionalData.tiOperacion
    zyRequestApi.url = opcionalData.urlSource
    zyRequestApi.stepper = Stepper(opcionalData.stepper.nuPasos,opcionalData.stepper.pasoActual)

    val vsList: MutableList<VisualScreenError> = ArrayList()
    vsList.add(
      VisualScreenError(
        opcionalData.errores.reintentar.titulo,
        opcionalData.errores.reintentar.descripcion,
        opcionalData.errores.reintentar.textoBoton,
        ScreenEnum.REINTENTAR
      )
    )
    vsList.add(
      VisualScreenError(
        opcionalData.errores.limite_tiempo_alcanzado.titulo,
        opcionalData.errores.limite_tiempo_alcanzado.descripcion,
        opcionalData.errores.limite_tiempo_alcanzado.textoBoton,
        ScreenEnum.LIMITE_TIEMPO_ALZANZADO
      )
    )
    vsList.add(
      VisualScreenError(
        opcionalData.errores.limite_intentos_alcanzado.titulo,
        opcionalData.errores.limite_intentos_alcanzado.descripcion,
        opcionalData.errores.limite_intentos_alcanzado.textoBoton,
        ScreenEnum.LIMITE_INTENTOS_ALCANZADO
      )
    )

    zyRequestApi.errores = vsList

    Log.i(TAG, "calling validacionFacialOcr")
    Log.i(TAG, "nuDocumento:" + zyRequestApi.bioTiDoc)
    Log.i(TAG, "tiDocumento:" + zyRequestApi.bioNuDoc)
    verifyBanbif(zyRequestApi, promise)
  }

  protected fun verifyBanbif(zyRequestApi: ZyRequest?, promise: Promise) {

    val iZyApiCapturaFacial: IZyApiCapturaFacial =
      ZyApiCapturaFacial(getCurrentActivity(), object : IZyApiCapturaFacial.ICallback {
        override fun onStart() {
          Log.i(TAG, "onStart")
          //openDialog("Porfavor espere...");
        }

        override fun onComplete() {
          Log.i(TAG, "onComplete")
          //closeDialog();
        }

        override fun onSuccess(zyResponse: ZyResponse) {
          Log.i(TAG, "onSuccess")
          Log.i(TAG, "=====>>> zyResponse: $zyResponse")
          val result: WritableMap = WritableNativeMap().apply {
            putString("coError", if (zyResponse.coError.isNotEmpty()) zyResponse.coError else "")
            putString("deError", if (zyResponse.deError.isNotEmpty()) zyResponse.deError else "")
            putString("coErrorButton", if (zyResponse.coErrorButton.isNotEmpty()) zyResponse.coErrorButton else "")
            putString("deErrorButton", if (zyResponse.deErrorButton.isNotEmpty()) zyResponse.deErrorButton else "")
            putString("idSolicitud", if (zyResponse.bmoNuSolicitud.isNotEmpty()) zyResponse.bmoNuSolicitud else "")
          }

          //successCallBack.invoke(result)
          promise.resolve(result)

        }

        override fun onError(zyError: ZyResponse) {
          Log.i(TAG, "OnError")
          Log.i(TAG, "=====>>> zyError: $zyError")

          val result: WritableMap = WritableNativeMap().apply {
            putString("coError", if (zyError.coError.isNotEmpty()) zyError.coError else "")
            putString("deError", if (zyError.deError.isNotEmpty()) zyError.deError else "")
            putString("coErrorButton", zyError.coErrorButton?.takeIf { it.isNotEmpty() } ?: "")
            putString("deErrorButton", zyError.deErrorButton?.takeIf { it.isNotEmpty() } ?: "")
            putString("idSolicitud", if (zyError.bmoNuSolicitud.isNotEmpty()) zyError.bmoNuSolicitud else "")

          }
          //errorCallBack.invoke(result)
          promise.resolve(result)

          //promise.reject("ERROR_CODE", result)

        }

        override fun onRetries(zyError: ZyResponse) {
          Log.i(TAG, "onRetries")
          Log.i(TAG,"coError: " + zyError.coError + " , " + "deError: " + zyError.deError + " , " + "bmoNuSolicitud " + zyError.bmoNuSolicitud
          )
        }
      })

    val zyRequest = ZyRequest()
    zyRequest.bioTiDoc = zyRequestApi?.bioTiDoc ?: ""
    zyRequest.bioNuDoc = zyRequestApi?.bioNuDoc  ?: ""
    zyRequest.bmoNuOperacionEmps = "BANBIF-ANDROID"

    zyRequest.token =  zyRequestApi?.token ?: ""
    zyRequest.bioPais = zyRequestApi?.bioPais ?: "PE"
    zyRequest.bioOperacion = zyRequestApi?.bioOperacion ?: "FULL"
    zyRequest.isDialogActivated = true
    zyRequest.url = zyRequestApi?.url ?: "POC"
    zyRequest.stepper = zyRequestApi?.stepper
    zyRequest.errores = zyRequestApi?.errores

    iZyApiCapturaFacial.zyCapturaFacial(zyRequest)

  }

  companion object {
    private const val TAG = "ZyTFacialModule"
  }
}
