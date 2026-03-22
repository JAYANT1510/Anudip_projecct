# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\user\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard/index.html

# Add any custom rules here that are specific to your project.
-keep class com.google.android.gms.location.** { *; }
-dontwarn com.google.android.gms.location.**