<template>
    <div class="row px-3 ">
        <h1 style="margin-top: 100px;">Payments</h1>
    <div  class="col-12 p-5 rounded shadow" style="width: 100%;">
        <h4>การจ่ายเงินที่รอการยืนยัน</h4>
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
    
          <template #cell(status_payment)="row">
            <b-button size="sm" 
            :class="{'bg-warning' : row.item.payment_status === 'pending',
                    'bg-success': row.item.payment_status === 'complete'
                    }"
            disabled>
              {{ row.item.payment_status }}
            </b-button>
          </template>
    
          <template #cell(confirm_payment)="row">
            <b-button size="sm" 
            style="margin-right: 5px; background-color: green;"
            @click="AdminupdateOrder(row.item._id)"
            v-show="row.item.payment_status === 'pending'"
            >
              Accept
            </b-button>
            <b-button size="sm" 
            style="background-color: red;"
            v-show="row.item.payment_status === 'pending'"
            >
              Cancle
            </b-button>
          </template>
    
          <template #row-details="row">
            <b-card>
              <b-row class="mb-2">
                <b-col sm="3" class="text-sm-right"><b>PaymentId:</b></b-col>
                <b-col>{{ row.item._id }}</b-col>
              </b-row>

              <b-row class="mb-2">
                <b-col sm="3" class="text-sm-right"><b>OrderId:</b></b-col>
                <b-col>{{ row.item.orderId }}</b-col>
              </b-row>

              <b-row class="mb-2">
                <b-col sm="3" class="text-sm-right"><b>Amountpaid:</b></b-col>
                <b-col>{{ row.item.amount_paid }}</b-col>
              </b-row>

              <b-row class="mb-2">
                <b-col sm="3" class="text-sm-right"><b>PaymentDate:</b></b-col>
                <b-col>{{ row.item.payment_date }}</b-col>
              </b-row>

              <b-row class="mb-2">
                <b-col sm="3" class="text-sm-right"><b>PaymentTime:</b></b-col>
                <b-col>{{ row.item.payment_time }}</b-col>
              </b-row>

              <b-row class="mb-2">
                <b-col sm="3" class="text-sm-right"><b>Phone:</b></b-col>
                <b-col>{{ row.item.phone }}</b-col>
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
      import { updatePayment } from '@/service/Userservices'
      import Alldata from '@/helper'
    
        export default {
            name:"AdminOrder",
          data() {
            return {
              perPage: 3,
              currentPage: 1,
              fields: ['_id', 'email', 'status_payment' ,'show_details', 'confirm_payment'],
                items: [],
            }
          },
          computed: {
            rows() {
              return this.items.length
            }
          },
          created(){
            this.getAllPayment();
          },
          methods:{
            AdminupdateOrder(id){
                const data = id
                updatePayment(data).then(response => {
                console.log(response)
                    this.$notify({
                    type: "success",
                    group: 'foo',
                    title: 'PaymentUpdateComplete',
                    text: 'Payment อัพเดทของคุณทำการเสร็จสิ้นเรียบร้อยแล้ว' 
            });
            this.getAllPayment();
                })
            .catch((error)=>{
                console.log(error);
                this.$notify({
                    type: "error",
                    group: 'foo1',
                    title: 'PaymentUpdateError',
                    text: 'Payment อัพเดทเกิดข้อผิดพลาด'
                });
            });
            },
            getAllPayment(){
                Alldata.retrieveAllPayment().then(response =>{
              this.items = response.data
            })
            },
          }
        }
      </script>