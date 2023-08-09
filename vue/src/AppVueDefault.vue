<template>
  <div id="app">
    <Header></Header>
    
    <router-view />
    <new-post-form/>
  </div>
</template>

<script>
import Header from '../src/components/Header.vue'
import NewPostForm from '../src/components/NewPostForm.vue'

export default {
  components: {
    Header, NewPostForm
  }
}
</script>

<style>

</style>