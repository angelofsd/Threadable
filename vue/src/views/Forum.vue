<template>
  <div class="forum">
    <h1>Forum Title</h1>
    <p>This is the forum description</p>
  </div>
</template>

<script>
export default {
  name: "forum",
  components: {},
  data() {
    return {
      forum: {
        forumName: "",
        forumDescription: "",
        forumDateCreated: "",
        createdBy: 0,
      },
      invalidCredentials: false
    };
  },
};
</script>
