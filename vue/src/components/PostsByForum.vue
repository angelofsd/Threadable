<template>
  <div>
    <h3>Posts in Forum:</h3>
    <div v-for="post in posts" :key="post.id" class="post">
      <h4>{{ post.title }}</h4>
      <p>{{ post.body }}</p>
      <!-- You can add more details as needed -->
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: {
    forumId: {
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      posts: [],
    };
  },
  created() {
    this.getPostsByForum();
  },
  methods: {
    getPostsByForum() {
      axios.get(`/forums/${this.forumId}/posts`)
        .then((response) => {
          if (response.status === 200) {
            this.posts = response.data;
          }
        })
        .catch((error) => {
          console.error('An error occurred:', error);
        });
    },
  },
};
</script>

<style scoped>
 
</style>
