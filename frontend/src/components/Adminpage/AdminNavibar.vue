<template>
    <b-navbar toggleable type="dark" variant="dark">
      <b-navbar-brand href="/admin/order"
      style="margin-left: 30px;"
      >
        <img src="https://www.seekpng.com/png/detail/46-460324_microphone-clipart-talk-show-microphone-logo.png" alt="Bootstrap" width="40" height="40" />
        B&R Karaoke | Admin Panel
    </b-navbar-brand>

      <b-navbar-toggle target="navbar-toggle-collapse">
        <template #default="{ expanded }">
          <b-icon v-if="expanded" icon="chevron-bar-up"></b-icon>
          <b-icon v-else icon="chevron-bar-down"></b-icon>
        </template>
      </b-navbar-toggle>

      <b-collapse id="navbar-toggle-collapse" is-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item class="m-sm-3" href="/admin/order">Order</b-nav-item>
          <b-nav-item class="m-sm-3" href="/admin/payment">Payment</b-nav-item>
          <b-nav-item class="m-sm-3" href="/admin/fooddrink">Food/Drink</b-nav-item>
          <b-button class="button" variant="danger" @click="signOut">Sign Out</b-button>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </template>

<script>
import firebase from 'firebase/compat/app'
import {firebaseConfig} from "@/config/firebaseConfig";
import 'firebase/compat/auth'

firebase.initializeApp(firebaseConfig)
export default {
  methods: {
    async signOut () {
      try {
        await firebase.auth().signOut()
        console.log('User signed out')
        this.$router.push({ name: 'signin' })
      } catch (error) {
        console.error(error)
      }
    }
  }

}
</script>

<style>

</style>