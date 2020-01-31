<h1>duitku-sdk-android</h1>


<b>Welcome to,</b> Duitku Android SDK registration page, Integrate this SDK to start transaction using duitku in your android application.


<h2>Installations</h2>

<h3>Server Merchant</h3>


```html
	Merchant Server Base URL  : example https://merchant.com/api/
	Transaction Request       : <a href="https://github.com/duitkupg/duitku-android-sdk/blob/master/Webserver/requestTransaction.php" > Request </a>
	Transaction Check         : https://github.com/duitkupg/duitku-android-sdk/blob/master/Webserver/checkTransaction.php
	List Payment       	  : https://github.com/duitkupg/duitku-android-sdk/blob/master/Webserver/listPayment.php
```



<h3>Recommended specifications for your application development</h3>


```html
	Development Tool       		 : Android Studio 3.4 > 
	Gradle Version         		 : 3.4.1 > 
	Target SDK Version     		 : API Level 29 
	Minimum SDK Version   		 : API Level 21 (Lollipop) 
```





How to try example
Visit and try the app module to see an example of how the SDK works.



<h3>Installation</h3>

<h4>Maven</h4>

```html
	<repositories> 
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

```html
	<dependency>
	    <groupId>com.github.duitkupg</groupId>
	    <artifactId>DuitkuSDK</artifactId>
	    <version>1.0</version>
	</dependency>
```

<h4>Build.gradle(Module:app)</h4>

```html
	implementation 'com.duitku:sdk-android:1.0'
```

<h4>Build.gradle</h4>

```html
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```


link ..



<h3>Full step Installation </h3>
link ..

