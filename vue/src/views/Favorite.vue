<template>
  <div>
    <div class="profile">
        <FavoriteForums></FavoriteForums>
    </div>
  </div>
</template>

<script>
import FavoriteForums from '../components/FavorieForums.vue';
export default {
  name: "user-profile",
  components: {FavoriteForums},
  data() {
    return {
      user: {},
      invalidCredentials: false
    };
  },
};
</script>