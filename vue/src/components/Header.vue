<template>
  <div>
      <h1>Reddit (Lite)</h1>
      <form id="search">
          <input id="searchbox" type="text" placeholder="Search..."/>
      </form>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
    #search {
        text-align: center;
    }
    #searchbox {
        padding-right: 250px;
        padding-top: 10px;
        padding-bottom: 10px;
        padding-left: 5px;
        font-size: 24px;
        border: solid 2px #889DAE;
        border-radius: 8px;
        margin: 10px;
    }

    
</style>