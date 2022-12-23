<template>
  <div class="row g-4">
    <div v-for="(sound, index) in sounddata" :key="index" class="col-4"
    v-show="sound.name === Homepagedata.chooseroom.name"
    >
          <b-card
              img-top
              tag="article"
              style="max-width: 20rem; margin-left: 70px; margin-top: 10%;"
              class="mb-2"
              :class="{'bg-success' : soundchoose === sound.accessory}"
          >
          <h3>{{sound.accessory}}
            <b-icon icon="soundwave"></b-icon></h3>
              <b-card-text>
                  {{sound.detail}}
              </b-card-text>

              <b-button href="#" variant="primary" v-b-modal.modal-3 @click="checkfoodindex(index)">Detail</b-button>
          </b-card>
      </div>
    
      <b-modal id="modal-3" title="AccessoriesDetail" hide-footer hide-header ref="my-modal">
        <div v-for="(foodmenu, index) in sounddata" :key="index"
          v-show="index === soundindex">
<!--          <p>ราคา: {{foodmenu.price}}</p>-->
          <!-- <p>รายการอาหาร: 
            <span class="my-4" v-for="(foodmenulist, index) in foodmenu.menu" :key="index"
            > {{foodmenulist}} 
          </span >
          </p> -->
          <div>
            <h4>{{foodmenu.name}}</h4>
            <h5>{{foodmenu.accessory}}</h5>
            จำนวนไมค์ที่ต้องการเพิ่ม: <input class="input" type="text" v-model="editMicHowmuch"/>
          </div>
          <b-button class="mt-3" variant="outline-danger" block @click="hideModal">Close Me</b-button>
          <b-button class="mt-3" variant="outline-success" block @click="choosefood(foodmenu)" style="margin-left: 10px;">Choose Set</b-button>
          
      </div>
      </b-modal>
  </div>
</template>
  
<script>

import soundlist from '@/assets/soundsystem.json'
import Alldata from '@/helper'
export default {

  name: 'Soundsystem',
  components: {
  },
  data(){
      return{
          sounddata: [],
          soundindex:0,
          soundchoose:'',
          editMicHowmuch: '',
      }
  },
  created(){
      this.Homepagedata=this.$route.params.data
      this.getAllroom()
  },
  methods:{
    checkfoodindex(index){
      this.soundindex = index
    },
    hideModal() {
      this.$refs['my-modal'].hide()
    },
    choosefood(set){
      this.soundchoose = set.accessory
      this.$emit('choosesound', set)
      this.$emit('editmic', this.editMicHowmuch)
      this.$refs['my-modal'].hide()
    },
    getAllroom(){
      Alldata.retrieveAllRoom().then(response =>{
          this.sounddata = response.data
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