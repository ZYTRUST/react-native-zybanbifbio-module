package com.zybanbifbiomodule


import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager


class ZyBioBanbifReactNativeModulePackage : ReactPackage {
  /**
   * @param reactContext react application context that can be used to create modules
   * @return list of native modules to register with the newly created catalyst instance
   */
  override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
    return listOf(ZyBioBanbifReactNativeModuleModule(reactContext))
  }

  /**
   * @param reactContext
   * @return a list of view managers that should be registered with [UIManagerModule]
   */
  override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
    return emptyList()
  }
}
