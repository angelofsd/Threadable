<template>
  <div>
      <post-details v-bind:postId="$route.params.id" />
  </div>
</template>

<script>
import PostDetails from '../components/PostDetails.vue'
import PostService from '../services/PostService'

export default {
    name: "post",
    components: {
        PostDetails
    },
    created() {
        if(this.$store.state.token != '') {
      PostService.getLikedPostsByUserId(this.$store.state.user.id).then((response) => {
                  this.$store.commit("SET_LIKED_POSTS", response.data)
              })
      PostService.getDislikedPostsByUserId(this.$store.state.user.id).then((response) => {
        this.$store.commit("SET_DISLIKED_POSTS", response.data)
      })
    }
    }
}
</script>

<style>

</style>