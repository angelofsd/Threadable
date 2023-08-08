<template>
  <div class="forum">
    <h1>UserProfile</h1>
    <p>This is the bio</p>
  </div>
</template>

<script>
export default {
  name: "profile",
  components: {},
  data() {
    return {
      user: {},
      invalidCredentials: false
    };
  },
};
</script>