<template>
  <div class="post-details">
    <div class="post" >
      <div id="post-subheader">
        <h1>{{ post.title }}</h1>
        <LikeAndDislike :postId="postId" :post="post" />
        
      </div>
        <p>{{post.body}}</p>
        <small>{{post.username}} on {{formatDate(post.dateCreated)}}</small>
        <button class="delete-button" v-on:click="deletePost(post.id)" >Delete Post</button>
    </div>
    <div><reply-list v-bind:postId="postId" /></div>
    <div><create-reply /></div>
  </div>
</template>

<script>
import PostService from "../services/PostService.js";
import CreateReply from "../components/CreateReply.vue"
import ReplyList from "../components/ReplyList.vue"
import LikeAndDislike from './LikeAndDislike.vue';
import UserService from '../services/UserService.js';


export default {
  name: "post-details",
  
  props: [
    "postId"
  ],
  data() {
    return{
      post: {},
    }
  },
  components: {
    CreateReply, ReplyList, LikeAndDislike
  },
  methods: {
    getUsername(post) {
      UserService.getUserById(post.userId)
        .then((response) => {
          post.username = response.data.username; // Set username to reply object
          this.$forceUpdate(); // Force re-render
        })
        .catch((error) => {
          console.error('An error occurred:', error);
        });
    },
    deletePost() {
      PostService.deletePost(this.postId)
      .then(() => {
                const route = {
                    name: "hot",
                };

                this.$router.push(route);
      })
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return date.toLocaleDateString('en-US', options);
    },
    setLikes() {
       if(this.$store.state.token != '') {
        PostService.getLikedPostsByUserId(this.$store.state.user.id).then((response) => {
          this.$store.commit("SET_LIKED_POSTS", response.data)
        })
        PostService.getDislikedPostsByUserId(this.$store.state.user.id).then((response) => {
          this.$store.commit("SET_DISLIKED_POSTS", response.data)
        })
      }
    },
    getPost() {
      PostService
      .get(this.postId)
      .then(response => {
        this.$store.commit("SET_ACTIVE_POST", response.data);
        this.post = response.data;
        this.getUsername(this.post);
      })
      .catch(error => {
        if (error.response.status == 404) {
          this.$router.push({name: 'NotFound'});
        }
      });
     
    
    }
    
  },
  created() {
    this.getPost()
    this.setLikes()

  },
}

</script>

<style scoped>
.post {
 padding: 10px;
}
/** page structure **/
#post-subheader{
  display:flex;
  justify-content: space-between;
}
.post-details {
  padding: 20px 20px;
  margin: 0 auto;
  max-width: 600px;
}

button {
    float: right;
    width: 100px;
      color: rgb(255, 255, 255);
      font-size: 14px;
      line-height: 14px;
      padding: 1px 3px;
      margin-right: 3px;
      margin-bottom: 3px;
      border-radius: 6px;
      background-color: rgb(106, 126, 170);
      box-shadow: rgb(68, 88, 94) 1px 2px 2px 2px;
      border: 2px solid rgb(107, 111, 114);
}

button:hover {
     
      color: rgb(255, 255, 255);
      font-size: 14px;
      line-height: 14px;
      padding: 1px 3px;
      margin-right: 3px;
      margin-bottom: 3px;
      border-radius: 6px;
      background-color: rgb(78, 79, 82);
      box-shadow: rgb(68, 88, 94) 1px 2px 2px 2px;
      border: 2px solid rgb(107, 111, 114);
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
