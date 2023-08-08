<template>
  <div id="app">
    <Header></Header>
    <router-view />
    <ForumList></ForumList>
  </div>
  
</template>

<script>
import Header from '../src/components/Header.vue'
import ForumList from '../src/components/ForumList.vue'

export default {
  components: {
    Header, ForumList
  }
}
</script>

<style>

</style>