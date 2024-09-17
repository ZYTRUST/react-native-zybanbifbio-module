#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <zy_banbif_bio_lib_ios/zy_banbif_bio_lib_ios-Swift.h>

#import "ZyBanbifBioRn.h" // Here put the name of your module

//@interface RCT_EXTERN_MODULE(ZyBanbifBioRn, NSObject)


/*RCT_EXTERN_METHOD(validacionFacialOcr:
 (NSString *) bioTiDoc
 bioNuDoc: (NSString *)bioNuDoc
 bioPais: (NSString *)bioPais
 bmoNuOperacionEmps: (NSString *)bmoNuOperacionEmps
 isDialogActivated: (BOOL *)isDialogActivated
 token: (NSString *)token)*/
@implementation  ZyBanbifBioRn

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(validacionFacialOcr:
                  (NSString *) tiDocumento
                  nuDocumento: (NSString *)bioNuDoc
                  accessToken: (NSString *)token
                  bioPais: (NSString *)bioPais
                  tiOperacion: (NSString *)tiOperacion
                  urlSource: (NSString *)urlSource

                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
  
  dispatch_async(dispatch_get_main_queue(), ^{
    @try{
      UIViewController *ctrl = [[[[UIApplication sharedApplication] delegate] window] rootViewController];

      ReactRequest *request = [ReactRequest new];
      request.token = token ;
      request.bioTiDoc = tiDocumento;
      request.bioNuDoc = bioNuDoc;
      request.bioPais = bioPais;
      request.bioOperacion = tiOperacion;
      request.url = urlSource;

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


