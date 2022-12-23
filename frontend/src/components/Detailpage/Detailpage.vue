<template>
  <div>
    <navbar></navbar>
    <div>
      <b-img src="https://www.imgacademy.com/sites/default/files/inline-images/lunch.jpg" alt="Responsive image" fluid></b-img>
    </div>

    <h2 style="text-align: center; margin-top: 20px;">เลือกประเภทอาหาร</h2>
    <food-detail @choosefood="getfoodset"></food-detail>
    <center><hr style="width: 90%;"></center>
<!--    <div style="border: 1px solid black;margin-top: 20px; background-color: black;padding: 5px; margin: 60px;" ></div>-->
    <h2 style="text-align: center; margin-top: 60px;">เลือกประเภทเครื่องดื่ม</h2>
    <drink-detail-vue @choosedrink="getdrinkset"></drink-detail-vue>
    <center><hr style="width: 90%;"></center>
<!--    <div style="border: 1px solid black;margin-top: 50px; background-color: black; padding: 5px; margin: 60px;"></div>-->
    <h2 style="text-align: center; margin-top: 60px;">เลือกอุปกรณ์</h2>
    <soundsystem-detail-vue @choosesound="getsoundset" @editmic="getmic"></soundsystem-detail-vue>


  <div style="text-align: center;">
    <b-button class="mt-2 mb-3 button" @click="gotoOrderpage()"><span>ไปหน้าถัดไป</span></b-button>
  </div>
  </div>
  </template>
    
  <script>
import FoodDetail from './FoodDetail.vue'
import DrinkDetailVue from './DrinkDetail.vue'
import Navbar from '../Homepage/NavbarReservetion.vue'
import SoundsystemDetailVue from './SoundsystemDetail.vue'

  export default {
    name: 'Detailpage',
    components: {
        FoodDetail,
        DrinkDetailVue,
        Navbar,
        SoundsystemDetailVue
    },
    data(){
        return{
            Homepagedata:{},
        }
    },
    created(){
        this.Homepagedata=this.$route.params.data
    },
    methods:{
      getfoodset(set, price){
        this.Homepagedata.foodset = set
        this.Homepagedata.foodprice = price
        this.Homepagedata.resualt += price
      },
      getdrinkset(set1, price1){
        this.Homepagedata.drinkset = set1
        this.Homepagedata.drinkprice = price1
        this.Homepagedata.resualt += price1
      },
      getsoundset(set2){
        this.Homepagedata.soundset = set2
      },
      getmic(set3){
        this.Homepagedata.editmic = set3
      },
      gotoOrderpage(){
        this.$router.push({name: "orderpage", params:{data: this.Homepagedata}});
      }
    }
    
  }
  </script>
  
  <style>
  #app {
    font-family: 'Prompt', sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 5px;
  }
  h2, h1, span, h5, h3, h4{
    font-family: 'Prompt', sans-serif;
  }
  </style>