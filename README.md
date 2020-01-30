<h1>duitku-sdk-android</h1>
<b>Welcome to,</b> Duitku Android SDK registration page, Integrate this SDK to start transaction using duitku in your android aplication.

<h2>Installations</h2>
<hr>
<h3>Server Merchant</h3>

<label>Merchant Server Base URL  :</label><br/>
<label>Transaction Request       :</label><br/>
<label>Transaction Check         :</label><br/>

<h3>Recommended specifications for your application development</h3>

Development Tool          :
Gradle Version            :
Target SDK Version        :
Minimum SDK Version       :



How to try example
Visit and try the app module to see an example of how the SDK works.

Install android studio.
Clone repository.
Get your own API Key.
Change line with your own API Key.
PUBLISHABLE_KEY="xnd_public_development_YOURAPIKEY"
*Replace YOURAPIKEY with your own API Key.

Installation
Maven:

<dependency>
  <groupId>com.xendit</groupId>
  <artifactId>xendit-android</artifactId>
  <version>2.2.0</version>
  <type>pom</type>
</dependency>
Gradle:

compile 'com.xendit:xendit-android:2.2.0'
Ivy:

<dependency org='com.xendit' name='xendit-android' rev='1.1.0'>
  <artifact name='xendit-android' ext='pom' ></artifact>
</dependency>
For more information, visit https://bintray.com/xendit/android/xendit-sdk-android

Initializing Xendit
Xendit xendit = new Xendit(getApplicationContext(), "xnd_public_development_O4uGfOR3gbOunJU4frcaHmLCYNLy8oQuknDm+R1r9G3S/b2lBQR+gQ==");
Creating a single-use token
Card card = new Card("4000000000000002", "12", "2017", "123");

xendit.createSingleUseToken(card, 75000, true, new TokenCallback() {
    @Override
    public void onSuccess(Token token) {
        // Handle successful tokenization
        System.out.println("Token ID: " + token.getId());
    }

    @Override
    public void onError(XenditError xenditError) {
        // Handle error
    }
});
createSingleUseToken accept 4 parameters: Card object, amount, an optional shouldAuthenticate boolean, and a TokenCallback. shouldAuthenticate will be set to true if you don't pass this value.

Creating a multiple-use token
Card card = new Card("4000000000000002", "12", "2017", "123");

xendit.createMultipleUseToken(card, new TokenCallback() {
    @Override
    public void onSuccess(Token token) {
        // Handle successful tokenization
        System.out.println("Token ID: " + token.getId());
    }

    @Override
    public void onError(XenditError xenditError) {
        // Handle error
    }
});
Creating a 3ds authentication
String tokenId = "sample-token-id";
int amount = 50000;

xendit.createAuthentication(tokenId, amount, new AuthenticationCallback() {
    @Override
    public void onSuccess(Authentication authentication) {
        // Handle successful authentication
        System.out.println("Authentication ID: " + authentication.getId());
    }

    @Override
    public void onError(XenditError xenditError) {
        // Handle error
    }
});
Creating a charge
When you're ready to charge a card, use the private key on your backend to call the charge endpoint. See our API reference at https://xendit.github.io/apireference/#create-charge
