








const progress = document.querySelector('.progress-done');

progress.style.width = progress.getAttribute('data-done') + '%';
progress.style.opacity = 1;




// Import the functions you need from the SDKs you need
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.6.4/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.6.4/firebase-analytics.js";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyAtbS6RcxKFRRsGlz5jdDk-Vw3YBVeU2Zg",
  authDomain: "step-up-aak3.firebaseapp.com",
  databaseURL: "https://step-up-aak3-default-rtdb.firebaseio.com",
  projectId: "step-up-aak3",
  storageBucket: "step-up-aak3.appspot.com",
  messagingSenderId: "120118040580",
  appId: "1:120118040580:web:dbf7a1d4d5e00e6381bd51",
  measurementId: "G-DBJZM0XR4Y"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);