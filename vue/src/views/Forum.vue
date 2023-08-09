<template>
  <div class="forums">
    <h1>Forum Title</h1>
    <p>This is the forum description</p>
    <ForumList></ForumList>
  </div>
</template>

<script>
import ForumList from '../components/ForumList.vue'

export default {
  name: "forum",
  components: {
    ForumList
  },
  data() {
    return {
      forum: {},
      invalidCredentials: false
    };
  },
};
</script>
