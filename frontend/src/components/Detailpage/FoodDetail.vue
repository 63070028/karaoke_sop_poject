<template>
    <div class="row g-4">
      <div v-for="(food, index) in fooddata" :key="index" class="col-4"
      v-show="food.type === 'food'">
            <b-card
                img-top
                tag="article"
                style="max-width: 20rem; margin-left: 70px; margin-top: 10%;"
                class="mb-2"
                :class="{'bg-success' : foodchoose === food.name}"
            >
            <h3>{{food.name}}</h3>

                <b-button href="#" variant="primary" v-b-modal.modal-1 @click="checkfoodindex(index)" v-show="food.quantity !== 0">รายละเอียด</b-button>
            </b-card>
        </div>
      
        <b-modal id="modal-1" title="FoodDetail" hide-header hide-footer ref="my-modal">
          <div v-for="(foodmenu, index) in fooddata" :key="index"
            v-show="index === foodindex">
            <h3 class="m-2 fw-bold">{{foodmenu.name}}</h3>
            <h5 class="m-1">ราคา: <span style="font-size: 15px">{{foodmenu.price}} บาท</span></h5>
            <h5 class="m-1">รายการอาหาร </h5>
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
  
  import foodList from '@/assets/food.json'
  import Alldata from '@/helper'

  export default {

    name: 'Food',
    components: {
    },
    data(){
        return{
            fooddata: [],
            foodindex:0,
            foodchoose:'',
        }
    },
    created(){
        this.Homepagedata=this.$route.params.data
        this.getAllFood()
    },
    methods:{
      checkfoodindex(index){
        this.foodindex = index
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
      choosefood(set, price){
        this.foodchoose = set.name
        this.$emit('choosefood', set, parseInt(price))
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
  h2, h1, span, h5, h3, h4{
    font-family: 'Prompt', sans-serif;
  }
  </style>