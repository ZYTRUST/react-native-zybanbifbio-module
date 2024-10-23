#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <zy_banbif_bio_lib_ios/zy_banbif_bio_lib_ios-Swift.h>

#import "ZyBanbifBioRn.h" // Here put the name of your module

@implementation  ZyBanbifBioRn

RCT_EXPORT_MODULE();


RCT_EXPORT_METHOD(validacionFacialOcr:(NSDictionary *)opcional
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
  
  dispatch_async(dispatch_get_main_queue(), ^{
    @try{
      UIViewController *ctrl = [[[[UIApplication sharedApplication] delegate] window] rootViewController];

      ReactRequest *request = [ReactRequest new];
      request.token = opcional[@"accessToken"] ;
      request.bioTiDoc = opcional[@"tiDocumento"];
      request.bioNuDoc = opcional[@"nuDocumento"];
      request.bioPais = opcional[@"bioPais"];
      request.bioOperacion = opcional[@"tiOperacion"];
      request.url = opcional[@"urlSource"];
      
      
      NSDictionary *stepperDict = opcional[@"stepper"];
      if (stepperDict && [stepperDict isKindOfClass:[NSDictionary class]]) {
        Stepper *st = [Stepper new];
        st.nuPasos = [stepperDict[@"nuPasos"] intValue];
        st.pasoActual = [stepperDict[@"pasoActual"] intValue];
        request.stepper = st;
      }
      
      NSDictionary *erroresDict = opcional[@"errores"];
      NSDictionary *reintentarDict = erroresDict[@"reintentar"];
      NSDictionary *limite_tiempo_alcanzadoDict = erroresDict[@"limite_tiempo_alcanzado"];
      NSDictionary *limite_intentos_alcanzadoDict = erroresDict[@"limite_intentos_alcanzado"];

      VisualScreenError *error1 = [[VisualScreenError alloc] initWithTitulo:reintentarDict[@"titulo"]
                                                                  descripcion:reintentarDict[@"descripcion"]
                                                                  textBoton:reintentarDict[@"textoBoton"]
                                                                  screenEnum:ScreenEnumREINTENTAR];

      VisualScreenError *error2 = [[VisualScreenError alloc] initWithTitulo:limite_tiempo_alcanzadoDict[@"titulo"]
                                                                descripcion:limite_tiempo_alcanzadoDict[@"descripcion"]
                                                                  textBoton:limite_tiempo_alcanzadoDict[@"textoBoton"]
                                                                  screenEnum:ScreenEnumLIMITE_TIEMPO_ALZANZADO];

      VisualScreenError *error3 = [[VisualScreenError alloc] initWithTitulo:limite_intentos_alcanzadoDict[@"titulo"]
                                                                descripcion:limite_intentos_alcanzadoDict[@"descripcion"]
                                                                  textBoton:limite_intentos_alcanzadoDict[@"textoBoton"]
                                                                  screenEnum:ScreenEnumLIMITE_INTENTOS_ALCANZADO];

      NSArray<VisualScreenError *> *errores = @[error1, error2, error3];
      request.errores = errores;

      ZyReactComp *biomatch = [[ZyReactComp alloc] initOnView:ctrl];
        
      [biomatch reactValidacionFacial:request
                      completion:^(ReactResponse * _Nonnull response) {
          
          dispatch_async(dispatch_get_main_queue(), ^{
            resolve(@{ @"coError": [NSString stringWithString:(response.coError.length != 0) ? response.coError : @""],
                       @"deError": [NSString stringWithString:(response.deError.length != 0) ? response.deError : @"" ],
                       @"coErrorButton": [NSString stringWithString:(response.coErrorButton.length != 0) ? response.coErrorButton : @"" ],
                       @"deErrorButton": [NSString stringWithString:(response.deErrorButton.length != 0) ? response.deErrorButton : @"" ],
                       @"idSolicitud": [NSString stringWithString:(response.bmoNuSolicitud.length != 0) ? response.bmoNuSolicitud : @"" ]
                       
                    });

          });
          
      }];

    }
    @catch(NSException *exception){
      reject(@"get_error",exception.reason, nil);
    }
  });
}


@end


