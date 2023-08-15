
<template>
  <div class="forums">
    <user-list/>
    <posts-by-forum :forumId="$route.params.id" :forumName="forumName">
    </posts-by-forum>
    <create-post/>

  </div>
</template>


<script>
// This is for specific forum i.e. forums/forumId

import PostsByForum from '../components/PostsByForum.vue'
import PostService from '../services/PostService'
import UserList from '../components/UserList.vue'
import CreatePost from '../components/CreatePost.vue'

export default {
  name: "forum",
  components: {
    PostsByForum,
    UserList,
    CreatePost
  },
  data() {
    return {
      forum: {},
      forumName:"",
      invalidCredentials: false
    };
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
};
</script>
