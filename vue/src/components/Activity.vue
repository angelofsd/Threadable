<template>
  <div>
      <h3>Activity</h3>
      
  </div>
</template>

<script>
import postService from '../services/PostService'

export default {
  components: { },
    data() {
        return {
            posts: {}
        }
    },
    created() {
        postService.getPostsByUserId(this.$route.params.id).then((response) => {
            this.postList = response.data
        })
    }
}
</script>

<style>

</style>