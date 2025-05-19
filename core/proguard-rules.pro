# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-keep class com.rifftyo.flixlist.core.data.MovieRepository { *; }

# KEEP all class in core
-keep class com.rifftyo.flixlist.core.** { *; }

# KEEP Hilt/Dagger Modules
-keep class dagger.** { *; }
-keep class javax.inject.** { *; }
-keep class com.rifftyo.flixlist.core.di.** { *; }

# KEEP Repository and UseCase
-keep interface com.rifftyo.flixlist.core.domain.repository.** { *; }
-keep class com.rifftyo.flixlist.core.domain.usecase.** { *; }

# KEEP Resource sealed class
-keep class com.rifftyo.flixlist.core.data.Resource { *; }
-keep class com.rifftyo.flixlist.core.data.Resource$* { *; }

# KEEP MovieRepository & Datasource
-keep class com.rifftyo.flixlist.core.data.MovieRepository { *; }
-keep class com.rifftyo.flixlist.core.data.source.local.** { *; }
-keep class com.rifftyo.flixlist.core.data.source.remote.** { *; }

# KEEP Room DAO
-keep class com.rifftyo.flixlist.core.data.source.local.room.** { *; }

# KEEP Retrofit API
-keep interface com.rifftyo.flixlist.core.data.source.remote.network.ApiService { *; }

# KEEP Utility
-keep class com.rifftyo.flixlist.core.utils.** { *; }

# KEEP Adapter
-keep class com.rifftyo.flixlist.core.ui.MovieAdapter { *; }

