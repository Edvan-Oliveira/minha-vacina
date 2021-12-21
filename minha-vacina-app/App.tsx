import * as firebase from "firebase";
import React from 'react';
import { firebaseConfig } from './src/config/firebase';
import { MainNavigation } from './src/navigations/index';

firebase.initializeApp(firebaseConfig);

export default function App() {
 
  return ( <MainNavigation/> )
}
