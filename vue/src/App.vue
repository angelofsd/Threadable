<template>
  <div id="app">
    <Header></Header>
    <router-view />
    <ForumList></ForumList>
    <new-post-form/>
  </div>
  
</template>

<script>
import Header from '../src/components/Header.vue'
import ForumList from '../src/components/ForumList.vue'
import NewPostForm from '../src/components/NewPostForm.vue'

export default {
  components: {
    Header, ForumList, NewPostForm
  }
}
</script>

<style>

</style>