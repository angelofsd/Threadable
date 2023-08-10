<template>
  <div>
    <h3>Posts in Forum: {{this.forumId}}</h3>
    <div v-for="post in posts" :key="post.id" class="post">
      <h4>{{ post.title }}</h4>
      <p>{{ post.body }}</p>
      <button @click="replyToPost(post.id)">Reply</button>
      <button @click="deletePost(post.id)">Delete Post</button>
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
     forumName: { 
    type: String,
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
  button {
      
      color: rgb(255, 255, 255);
      font-size: 12px;
      line-height: 12px;
      padding: 1px 3px;
      margin-right: 15px;
      border-radius: 6px;
      background-image: linear-gradient(to right, rgb(28, 110, 164) 0%, rgb(35, 136, 203) 50%, rgb(134, 174, 224)100%); 
     box-shadow: rgb(10, 86, 109) 2px 3px 3px 3px; border: 2px solid rgb(28, 110, 164);
      display: inline-block;
      }
</style>
