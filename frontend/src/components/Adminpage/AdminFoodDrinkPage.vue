<template>
  <div>
    <div>
      <b-table striped hover :items="items" :fields="fields">
        <template v-slot:cell(name)="data">
          <b-form-input v-model="data.item.name" @input="updateItem(data.item)"></b-form-input>
        </template>
        <template v-slot:cell(type)="data">
          <b-form-select v-model="data.item.type" @input="updateItem(data.item)">
            <option value="food">Food</option>
            <option value="drink">Drink</option>
          </b-form-select>
        </template>
        <template v-slot:cell(foods)="data">
          <b-form-input v-model="data.item.foods" @input="updateItem(data.item)"></b-form-input>
        </template>
        <template v-slot:cell(price)="data">
          <b-form-input v-model="data.item.price" @input="updateItem(data.item)"></b-form-input>
        </template>
        <template v-slot:cell(quantity)="data">
          <b-form-input v-model="data.item.quantity" @input="updateItem(data.item)"></b-form-input>
        </template>
        <template v-slot:cell(actions)="data">
          <b-button size="sm" variant="info" v-b-modal.modal-2 @click="updateItem(data.item._id, data.item.name, data.item.type, data.item.foods, data.item.price, data.item.quantity)">
            Update</b-button>

          <b-button size="sm" variant="danger" @click="deleteItem(data.item)" style="margin-left: 10px;">Delete</b-button>
        </template>
      </b-table>
      <b-button v-b-modal.modal-1>Add Item</b-button>

    </div>
    
    <b-modal id="modal-1" title="FoodAdd" hide-footer ref="my-modal">
        <span>ชื่อ set อาหาร</span>
        <b-form-input v-model="foodadd.name"></b-form-input>

        <p style="margin-top: 10px;">ประเภท
        <b-form-select v-model="foodadd.type">
            <option value="food">Food</option>
            <option value="drink">Drink</option>
          </b-form-select></p>

        <p style="margin-top: 10px;">อาหารใน set</p>
        <b-form-input v-model="foodmenu"></b-form-input>
          <div 
          v-for="(menu, index) in foodadd.foods" :key="index">
            <p style="margin-top: 10px;">   - {{ menu }}</p>
          </div>
          <b-button size="sm" variant="success" style="margin-top: 10px;" @click="addfoodmenu">AddFoodmenu</b-button>

          <p>ราคา</p>
          <b-form-input v-model="foodadd.price"></b-form-input>

          <span>จำนวน set</span>
          <b-form-input v-model="foodadd.quantity"></b-form-input>

          <b-button size="sm" variant="success" style="margin-top: 10px;" @click="addItem">Add</b-button>
        </b-modal>

      
      
      <b-modal id="modal-2" title="FoodUpdate" hide-footer ref="my-modal">
        <span>ชื่อ set อาหาร</span>
        <b-form-input v-model="updatefood.name"></b-form-input>

        <p style="margin-top: 10px;">ประเภท
        <b-form-select v-model="updatefood.type">
            <option value="food">Food</option>
            <option value="drink">Drink</option>
          </b-form-select></p>

        <p style="margin-top: 10px;">อาหารใน set</p>
        <b-form-input v-model="updatefoodmenu"></b-form-input>
          <div 
          v-for="(menu, index) in updatefood.foods" :key="index">
            <p style="margin-top: 10px;">   - {{ menu }}
            <b-button size="sm" variant="danger" style="margin-top: 10px; margin-left: 20px;" @click="deletefoodmenu(index)">DeleteFood</b-button></p>
          </div>
          <b-button size="sm" variant="success" style="margin-top: 10px;" @click="updatemenufoods">UpdateFoodmenu</b-button>

          <p>ราคา</p>
          <b-form-input v-model="updatefood.price"></b-form-input>

          <span>จำนวน set</span>
          <b-form-input v-model="updatefood.quantity"></b-form-input>

          <b-button size="sm" variant="success" style="margin-top: 10px;" @click=" updatetodatabase" >Update</b-button>
        </b-modal>
  
          <notifications group="foo" />
          <notifications group="foo1" />
          <notifications group="foo2" />
          <notifications group="foo3" />
  </div>
  
  </template>
  
  <script>

  import Alldata from '@/helper'
  import { createFood, updateFood} from '@/service/Userservices'

  export default {
    data() {
      return {
        // foods: [],
        items: [],
        fields: [{
          key: 'name',
          label: 'Name'
        },
          {
            key: 'type',
            label: 'Type'
          },
          {
            key: 'foods',
            label: 'Foods'
          },
          {
            key: 'price',
            label: 'Price'
          },
          {
            key: 'quantity',
            label: 'Quantity'
          },
          {
            key: 'actions',
            label: 'Actions'
          }
        ],
        
        foodadd:{
          name: "",
          type: "",
          foods: [],
          price: 0,
          quantity: 0,
        },
        foodmenu:'',

        updatefood:{
          _id:'',
          name:'',
          type:'',
          foods:[],
          price: 0,
          quantity: 0,
          img: null
        },
        updatefoodmenu:'',
      }
    },
    created(){
        this.getAllFood()
    },
    methods: {

      updateItem(food_id, name, type, foods, price, quantity) {
        this.updatefood._id = food_id
        this.updatefood.name = name
        this.updatefood.type = type
        this.updatefood.foods = foods
        this.updatefood.price = price
        this.updatefood.quantity = quantity
      },

      updatetodatabase(){
        const data ={
          _id:this.updatefood._id,
          name:this.updatefood.name,
          type:this.updatefood.type,
          foods:this.updatefood.foods,
          price:parseInt(this.updatefood.price),
          quantity:parseInt(this.updatefood.quantity)
        }

        updateFood(data)
        .then(response => {
                console.log(response)
                    this.$notify({
                    type: "success",
                    group: 'foo2',
                    title: 'UpdateFoodComplete',
                    text: 'UpdateFood ของคุณทำการเสร็จสิ้นเรียบร้อยแล้ว'
                    
            });
            // this.$router.push({name: "homepage"});
            this.updatefood._id=''
            this.updatefood.name = ''
            this.updatefood.type = ''
            this.updatefood.foods = []
            this.updatefood.price = 0
            this.updatefood.quantity = 0
            this.getAllFood()
                })
            .catch((error)=>{
                console.log(error);
                this.$notify({
                    type: "error",
                    group: 'foo3',
                    title: 'UpdateFoodError',
                    text: 'UpdateFood เกิดข้อผิดพลาด'
                });
            });
            this.$refs['my-modal'].hide()

      },

      deleteItem(item) {
        const index = this.items.indexOf(item)
        if (index > -1) {
          this.items.splice(index, 1)
        }
      },

      deletefoodmenu(index) {
        console.log(index)
       this.updatefood.foods.splice(index, 1)
      },

      //เอาออก
      addItem() {
        // this.foodadd.foods.push(this.foodmenu)
        const data ={
          name:this.foodadd.name,
          type:this.foodadd.type,
          foods:this.foodadd.foods,
          price:parseInt(this.foodadd.price),
          quantity:parseInt(this.foodadd.quantity),
          img:null
        }
        createFood(data)
        .then(response => {
                console.log(response)
                    this.$notify({
                    type: "success",
                    group: 'foo',
                    title: 'AddFoodComplete',
                    text: 'AddFood ของคุณทำการเสร็จสิ้นเรียบร้อยแล้ว'
                    
            });
            // this.$router.push({name: "homepage"});
            this.foodadd.name = ''
            this.foodadd.type = ''
            this.foodadd.foods = []
            this.foodadd.price = 0
            this.foodadd.quantity = 0
            this.getAllFood()
                })
            .catch((error)=>{
                console.log(error);
                this.$notify({
                    type: "error",
                    group: 'foo1',
                    title: 'AddFoodError',
                    text: 'AddFood เกิดข้อผิดพลาด'
                });
            });
            this.$refs['my-modal'].hide()
      },

      addfoodmenu: function () {
      this.foodadd.foods.push(this.foodmenu);
      this.foodmenu = '';
      },

      updatemenufoods: function () {
      this.updatefood.foods.push(this.updatefoodmenu);
      this.updatefoodmenu = '';
      },

      getAllFood(){
        Alldata.retrieveAllFood().then(response =>{
          this.items = response.data
        })
      },
    }
  }
  </script>