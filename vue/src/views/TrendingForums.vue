<template>
  <div class="trending-forums"> 
    <trending-forum/>
  </div>
</template>


<script>
import TrendingForum from '../components/TrendingForum.vue'

export default {
  name: "trending-forums",
  components: {
    TrendingForum
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