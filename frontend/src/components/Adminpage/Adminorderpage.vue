<template>
<div class="row px-3 ">
    <!-- <AdminNavibarVue></AdminNavibarVue> -->
    <h1 style="margin-top: 100px;">Orders</h1>
<div  class="col-12 p-5 rounded shadow" style="width: 100%;">
    <h4>การจองที่รอการยืนยัน</h4>
    <div>
    <b-table
    :items="items"
    :fields="fields"
    striped responsive="sm"
    :per-page="perPage"
    :current-page="currentPage"
    >
      <template #cell(show_details)="row">
        <b-button size="sm" @click="row.toggleDetails" class="mr-2">
          {{ row.detailsShowing ? 'Hide' : 'Show'}} Details
        </b-button>
      </template>

      <template #cell(status_order)="row">
        <b-button size="sm"
        :class="{'bg-warning' : row.item.status === 'pending',
                'bg-info': row.item.status === 'payment',
                'bg-success': row.item.status === 'complete'
                }"
        disabled>
          {{ row.item.status }}
        </b-button>
      </template>

      <template #cell(accept_order)="row">
        <b-button size="sm"
        style="margin-right: 5px; background-color: green;"
        @click="AdminupdateOrder(row.item._id)"
        v-show="row.item.status === 'pending'"
        >
          Accept
        </b-button>
        <b-button size="sm"
        style="background-color: red;"
        v-show="row.item.status === 'pending'"
        >
          Cancle
        </b-button>
      </template>

      <template #row-details="row">
        <b-card>
          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>OrderId:</b></b-col>
            <b-col>{{ row.item._id }}</b-col>
          </b-row>

          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>Reserve Date:</b></b-col>
            <b-col>{{ row.item.reserve_date }}</b-col>
          </b-row>

          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>Reserve Time:</b></b-col>
            <b-col>{{ row.item.time}}</b-col>
          </b-row>

          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>Room:</b></b-col>
            <b-col>{{ row.item.room.name }}</b-col>
          </b-row>

          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>FoodSet:</b></b-col>
            <b-col>{{ row.item.foodMenu.name }}</b-col>
          </b-row>

          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>Drinkset:</b></b-col>
            <b-col>{{ row.item.drinkMenu.name }}</b-col>
          </b-row>

          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>Soundset:</b></b-col>
            <b-col>{{ row.item.room.accessory }}</b-col>
          </b-row>

          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>AddMicrophone:</b></b-col>
            <b-col>{{ row.item.microphone }}</b-col>
          </b-row>

          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>Result:</b></b-col>
            <b-col>{{ row.item.result }}</b-col>
          </b-row>

          <b-button size="sm" @click="row.toggleDetails">Hide Details</b-button>
        </b-card>
      </template>
    </b-table>
  </div>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
    ></b-pagination>
</div>
            <notifications group="foo" />
            <notifications group="foo1" />
</div>
  </template>

  <script>
  // import AdminNavibarVue from './AdminNavibar.vue';
  import { updateOrder } from '@/service/Userservices'
  import Orderlist from '@/assets/order.json'
  import Alldata from '@/helper'

    export default {
        name:"AdminOrder",
        // components: {
        //     AdminNavibarVue
        // },
      data() {
        return {
          perPage: 3,
          currentPage: 1,
          fields: ['name', 'email', 'status_order' ,'show_details', 'accept_order'],
            items: [],
        }
      },
      computed: {
        rows() {
          return this.items.length
        }
      },
      created(){
        this.getAllOrder();
      },
      methods:{
        AdminupdateOrder(id){
            const data = id
            updateOrder(data).then(response => {
                console.log(response)
                    this.$notify({
                    type: "success",
                    group: 'foo',
                    title: 'OrderComplete',
                    text: 'Order อัพเดทของคุณทำการเสร็จสิ้นเรียบร้อยแล้ว'
            });
            // this.$router.push({name: "homepage"});
            this.getAllOrder()
                })
            .catch((error)=>{
                console.log(error);
                this.$notify({
                    type: "error",
                    group: 'foo1',
                    title: 'OrderError',
                    text: 'Order อัพเดทเกิดข้อผิดพลาด'
                });
            });
        },
        getAllOrder(){
            Alldata.retrieveAllOrder().then(response =>{
          this.items = response.data
        })
        },
      }
    }
  </script>