<template>
  <div>
      <h1>Post Details</h1>
      <post-details v-bind:postId="$route.params.id" />
  </div>
</template>

<script>
import PostDetails from '../components/PostDetails.vue'

export default {
    name: "post",
    components: {
        PostDetails
    },
}
</script>

<style>

</style>