import Vue from 'vue'
import VueRouter from 'vue-router'
import Homepage from '../components/Homepage/Homepage.vue'
import Detailpaeg from '../components/Detailpage/Detailpage.vue'
import Orderpage from '../components/Orderpage/Orderpage.vue'
import Payment from '../components/Paymentpage/PaymentSlip.vue'

import AdminPanel from "@/views/AdminPanel";
import AdminOrderpage from '../components/Adminpage/Adminorderpage.vue'
import AdminPaymentpage from '../components/Adminpage/Adminpaymentpage.vue'
import AdminFoodDrinkPage from '../components/Adminpage/AdminFoodDrinkPage.vue'
import SignIn from "@/components/Adminpage/SignIn";

import firebase from 'firebase/compat/app'
import {firebaseConfig} from "@/config/firebaseConfig";
import 'firebase/compat/auth'

firebase.initializeApp(firebaseConfig)
Vue.use(VueRouter)

const routes = [
  {
    path: '/Homepage',
    name: 'homepage',
    component: Homepage
  },
  {
    path: '/Detailpage',
    name: 'detailpage',
    component: Detailpaeg
  },
  {
    path: '/Orderpage',
    name: 'orderpage',
    component: Orderpage
  },
  {
    path: '/Paymentpage',
    name: 'paymentpage',
    component: Payment
  },
  {
    path: '/admin',
    component: AdminPanel,
    children: [
        { path: 'order', component: AdminOrderpage },
      { path: 'payment', component: AdminPaymentpage},
      { path: 'fooddrink', component: AdminFoodDrinkPage}
    ],
    beforeEnter: (to, from, next) => {
      const checkAuthentication = () => {
        return new Promise((resolve, reject) => {
          firebase.auth().onAuthStateChanged(user => {
            if (user) {
              resolve(true);
            } else {
              resolve(false);
            }
          });
        });
      };
      checkAuthentication().then(authenticated => {
        if (authenticated) {
          next();
        } else {
          next({ path: '/admin/signin' });
        }
      });
    }
  },
  {
    path: '/admin/signin',
    component: SignIn,
    name: 'signin'
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router