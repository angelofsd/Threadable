
<template>
  <div class="forums">
    <posts-by-forum :forumId="$route.params.id" :forumName="forumName">
    </posts-by-forum>
    <create-forum></create-forum>
  </div>
</template>


<script>
// This is for specific forum i.e. forums/{forumId

import PostsByForum from '../components/PostsByForum.vue'
import CreateForum from '../components/CreateForum.vue'

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
  }
};
</script>
