<template>
  <div class="allForums">
    <create-forum/>
    <forum-list/>
    <create-post/>
  </div>
</template>

<script>
import CreateForum from '../components/CreateForum.vue';
import ForumList from '../components/ForumList.vue'
import CreatePost from '../components/CreatePost.vue'

export default {
  name: "allForums",
  components: {
    ForumList,
    CreateForum,
    CreatePost
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
