# TimeNavigator
Cute library to implement a simple time navigation view. *Works from Android API 14 (ICS) and above*.

<a href="https://play.google.com/store/apps/details?id=com.claudiodegio.timenavigator.sample">
  <img alt="Get it on Google Play"
       src="https://developer.android.com/images/brand/en_generic_rgb_wo_60.png" />
</a>

![sample](https://raw.githubusercontent.com/claudiodegio/TimeNavigator/master/screen/screen_001.png)
![sample](https://raw.githubusercontent.com/claudiodegio/TimeNavigator/master/screen/screen_002.png)
![sample](https://raw.githubusercontent.com/claudiodegio/TimeNavigator/master/screen/screen_003.png)


# Usage
**Add the dependencies to your gradle file:**
```javascript
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
dependencies {
        compile 'com.github.claudiodegio:TimeNavigator:1.0.0'
}
```
**Add TimeNavigator to your layout file along with the Toolbar** 

```xml
<android.support.design.widget.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:theme="@style/AppTheme.AppBarOverlay">

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        app:popupTheme="@style/AppTheme.PopupOverlay" />

    <com.claudiodegio.timenavigator.TimeNavigator
        android:id="@+id/tn"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        app:tnInterval="EveryThing"/>

</android.support.design.widget.AppBarLayout>
```

**Add TimeNavigator to your layout file along with the Toolbar with styles** 

```xml
<android.support.design.widget.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:theme="@style/AppTheme.AppBarOverlay">

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        app:popupTheme="@style/AppTheme.PopupOverlay" />

    <com.claudiodegio.timenavigator.TimeNavigator
        android:id="@+id/tn"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        app:tnColor="@color/colorAccent"
        app:tnTextSize="14pt"
        app:tnInterval="EveryThing"/>

</android.support.design.widget.AppBarLayout>
```
# Help me
Pull requests are more than welcome, help me and others improve this awesome library.

# License
	Copyright 2017 Claudio Degioanni

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.