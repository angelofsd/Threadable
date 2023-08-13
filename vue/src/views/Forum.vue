
<template>
  <div class="forums">
    <posts-by-forum :forumId="$route.params.id" :forumName="forumName">
    </posts-by-forum>
    <create-forum></create-forum>
  </div>
</template>


<script>
// This is for specific forum i.e. forums/forumId

import PostsByForum from '../components/PostsByForum.vue'
import CreateForum from '../components/CreateForum.vue'
import PostService from '../services/PostService';

export default {
  name: "forum",
  components: {
    PostsByForum, CreateForum
  },
  data() {
    return {
      forum: {},
      forumName:"",
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
    }
  }
};
</script>
