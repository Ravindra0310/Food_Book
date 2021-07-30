# PotLuck
<p>A potluck is a communal gathering where each guest or group contributes a different, often homemade, dish of food to be shared. Other names for a “potluck” include potluck dinner, pitch-in, shared lunch, spread, faith supper, carry-in dinner, covered-dish-supper, fuddle, Jacob’s Join, and fellowship meal.</p>
<hr>
<h3>Used Technologies</h3>
<ul>
  <li>Java</li>
  <li>Kotlin</li>
  <li>Firebase</li>
  <li>Recyclerview</li>
  <li>Swiperefreshlayout</li>
  <li>circleimageview</li>	  
</ul>  
<hr>
<h3>Features</h3>
<ul>
  <li>The idea is all about offering food to the needy once, from those who have excess food. Where guests like personal, hotels, restaurants can either sell food at a cheaper price or donate. Whereas needy can search for food on the app. So they can buy or get food for free.
  </li>
    <li>The app will provide certain details like how fresh the food is, its for sale or donation, pictures of food, time location of guest who wanted to share their food. So nearby needy can go direct to the location and grab the packet of food.</li>
</ul>

<hr>
<h3>Screenshort and description</h3>
<img src="https://miro.medium.com/max/700/1*WWPNXH7J23f4FXL5ZJy-CQ.jpeg" width="500" height="333">

<hr>
<h3>Dependencies</h3>
<hr>

    //firebase
    implementation platform('com.google.firebase:firebase-bom:28.1.0')
    // Declare the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation 'com.google.firebase:firebase-auth-ktx'

    implementation 'com.google.firebase:firebase-auth:21.0.1'

    implementation 'com.firebaseui:firebase-ui-database:7.2.0'
    //dimens
    implementation 'com.intuit.sdp:sdp-android:1.0.6'
    
    //camera
    implementation "androidx.camera:camera-core:${camerax_version}"
    implementation "androidx.camera:camera-camera2:${camerax_version}"
    implementation "androidx.camera:camera-lifecycle:${camerax_version}"
    implementation "androidx.camera:camera-view:1.0.0-alpha25"
    implementation "androidx.camera:camera-extensions:1.0.0-alpha25"
    implementation platform('com.google.firebase:firebase-bom:28.1.0')
    
     //swipe to refresh and recycler view
    implementation "androidx.recyclerview:recyclerview:1.2.1"
    implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
