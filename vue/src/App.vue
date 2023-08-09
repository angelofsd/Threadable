<template>
  <div id="app">
    <Header></Header>
    <!-- <ForumList></ForumList> -->
    <router-view />
    <new-post-form/>
  </div>
</template>

<script>
import Header from '../src/components/Header.vue'
import NewPostForm from '../src/components/NewPostForm.vue'
// import ForumList from '../src/components/ForumList.vue'

export default {
  components: {
    Header, NewPostForm//, ForumList
  }
}
</script>

<style>

</style>