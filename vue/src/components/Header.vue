<template>
  <div id="header">
    <h1>Reddit (Lite)</h1>
    <div id="nav">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">&nbsp;|&nbsp;Logout</router-link>
        <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">&nbsp;|&nbsp;Login</router-link>
        <p>You are logged in as {{$store.state.user.username}}</p>
    </div>
      <form id="search">
          <input type="text" placeholder="Search..."/>
      </form>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
    #header {
        border-bottom: solid 1px #889DAE;
        padding-bottom: 25px;
        margin-left: 40px;
        margin-right: 40px;
    }
    #search {
        text-align: center;
    }
    input {
        padding-right: 300px;
        padding-top: 10px;
        padding-bottom: 10px;
        padding-left: 10px;
        font-size: 18px;
        border: solid 2px #889DAE;
        border-radius: 8px;
        margin: 10px;
    }

    
</style>