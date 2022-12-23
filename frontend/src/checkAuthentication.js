import firebase from 'firebase/compat/app'
import {firebaseConfig} from "@/config/firebaseConfig";
import 'firebase/compat/auth'

firebase.initializeApp(firebaseConfig)

export default {
    methods: {
        async checkAuthentication() {
            try {
                const user = await firebase.auth().currentUser;
                return user !== null;
            } catch (error) {
                console.error(error);
                return false;
            }
        }
    }
}