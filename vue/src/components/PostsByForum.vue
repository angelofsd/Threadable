<template>
  <div>
     <h3>Posts in Forum: r/{{this.forumName}}</h3>
    <div v-for="post in posts" :key="post.id" class="post">
  <h4 @click="toggleReplies(post.id)">{{ post.title }}</h4>
  <p>{{ post.body }}</p>
  
  <ReplyList v-if="currentPostId === post.id" :post-id="post.postId" />
      <button @click="replyToPost()">Reply</button>
      <button @click="deletePost(post.id)">Delete</button>
     
    </div>
  </div>
</template>

<script>

import ReplyList from './ReplyList.vue';
import PostService from '../services/PostService';

export default {
  components: {
    ReplyList, 
  },
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
      currentPostId: null,
      showReplies: false,
      
    };
  },
  created() {
    this.getPostsByForum();
    console.log(this.posts);
  },
  methods: {
      toggleReplies(postId) {
    if (this.currentPostId === postId) {
      this.currentPostId = null; // Hide if already showing
    } else {
      this.currentPostId = postId; // Show replies for clicked post
    }
  },
    getPostsByForum() {
      PostService.getPostsByForumId(this.forumId)
    .then((response) => {
      if (response.status === 200) {
        this.posts = response.data;
      }
    })
    .catch((error) => {
      console.error('An error occurred:', error);
    });
    },
    replyToPost() {
      
    },
    deletePost(postId) {
        postId; //placeholder
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
