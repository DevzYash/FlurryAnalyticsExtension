# Add any ProGuard configurations specific to this
# extension here.

-keep public class com.yash.flurryanalytics.FlurryAnalytics {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'com/yash/flurryanalytics/repack'
-flattenpackagehierarchy
-dontpreverify
