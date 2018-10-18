rem 生成数字签名
 keytool -genkey -keystore boogie.store -alias boogie -validity 36500
rem 导出证书
 keytool -export -keystore boogie.store -alias boogie -file boogie.cer
rem 打包文件
rem jar -c applets.jar ./*.class
rem jar cvfm applets2.jar -C ./org/ 
jar  cvf  applets.jar  org

rem 对包进行数字签名
jarsigner   -keystore   boogie.store   applets.jar   boogie