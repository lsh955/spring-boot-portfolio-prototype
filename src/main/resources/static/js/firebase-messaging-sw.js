// Import and configure the Firebase SDK
// These scripts are made available when the app is served or deployed on Firebase Hosting
// If you do not serve/host your project using Firebase Hosting see https://firebase.google.com/docs/web/setup
importScripts('https://www.gstatic.com/firebase/7.7.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebase/7.7.0/firebase-messaging.js');
importScripts('https://www.gstatic.com/firebase/init.js');

const messaging = firebase.messaging();

// [START initialize_firebase_in_sw]
// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here, other Firebase libraries
// are not available in the service worker.
//importScripts('https://www.gstatic.com/firebasejs/6.3.4/firebase-app.js');
//importScripts('https://www.gstatic.com/firebasejs/6.3.4/firebase-messaging.js');
// Initialize the Firebase app in the service worker by passing in the
// messagingSenderId.
// firebase.initializeApp({
//     'messagingSenderId': '838635500072'
// });
// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
//const messaging = firebase.messaging();
// [END initialize_firebase_in_sw]

/**
 * Here is is the code snippet to initialize Firebase Messaging in the Service
 * Worker when your app is not hosted on Firebase Hosting.
 // [START initialize_firebase_in_sw]
 // Give the service worker access to Firebase Messaging.
 // Note that you can only use Firebase Messaging here, other Firebase libraries
 // are not available in the service worker.
 importScripts('https://www.gstatic.com/firebasejs/6.3.4/firebase-app.js');
 importScripts('https://www.gstatic.com/firebasejs/6.3.4/firebase-messaging.js');
 // Initialize the Firebase app in the service worker by passing in the
 // messagingSenderId.
 firebase.initializeApp({
   'messagingSenderId': 'YOUR-SENDER-ID'
 });
 // Retrieve an instance of Firebase Messaging so that it can handle background
 // messages.
 const messaging = firebase.messaging();
 // [END initialize_firebase_in_sw]
 **/


// If you would like to customize notifications that are received in the
// background (Web app is closed or not in browser focus) then you should
// implement this optional method.
// [START background_handler]
messaging.setBackgroundMessageHandler(function (payload) {
    console.log('[firebase-messaging-sw.js] Received background message ', payload);
    // Customize notification here
    const notificationTitle = 'Background Message Title';
    const notificationOptions = {
        body: 'Background Message body.',
        icon: '/firebase-logo.png'
    };

    return self.registration.showNotification(notificationTitle,
        notificationOptions);
});
// [END background_handler]

// messaging.onMessage(function(payload){
//     console.log('onMessage: ', payload);
//     var title = "고라니 서비스";
//     var options = {
//         body: payload.notification.body
//     };
//
//     var notification = new Notification(title, options);
// });
