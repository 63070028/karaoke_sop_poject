<template>
  <div id="app">
    <NavbarRe/>
<!--      <div class="row">-->
<!--        <div class="column">-->
<!--          <img src="https://supercube.biz/wp-content/uploads/2018/11/Supercube-Cowgate-Wide-100-scaled.jpg" style="width:100%" alt="Snow">-->
<!--          <h4 style="margin-top: 5px">ห้องขนาดเล็ก</h4>-->
<!--        </div>-->
<!--      -->
<!--        <div class="column">-->
<!--          -->
<!--          <img src="https://images.squarespace-cdn.com/content/v1/5a3808319f07f5aac2692a82/1570313106044-QCTT0GZ9IGESVJUQ7THC/_CA22056-Edit.jpg" style="width:100%" alt="Snow">-->
<!--          <h4 style="margin-top: 5px">ห้องขนาดกลาง</h4>-->
<!--        </div>-->
<!--        <div class="column">-->
<!--          <img src="https://images.squarespace-cdn.com/content/v1/5a3808319f07f5aac2692a82/1570313108058-DOIUMXJ8MT72IEGO22L9/_CA22106-Edit.jpg" style="width:100%" alt="Snow">-->
<!--          <h4 style="margin-top: 5px">ห้องขนาดใหญ่</h4>-->
<!--        </div>-->
<!--        <div class="column">-->
<!--          -->
<!--          <img src="https://images.squarespace-cdn.com/content/v1/5a3808319f07f5aac2692a82/1570315223187-2PT271J4SJMN8G704MNM/_CA23957-Edit.jpg" style="width:100%" alt="Snow">-->
<!--          <h4 style="margin-top: 5px">ห้องขนาดปาร์ตี้</h4>-->
<!--        </div>-->
<!--      </div>-->
      <h2 style="margin-top: 2%;">เลือกขนาดของห้อง</h2>
      <div class="row gy-5">
      <div>
        <RadioRoom 
        class="p-4" 
        :chooseroom="Homepagedata.chooseroom.name" 
        @onUpdateRoom="onclickChoose"
        />
      </div>
    </div>
    <div class="container-box">
      <div class="flex">
        <div class="base style8" style="padding-top: 10px">
          <h2>เลือกวันที่ต้องการจอง</h2>
          <CalenderVue style="margin-top: 2%;" @clickdate="getDate"></CalenderVue>

          <h2 style="padding-top: 10px">เลือกเวลาที่ต้องการจอง</h2>
          <timedate-vue @timereserve="gettime"></timedate-vue>

          <div class="container-box">
            <div class="sumary-card">
              <h5 style="font-family: 'Prompt', sans-serif">ห้องที่ระบุ : <span style="color: forestgreen">{{Homepagedata.chooseroom.name}}</span></h5>
              <h5 style="font-family: 'Prompt', sans-serif">วันที่ระบุ : <span style="color: forestgreen">{{convertDate()}}</span></h5>
              <h5 style="font-family: 'Prompt', sans-serif">เวลาที่ระบุ : <span style="color: forestgreen">{{Homepagedata.time}}</span></h5>
              <b-button class="mt-2 mb-3 button" @click="gotoDetailpage()"><span>ไปหน้าถัดไป</span></b-button>
            </div>

          </div>

          <notifications group="foo" />
        <notifications group="foo1" />
        </div>
      </div>
    </div>
<!--    <v-app id="inspire">-->
<!--    <v-footer padless>-->
<!--      <v-col-->
<!--        class="text-center"-->
<!--        cols="12"-->
<!--      >-->
<!--        {{ new Date().getFullYear() }} — <strong>SOP SOMSAN KARAOKE</strong>-->
<!--      </v-col>-->
<!--    </v-footer>-->
<!--  </v-app>-->
<footer-vue></footer-vue>

  </div>
</template>
  
<script>
import NavbarRe from './NavbarReservetion.vue'
import RadioRoom from './RoomRadio.vue'
import CalenderVue from './Calender.vue'
import TimedateVue from './Timedate.vue'
import FooterVue from "./Footer.vue";
import dayjs from 'dayjs'

export default {
  name: 'Homepage',
  components: {
    NavbarRe,
    RadioRoom,
    CalenderVue,
    TimedateVue,
    FooterVue
  },
  data(){
    return{
      Homepagedata:{
          chooseroom: '',
          reverdate:'',
          foodset:null,
          drinkset:null,
          soundset:'',
          resualt: 0,
          roomprice: 0,
          foodprice: 0,
          drinkprice: 0,
          time:''
      }
    }
  },
  methods:{
    onclickChoose(room, price){
      this.Homepagedata.chooseroom = room
      this.Homepagedata.resualt = price
      this.Homepagedata.roomprice =  price
    },
    getDate(date){
      this.Homepagedata.reverdate = date
    },
    gotoDetailpage(){
      if (this.Homepagedata.chooseroom === '') {
        alert('โปรดระบุห้องที่ต้องการ')
      }
      else if (this.Homepagedata.reverdate === '') {
        alert('โปรดระบุวันที่')
      }
      else if (this.Homepagedata.time === '') {
        alert('โปรดระบุเวลาที่ต้องการ')
      }
      else {
        this.$router.push({name: "detailpage", params:{data: this.Homepagedata}});
      }
    },
    gettime(time){
      this.Homepagedata.time = time
    },
    hideModal() {
      this.$refs['my-modal'].hide()
    },
    convertDate() {
      const dated = this.Homepagedata.reverdate
      let formatDate = dayjs(dated, 'YYYY-MM-DD').format('DD/MM/YYYY')
      if (formatDate != 'Invalid Date') {
        return `วันที่ ${formatDate}`
      } else{
        return ``
      }
    }
  },

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
.row {
  display: flex;
}

.column {
  flex: 25%;
  padding: 5px;
}
.container-box{
  width: 90%;
  margin:auto;
}
.base {
  margin: 16px;
  width: 100%;
  height: 100%;
  background-color: #fff;
  border-radius: 15px
}
.style8{
   box-shadow:
  inset 0 0 0.5em rgba(0,0,0,0.1),
   0.2em 0.2em 0.5em rgba(0,0,0,0.2);
}
.style8 h1{
  margin-top: 10px;
}
h2, h1, h5, h3, h4, p{
  font-family: 'Prompt', sans-serif;
}

h5 >span{
  font-family: 'Prompt', sans-serif;
}

</style>