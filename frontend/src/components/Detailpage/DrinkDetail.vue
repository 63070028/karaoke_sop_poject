<template>
    <div class="row g-4">
      <div v-for="(food, index) in fooddata" :key="index" class="col-4"
      v-show="food.type === 'drink'">
            <b-card
                img-top
                tag="article"
                style="max-width: 20rem; margin-left: 70px; margin-top: 10%;"
                class="mb-2"
                :class="{'bg-success' : drinkchoose === food.name}"
            >
            <h3>{{food.name}}</h3>
                <b-button href="#" variant="primary" v-b-modal.modal-center @click="checkfoodindex(index)">รายละเอียด</b-button>
            </b-card>
        </div>
      
        <b-modal id="modal-center" centered hide-header hide-footer ref="my-modal">
          <div v-for="(foodmenu, index) in fooddata" :key="index"
            v-show="index === drinkindex">
            <h3 class="m-2 fw-bold">{{foodmenu.name}}</h3>
            <b-icon icon="food"></b-icon>
            <h5 class="m-1">ราคา: <span style="font-size: 15px">{{foodmenu.price}} บาท</span></h5>
            <h5 class="m-1">รายการอาหาร: </h5>
              <ul>
                <li class="my-4" v-for="(foodmenulist, index) in foodmenu.foods" :key="index"
                > {{foodmenulist}} </li >
              </ul>
            <b-button class="mt-3" variant="outline-success" block @click="choosefood(foodmenu, foodmenu.price)">เลือกเซ็ตนี้</b-button>
            <b-button class="mt-3" variant="outline-danger" block @click="hideModal" style="margin-left: 10px;" >ปิด</b-button>
        </div>
        </b-modal>
    </div>
  </template>
    
  <script>
  
  import drinklist from '@/assets/drink.json'
  import Alldata from '@/helper'
  
  export default {

    name: 'Drink',
    components: {
    },
    data(){
        return{
            fooddata: [],
            drinkindex:0,
            drinkchoose:'',
        }
    },
    created(){
        this.Homepagedata=this.$route.params.data
        this.getAllFood()
    },
    methods:{
      checkfoodindex(index){
        this.drinkindex = index
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
      choosefood(set, price){
        this.drinkchoose = set.name
        this.$emit('choosedrink', set, parseInt(price))
        this.$refs['my-modal'].hide()
      },
      getAllFood(){
        Alldata.retrieveAllFood().then(response =>{
          this.fooddata = response.data
        })
      },
    }
    
  }
  </script>
  
  <style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 5px;
  }
  </style>