<template>
  <div>
    <div class="hot-posts">
        <post-list/>
    </div>
  </div>
</template>

<script>
import PostList from '../components/PostList.vue';
export default {
  name: "user-profile",
  components: {PostList},
  data() {
    return {
      user: {},
      invalidCredentials: false
    };
  },
};
</script>