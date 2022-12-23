<template>
  <div style="margin-top: 20px;">
      <b-button variant="outline-light"@click="$emit('timereserve', time)"
      style="width: 570px;"
      >
          <select v-model="time"
          style="width: 550px;"
          >
              <option disabled value="">คลิกเพื่อเลือกเวลาที่ต้องการ</option>
              <option class="custom-option">11.00-12.00</option>
              <option class="custom-option">12.00-13.00</option>
              <option class="custom-option">13.00-14.00</option>
              <option class="custom-option">14.00-15.00</option>
              <option class="custom-option">15.00-16.00</option>
              <option class="custom-option">16.00-17.00</option>
              <option class="custom-option">17.00-18.00</option>
              <option class="custom-option">18.00-19.00</option>
              <option class="custom-option">19.00-20.00</option>
              <option class="custom-option">20.00-21.00</option>
              <option class="custom-option">21.00-22.00</option>
          </select>
  </b-button>
  </div>
</template>
<style>
option {
background-color: grey;
color: black;
font-size: 16px;
font-family: 'Prompt', sans-serif;
padding: 5px;
border: 1px solid gray;
}
.custom-option {
background-color: #fff;
color: black;
font-size: 16px;
font-family: 'Prompt', sans-serif;
padding: 5px;
border: 1px solid gray;
}
</style>
<script>
  export default {
    data() {
      return {
       time:''
      }
    },
  }
</script>