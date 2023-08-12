<template>
  <div class="forums">
    <new-posts-by-forum :forumId="$route.params.id" :forumName="forumName">
    </new-posts-by-forum>

  </div>
</template>


<script>
import NewPostsByForum from '../components/NewPostsByForum.vue'



export default { 
  name: "newpostsinforum",
  components: {
    NewPostsByForum
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
