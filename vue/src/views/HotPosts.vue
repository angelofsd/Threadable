<template>
  <div>
    <div class="hot-posts">
        <HotPosts></HotPosts>
    </div>
  </div>
</template>

<script>
import HotPosts from '../components/HotPosts.vue';
export default {
  name: "user-profile",
  components: {HotPosts},
  data() {
    return {
      user: {},
      invalidCredentials: false
    };
  },
};
</script>