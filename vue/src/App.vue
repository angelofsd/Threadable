<template>
  <div id="app">
    <Header></Header>
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
  body {
    font-family: 'Montserrat', sans-serif;
    margin: 50px 80px;
  }
  input {
      padding-right: 20%;
      padding-top: 10px;
      padding-bottom: 10px;
      padding-left: 10px;
      font-size: 18px;
      border: solid 2px #889DAE;
      border-radius: 8px;
      margin: 10px;
  }
  button {
    font-family: 'Montserrat', sans-serif;
    border: solid 1px transparent;
    border-radius: 5px;
    padding: 5px;
    background-color: inherit;
    cursor: pointer;
  }
  button:hover {
    border: solid 1px #555597;
    transition-duration: 250ms;
    box-shadow: 1px 2px #555597;
  }
  a {
    padding: 1px;
    border-bottom: solid 1px transparent;
  }
  a:hover {
        color:#555597;
        border-bottom: solid 1px #555597;
        transition-duration: 250ms;
    }
    a:not( :hover ) {
        transition-duration: 250ms;
    }
    a {
        text-decoration: none;
        color: inherit;
    }
</style>