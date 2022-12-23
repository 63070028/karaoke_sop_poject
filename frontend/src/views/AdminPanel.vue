<template>
    <div class="container">
      <div class="row main-content">
        <AdminNavibar/>
        <div class="col py-3">
          <router-view/>
        </div>
      </div>
    </div>
  </template>
  
  <style scoped>
  .main-content {
    background-color: white;
  }
  </style>
  
  <script>
  import AdminNavibar from "@/components/Adminpage/AdminNavibar";
  export default {
    components: {
      AdminNavibar
    }
  }
  </script>