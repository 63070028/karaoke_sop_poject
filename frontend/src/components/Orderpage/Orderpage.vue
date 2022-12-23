<template>
    <div class="wrapper2">
        
            <h2>
            <img src="https://www.seekpng.com/png/detail/46-460324_microphone-clipart-talk-show-microphone-logo.png" alt="Bootstrap" width="40" height="40"/>
                B&R Karaoke
            </h2>
            <div style="border: 1px solid black;margin-top: 20px;"></div>

                <div class="input_group2">
                    <h5>วันที่จอง: </h5>
                    <p>{{convertDate()}}</p>
                </div>
                <div class="input_group2">
                    <h5>ช่วงเวลาที่จอง: </h5>
                    <p>{{Homepagedata.time}}</p>
                </div>
                <div class="input_group2">
                    <h5>ขนาดห้อง: </h5>
                    <p>{{Homepagedata.chooseroom.name}}</p>
                </div>
                <div class="input_group2">
                    <h5>ชุดเครื่องดื่ม: </h5>
                    <p
                    v-if="Homepagedata.drinkset === null"
                    >ไม่ได้ทำการเลือกเครื่องดื่ม</p>
                    <p
                    v-if="Homepagedata.drinkset !== null"
                    >{{Homepagedata.drinkset.name}}</p>
                </div>
                <div class="input_group2">
                    <h5>ชุดอาหาร: </h5>
                    <p
                    v-if="Homepagedata.foodset === null"
                    >ไม่ได้ทำการเลือกอาหาร</p>
                    <p
                    v-if="Homepagedata.foodset !== null"
                    >{{Homepagedata.foodset.name}}</p>
                </div>
                <div class="input_group2">
                    <h5>ชุดเครื่องเสียง: </h5>
                    <p>{{Homepagedata.soundset.accessory}}</p>
                </div>
                <div class="input_group2">
                    <h5>จำนวนไมค์ที่ต้องการเพิ่ม: </h5>
                    <p>{{Homepagedata.editmic}}</p>
                </div>
                <div class="input_group2">
                    <h5>ราคารวม: </h5>
                    <p>{{Homepagedata.resualt}} บาท</p>
                </div>

            <div class="input_group2">
                <div class="input_box2">
                    <h5>ชื่อผู้ใช้งาน: </h5>
                    <input class="name" v-model="userPick" placeholder="ชื่อ นามสกุล" />
                </div>
                
            </div>
            <div class="input_group2">
                <div class="input_box2">
                    <h5>อีเมล: </h5>
                    <input class="name" v-model="emailPick" placeholder="exampel@karaoke.com" />
                </div>
                
            </div>
              <b-button variant="success" class="mt-2 mb-2 button" @click="UsercreatOrder()"><span>ยืนยันการจอง</span></b-button>

            <notifications group="foo" />
            <notifications group="foo1" />
    </div>
    
</template>
      
    <script>
  import { createOrder } from '@/service/Userservices';
  import dayjs from "dayjs";

    export default {
      name: 'Orderpage',
      components: {
      },
      data(){
          return{
              Homepagedata:{},
              emailPick: '',
              userPick: '',
          }
      },
      created(){
          this.Homepagedata=this.$route.params.data
      },
      methods:{
        UsercreatOrder(){
            const data = {
                name:this.userPick,
                email:this.emailPick,
                reserve_date:this.Homepagedata.reverdate,
                time:this.Homepagedata.time,
                phone:'08000000',
                status:"pending",
                room:this.Homepagedata.soundset,
                accessoryName: this.Homepagedata.soundset.accessory,
                microphone:parseInt(this.Homepagedata.editmic),
                foodMenu:this.Homepagedata.foodset,
                drinkMenu:this.Homepagedata.drinkset,
                result:this.Homepagedata.resualt
            }
            createOrder(data)
            .then(response => {
                console.log(response)
                    this.$notify({
                    type: "success",
                    group: 'foo',
                    title: 'OrderComplete',
                    text: 'Order ของคุณทำการเสร็จสิ้นเรียบร้อยแล้ว'
                    
            });
            // this.$router.push({name: "homepage"});
                })
            .catch((error)=>{
                console.log(error);
                this.$notify({
                    type: "error",
                    group: 'foo1',
                    title: 'OrderError',
                    text: 'Order เกิดข้อผิดพลาด'
                });
            });
            this.$router.push({name: "homepage"});
        },
        convertDate() {
          const dated = this.Homepagedata.reverdate
          let formatDate = dayjs(dated, 'YYYY-MM-DD').format('DD/MM/YYYY')
          if (formatDate != 'Invalid Date') {
            return `${formatDate}`
          } else{
            return ``
          }
        }
      },
      // name:this.userPick,
                // email:this.emailPick,
                // reserve_date:this.Homepagedata.reverdate,
                // time:"19.00",
                // status:"pending",
                // roomId:"1",
                // roomName:this.Homepagedata.chooseroom,
                // accessoryName: this.Homepagedata.soundset,
                // microphone:this.Homepagedata.editmic,
                // foodMenuSetId:"1",
                // foodMenuName:this.Homepagedata.foodset,
                // drinkMenuSetId:"2",
                // drinkMenuName:this.Homepagedata.drinkset,
                // result:this.Homepagedata.resualt
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
    .wrapper2{
    background-color:#fff;
    width: 500px;
    padding: 25px;
    margin: 25px auto 0;
    box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
    border-radius: 10px;
    }
    .wrapper2 h2{
    background-color: #fcfcfc;
    color: #333;
    font-size: 24px;
    padding: 10px;
    margin-bottom: 8px;
    text-align: center;
    border: 1px solid #333;
    margin-top: 8px;
    }
    .wrapper2 h5{
    padding-bottom: 5px;
    color: #333;
    font-family: 'Prompt', sans-serif;
    }
    .input_group2 {
    margin-bottom: 8px;
    width: 100%;
    position: relative;
    display: flex;
    flex-direction: row;
    padding: 5px 0;
    }
    .input_group2 p{
        margin-left: 20px;
        padding-bottom: 5px;
        color: #333;
        font-size: 18px;
    }
    .input_box2 .name{
    padding: 14px 10px 14px 50px;
    width: 400px;
    border: 1px solid #0003;
    outline: none;
    letter-spacing: 1px;
    transition: 0.3s;
    border-radius: 3px;
    color: #333;
    }
    

    </style>