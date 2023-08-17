<template>
  <div>
    <div class="profile">
        <Profile></Profile>
    </div>
  </div>
</template>

<script>
import Profile from '../components/Profile.vue'
import PostService from '../services/PostService';

export default {
  name: "user-profile",
  components: {Profile},
  data() {
    return {
      user: {},
      invalidCredentials: false
    };
  },
  created() {
      if(this.$store.state.user) {
        PostService.getLikedPostsByUserId(this.$store.state.user.id).then((response) => {
                    this.$store.commit("SET_LIKED_POSTS", response.data)
                })
        PostService.getDislikedPostsByUserId(this.$store.state.user.id).then((response) => {
          this.$store.commit("SET_DISLIKED_POSTS", response.data)
        })
        PostService.getPostsByUserId(this.$route.params.id).then((response) => {
            this.posts = response.data
        })
      }
  }
};
</script>