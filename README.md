<h1>duitku-android-sdk 1.0.1</h1>


<b>Welcome to,</b> Duitku Android SDK registration page, Integrate this SDK to start transaction using duitku in your android application.

<div align="center">
    <img style="align:center;" src="https://github.com/duitkupg/duitku-android-sdk/blob/master/img/transactionflow.png" width="550px"</img> 
</div>



<h2>Installations</h2>

<h3>Server Merchant</h3>


```html
	Example,

	Base Url 		: https://merchant.com/api/
	Request Transaction 	: https://merchant.com/api/requestTransaction.php
	Check Transaction	: https://merchant.com/api/checkTransaction.php
	List Payment		: https://merchant.com/api/listPayment.php
```
Download files sample  <a href="https://github.com/duitkupg/duitku-android-sdk/blob/master/Webserver/requestTransaction.php" download="requestTransaction.php">requestTransaction.php</a>&nbsp;&nbsp;<a href="https://github.com/duitkupg/duitku-android-sdk/blob/master/Webserver/checkTransaction.php" download="checkTransaction.php">checkTransaction.php</a>&nbsp;&nbsp;<a href="https://github.com/duitkupg/duitku-android-sdk/blob/master/Webserver/listpayment.php" download="listpayment.php">listPayment.php</a>


<h3>Recommended specifications for your application development</h3>


```html
	Development Tool       		 : Android Studio 3.4 > 
	Gradle Version         		 : 3.4.1 > 
	Target SDK Version     		 : API Level 29 
	Minimum SDK Version   		 : API Level 21 (Lollipop) 
```








<h3>Installation</h3>

<h4>Maven</h4>

```html
	<dependency>
	  <groupId>com.duitku</groupId>
	  <artifactId>duitku-android-sdk</artifactId>
	  <version>1.0.0</version>
	  <type>pom</type>
	</dependency>
```
<h4>ivy</h4>

```html
	<dependency org='com.duitku' name='duitku-android-sdk' rev='1.0.1'>
	  <artifact name='duitku-android-sdk' ext='pom' ></artifact>
	</dependency>
```

For more information, visit https://bintray.com/duitku/duitku-android-sdk/duitku-android-sdk

<h4>Build.gradle(Module:app)</h4>

```html
	implementation 'com.duitku:duitku-android-sdk:1.0.1'
```

<h3>Sample Project</h3>



```html
	git clone https://github.com/duitkupg/sample-project.git
```

Visit <a target="_Blank" href="https://github.com/duitkupg/sample-project" >Github Sample Project</a>


<h3>Full Step Docs </h3>
Download  <a target="_blank" href="https://docs.duitku.com/mobile/android/en/#introduction">Duitku Docs</a>

