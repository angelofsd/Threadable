<template>
  <div id="app">
    <Header></Header>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
import Header from '../src/components/Header.vue'
export default {
  components: {
    Header
  }
}
</script>

<style>

</style>