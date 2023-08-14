<template>
  <div>
    <h3>Posts in Forum: {{this.forumId}}</h3>
    <h3 id="sort-by">Sort Posts By:</h3>
    <!-- Still need to find out to refresh if on same URL -->
    <h3 class="options"><router-link v-bind:to="{ name: 'forumPageByNew', params: {id: this.forumId}}">New</router-link></h3>
    <h3 class="options"><router-link v-bind:to="{ name: 'forumPage', params: {id: this.forumId}}">Popular</router-link></h3>
    <div v-for="post in posts" :key="post.id" class="post">
      <div id="post-subheader">
        <h4><router-link v-bind:to="{ name: 'post', params:{id: post.postId} }">{{ post.title }}</router-link></h4>
        <LikeAndDislike v-bind:postId="post.postId" />
      </div>
      <p>{{ post.body }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import LikeAndDislike from './LikeAndDislike.vue';

export default {
  props: {
    forumId: {

      required: true,
    },
     forumName: { 

    required: true, 
  },
  },
  components: {
    LikeAndDislike
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
    }
  }
};
</script>

<style scoped>
  #post-subheader {
    display: flex;
    justify-content: space-between;
  }
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
  h3 {
    display: inline-block;
    width: 42%;
  }
  #sort-by {
    text-align: right;
  }
  .options {
    width: 8%;
    text-align: center;
  }
  
</style>
