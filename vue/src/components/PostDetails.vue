<template>
  <div class="post-details">
    <div class="post" >
        <h1>{{ post.title }}</h1>
        <p>{{post.body}}</p>
        
        <button class="mark-liked-button" v-on:click.prevent="setLike(true)">Mark Like</button> |
        <button class="mark-unliked-button" v-on:click.prevent="setLike(false)" >Mark Unlike</button> |
        <button class="delete-button" v-on:click="deletePost(post.id)" >Delete</button>
    </div>
    <div><reply-list v-bind:postId="postId" /></div>
    <div><create-reply/></div>
  </div>
</template>

<script>
import PostService from "../services/PostService.js";
import CreateReply from "../components/CreateReply.vue"
import ReplyList from "../components/ReplyList.vue"
//import ReplyService from "../services/ReplyService.js";

// import UserService from '../services/UserService.js';


export default {
  name: "post-details",
  props: [
    "postId" 
  ],
  data() {
    return{
      post: {}
    }
  },
  components: {
    CreateReply, ReplyList
  },
  methods: {
    getUsername() {
      
    },
    setLike(value) {
      this.$store.commit('SET_LIKE_STATUS', {post: this.post, value: value});
    },
    deletePost(postId) {
      PostService.deletePost(postId);
      this.$store.commit("DELETE_POST", postId);
    }
  },
  created() {
    PostService
      .get(this.postId)
      .then(response => {
        this.$store.commit("SET_ACTIVE_POST", response.data);
        this.post = response.data;
      })
      .catch(error => {
        if (error.response.status == 404) {
          this.$router.push({name: 'NotFound'});
        }
      });
  },
  };
  


</script>

<style>
/** page structure **/
.post-details {
  padding: 20px 20px;
  margin: 0 auto;
  max-width: 600px;
}

/** ios1-ios6 bubbles **/
.post-details .bubble {
  box-sizing: border-box;
  width: auto;
  position: relative;
  clear: both;

  background: #95c2fd;
  background-image: -webkit-gradient(
    linear,
    left bottom,
    left top,
    color-stop(0.15, #bee2ff),
    color-stop(1, #95c2fd)
  );
  background-image: -webkit-linear-gradient(bottom, #bee2ff 15%, #95c2fd 100%);
  background-image: -moz-linear-gradient(bottom, #bee2ff 15%, #95c2fd 100%);
  background-image: -ms-linear-gradient(bottom, #bee2ff 15%, #95c2fd 100%);
  background-image: -o-linear-gradient(bottom, #bee2ff 15%, #95c2fd 100%);
  background-image: linear-gradient(to top, #bee2ff 15%, #95c2fd 100%);
  filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#95c2fd', endColorstr='#bee2ff');

  border: solid 1px rgba(0, 0, 0, 0.5);
  -webkit-border-radius: 20px;
  -moz-border-radius: 20px;
  border-radius: 20px;

  -webkit-box-shadow: inset 0 8px 5px rgba(255, 255, 255, 0.65),
    0 1px 2px rgba(0, 0, 0, 0.2);
  -moz-box-shadow: inset 0 8px 5px rgba(255, 255, 255, 0.65),
    0 1px 2px rgba(0, 0, 0, 0.2);
  box-shadow: inset 0 8px 5px rgba(255, 255, 255, 0.65),
    0 1px 2px rgba(0, 0, 0, 0.2);
  margin-bottom: 20px;
  padding: 6px 20px;
  color: #000;
  text-shadow: 0 1px 1px rgba(255, 255, 255, 0.8);
  word-wrap: break-word;
}


</style>
