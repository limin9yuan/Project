rem ��������ǩ��
 keytool -genkey -keystore boogie.store -alias boogie -validity 36500
rem ����֤��
 keytool -export -keystore boogie.store -alias boogie -file boogie.cer
rem ����ļ�
rem jar -c applets.jar ./*.class
rem jar cvfm applets2.jar -C ./org/ 
jar  cvf  applets.jar  org

rem �԰���������ǩ��
jarsigner   -keystore   boogie.store   applets.jar   boogie