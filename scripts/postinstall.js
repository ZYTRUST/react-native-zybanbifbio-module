// scripts/postinstall.js

const { execSync } = require('child_process');

const repos = [
  {
    name: 'zy_banbif_bio_lib_ios',
    url: 'https://ZYTRUST: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@github.com/ZYTRUST/zy_banbif_bio_lib_ios.git'
  },
  {
    name: 'zy_banbif_lib_ui_ios',
    url: 'https://ZYTRUST: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@github.com/ZYTRUST/zy_banbif_lib_ui_ios.git'
  },
  {
    name: 'zy_lib_idemia_face_ios',
    url: 'https://ZYTRUST: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@github.com/ZYTRUST/zy_lib_idemia_face_ios.git'
  },
  {
    name: 'BiometricSDKAlgorithmPlugin_F6_0_IDD80',
    url: 'https://ZYTRUST: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@github.com/ZYTRUST/BiometricSDKAlgorithmPlugin_F6_0_IDD80.git'
  },
  {
    name: 'BiometricSDKFaceCapturePluginNormal',
    url: 'https://ZYTRUST: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@github.com/ZYTRUST/BiometricSDKFaceCapturePluginNormal.git'
  },
  {
    name: 'zyBiometricSDK',
    url: 'https://ZYTRUST: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@github.com/ZYTRUST/BiometricSDK.git'
  },
  {
    name: 'zy_lib_regula_ocr_ios',
    url: 'https://ZYTRUST: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@github.com/ZYTRUST/zy_lib_regula_ocr_ios.git'
  },
  {
    name: 'zy_lib_zytrust_pdv_ios',
    url: 'https://ZYTRUST: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@github.com/ZYTRUST/zy_lib_zytrust_pdv_ios.git'
  }
];

console.log('📦 Ejecutando postinstall: Añadiendo repositorios CocoaPods...');

repos.forEach(repo => {
  try {
    console.log(`🔗 Añadiendo ${repo.name}...`);
    execSync(`pod repo add ${repo.name} ${repo.url}`, { stdio: 'inherit' });
  } catch (err) {
    console.warn(`⚠️ Ya existe o error añadiendo ${repo.name}: ${err.message}`);
  }
});

console.log('✅ Repositorios añadidos.');
