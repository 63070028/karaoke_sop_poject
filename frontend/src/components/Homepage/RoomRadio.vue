<template>
  <div class="row g-4">
    <div class="col-3 fx5" v-for="(room, index) in roomsdata" :key="index">
      <a class="btn" role="button"
      :class="{'bg-warning' : room.name === chooseroom}"
      v-b-modal.modal-center
      @click="setindex(index)"
      >
        <div class="item col4">
            <img :src="room.img"
            width="350" height="300" />
            <div class="text-box">
              <h4>คลิกเพื่อดูรายละเอียด!</h4>
            </div>
        </div>
      </a>
        <h4>{{room.name}}</h4>
    </div>

    <b-modal id="modal-center" centered title="รายละเอียดของห้อง" hide-footer hide-header ref="my-modal">
            <div v-for="(roomdetail, index) in roomsdata" :key="index"
            v-show="index === roomsindex">
              <center>
              <img :src="roomdetail.img" class="rounded"
                   width="250" height="200"/>
              </center>
              <h4 style="text-align: center">รายละเอียด</h4>
              <h5>ขนาด: {{roomdetail.name}}</h5>
              <span class="my-2"
              >  {{roomdetail.detail}}
              </span >
              <h5 class="mb-3">ราคา: {{roomdetail.price}} บาท/ชั่วโมง</h5>
              <b-button class="mt-1" variant="outline-success" @click="onclickChoose(roomdetail, roomdetail.price)">เลือกห้องนี้</b-button>
              <b-button class="mt-1" variant="outline-danger" @click="hideModal" style="margin-left: 10px;">ปิด</b-button>
            </div>
        </b-modal>
  </div>
      
  </template>
  
  <script>
  import roomList from '@/assets/rooms.json'

  export default {
    data(){
      return{
        roomsdata: roomList ,
        roomsindex: 0,
      }
    },
    props: ["chooseroom"],
    methods:{
      onclickChoose(room, price){
        this.$refs['my-modal'].hide()
        this.$emit('onUpdateRoom', room, parseInt(price))
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
      setindex(index){
        this.roomsindex = index
      },
    }
  }
  </script>