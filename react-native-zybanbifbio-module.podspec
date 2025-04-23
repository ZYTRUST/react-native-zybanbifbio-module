require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-zybanbifbio-module"
  s.version      = package["version"]
  s.summary      = package["description"]

  s.license      = package["license"]
  s.authors      = { 'Iván Cáceres' => 'ztmobile@zytrust.com' }
  s.homepage     = 'https://www.zytrust.com'
  s.ios.deployment_target = '13.0'

  s.source       = { :git => "https://github.com/ZYTRUST/react-native-zybanbifbio-module.git", :tag => "#{s.version}" }
  s.source_files  = "ios/*.{h,m,mm}"

  s.dependency 'zy_banbif_bio_lib_ios', '1.3.0'
  s.dependency 'React'

end


