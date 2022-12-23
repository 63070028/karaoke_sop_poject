<template>
    <div>
    <button @click="$emit('clickdate', value)" style="width: 80%;">
      <b-calendar v-model="value" :min="min" :max="max" locale="en" block
      ></b-calendar>
    </button>
    </div>
  </template>
  
  <script>
    export default {
      data() {
        const now = new Date()
        const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
        const minDate = new Date(today)
        minDate.setMonth(minDate.getMonth())
        minDate.setDate(now.getDate())
        // 15th in two months
        const maxDate = new Date(today)
        maxDate.setMonth(maxDate.getMonth() + 1)
        maxDate.setDate(31)
  
        return {
          value: '',
          min: minDate,
          max: maxDate
        }
      },
    }
  </script>