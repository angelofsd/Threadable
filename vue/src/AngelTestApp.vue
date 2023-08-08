<template>
  <div id="app">
    <Header></Header>
    <reply-list :post-id="1"/>
    <router-view />
  </div>
</template>

<script>
import Header from '../src/components/Header.vue'
import ReplyList from './components/ReplyList.vue'

export default {
  components: {
    Header, ReplyList
  }
}
</script>

<style>

</style>