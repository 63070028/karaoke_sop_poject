<template>
    <div class="container-fluid background py-5">
      
      <section>
        <h3>
          <span class="fw-bold mt-3 ">Karaoke
            <b-icon icon="mic"></b-icon>
          </span>
          <span class="fw-bold"> | Admin Panel Management</span>
        </h3>

        <div class="mt-4">
              <div v-if="isLoading" class="text-center p-5">
                <div class="spinner-border text-secondary" role="status">
                  <span class="visually-hidden">Loading ...</span>
                </div>
                <div class="mt-4 fw-light text-secondary">อยู่ระหว่างดำเนินการ</div>
              </div>
              <button v-else @click="GoogleSignIn" class="btn btn-primary w-100 px-4 py-3">
                <b-icon icon="google"></b-icon>
                Sign-in with Google
              </button>
            </div>
            <div v-if="isError" class="text-left text-danger fw-light mt-4">
              <i class="fas fa-exclamation-triangle me-1"></i>
              เกิดข้อผิดพลาดในการเข้าสู่ระบบ กรุณาลองใหม่อีกครั้ง
            </div>

      <div class="alternative-signup">
        <p class="text-danger">For Admin Only </p>
      </div>
      </section>
    </div>
  </template>
  <style scoped>
  * {
    font-family: 'Roboto', sans-serif;
  
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    font-size: 12px;
  }
  
   .background {
    background-color: #2c3e50;
    min-height: 100vh;
  }
  section {
    margin: 0 auto;
    width: 600px;
    height: 480px;
    transform: translateY(40%);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-evenly;
    background-color: rgba(255,255,255,0.4);
    border-radius: 20px;
    box-shadow: 0px 0 31px 0px rgb(0 0 0 / 10%);
    font-family: 'Prompt', sans-serif;
  
  
  }
  /* h1 {
    margin-top: 3px;
    font-size: 2rem;
    font-weight: 300;
    font-family: 'Poppins', sans-serif;
  } */
  .login-form {
    width: 70%;
    margin-bottom: 15px;
  }
  h4 {
    margin: 20px 0 5px 0;
    font-size: 1.5rem;
    font-weight: 300;
  }
  input {
    width: 80%;
    font-size:1.1rem;
    font-weight: 300;
    padding: 7px 0;
    border: none;
    background-color: inherit;
  }
  .username-input, .password-input {
    width: 90%;
    border-bottom: 1px solid #a4a4a4;
  }
  .login-form>p {
    width: 90%;
    font-size: 1.1rem;
    text-align: right;
    margin-top: 5px;
    font-weight: 300;
  }
  .login-btn {
    border: none;
    padding: 7px 20px;
    width: 50%;
    border-radius: 10px;
    font-size: 1.2rem;
    background-color: black;
    color: white;
    font-weight: 600;
  }
  .alternative-signup {
    width: 70%;
    margin: 20px 0;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .alternative-signup>p {
    width: 90%;
    font-size: 1.1rem;
    text-align: center;
    margin-bottom: 5px;
    font-weight: 300;
  }
  span {
    font-size: 1.5rem;
    font-weight: 600;
    color: black;
    margin-top: 20px;
  }
  
  
  </style>
  <script>
  
  import firebase from 'firebase/compat/app'
  import {firebaseConfig} from "@/config/firebaseConfig";
  import 'firebase/compat/auth'
  
  
  firebase.initializeApp(firebaseConfig)
  export default {
    data() {
      return {
        isLoading: false,
        isError: false
      }
    },
    methods: {
      async GoogleSignIn(token) {
        this.isLoading = true
        try {
          const provider = new firebase.auth.GoogleAuthProvider()
          const result = await firebase.auth().signInWithPopup(provider)
          console.log(result)
          this.$router.push({ path: '/admin/order' })
        } catch (error) {
          this.isLoading = false
          this.isError = true
          console.error(error)
        }
      }
    }
  }
  </script>
