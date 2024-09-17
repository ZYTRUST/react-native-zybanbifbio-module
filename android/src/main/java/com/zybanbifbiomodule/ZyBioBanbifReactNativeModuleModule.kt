package com.zybanbifbiomodule

import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.WritableNativeMap
import com.zy.banbif.android.lib.sdk.validacionfacial.api.IZyApiCapturaFacial
import com.zy.banbif.android.lib.sdk.validacionfacial.api.ZyApiCapturaFacial
import com.zy.banbif.android.lib.sdk.validacionfacial.api.ZyRequest
import com.zy.banbif.android.lib.sdk.validacionfacial.api.ZyResponse


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
  fun validacionFacialOcr(tiDocumento: String,
                          nuDocumento: String,
                          accessToken: String,
                          bioPais: String,
                          tiOperacion: String,
                          urlSource: String, promise: Promise) {
    val zyRequestApi: ZyRequest = ZyRequest()
    zyRequestApi.bioTiDoc = tiDocumento
    zyRequestApi.bioNuDoc = nuDocumento
    zyRequestApi.token = accessToken
    zyRequestApi.bioPais = bioPais
    zyRequestApi.bioOperacion = tiOperacion
    zyRequestApi.url = urlSource

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
    iZyApiCapturaFacial.zyCapturaFacial(zyRequest)

  }

  companion object {
    private const val TAG = "ZyTFacialModule"
  }
}