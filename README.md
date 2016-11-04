To clone the project use this command: git clone https://github.com/mhammad2/DynamicClassLoading

This is an Android app that utilizes a dynamic class loading feature to download a code from a JAR file at runtime. 
The JAR file can be downloaded from a remote server or from SD card. 
This app assumes that the JAR file is stored in the SD card. 
It uses Java reflection to read the classes from the JAR file, instantiate an object, and call the methods inside the loaded class.

Please take a look at loadCode() method in edu.uci.seal.dynamic.MainActivity Activity to see the implementation. 
(https://github.com/mhammad2/DynamicClassLoading/blob/master/app/src/main/java/edu/uci/seal/dynamic/MainActivity.java)

The “dexHiddenBehavior.jar” file is pushed to “/mnt/shared/avd_nexus4_sdcard/” directory in a Genymotion emulator using adb push command:
adb push <file-source-local> <destination-path-remote>
You can use this command to push the jar file to an emulator or a device.

The JAR file can be found at https://github.com/mhammad2/DynamicCode_Java/tree/master/gen

To change the loaded code, please follow the instructions on https://github.com/mhammad2/DynamicCode_Java
