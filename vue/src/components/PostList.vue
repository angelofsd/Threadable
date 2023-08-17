<template>
  <div id="post-list">
      <h3>Posts</h3>
      <div class="post" v-for="post in posts" v-bind:key="post.id">
        <div>
          <router-link
            v-bind:to="{ name: 'post', params: { id: post.postId } }"
          >{{ post.title }}</router-link>
        </div>
        <p>{{post.body}}</p>
      </div>
  </div>
</template>

<script>
import PostService from "../services/PostService.js";
import UserService from '../services/UserService.js';


export default {
  name: "post-list",
  data() {
    return {
      posts: [],
    };
  },
  created() {
    this.getPosts();
  },
  methods: {
    getPosts() {
      PostService.getHotPosts()
      .then( (response) => {
         if (response.status === 200) {
           this.posts = response.data;
         }
       })
       .catch( (error) => {
         if (error.response) {
           alert("Something went wrong: " + error.response.statusText);
         } else if(error.request){
                    //We could not reach the server
                    alert("We could not reach the server");
         } else {
                    alert("Something went horribly wrong");
                }
       })
    },
    getUsername(post) {
      UserService.getUserById(post.userId)
      .then((response) => {
        if (response.status === 200) {
          post.username = response.data.username;
          this.$forceUpdate();
        }
      })
      .catch( (error) => {
         if (error.response) {
           alert("Something went wrong: " + error.response.statusText);
         } else if(error.request){
                    //We could not reach the server
                    alert("We could not reach the server");
         } else {
                    alert("Something went horribly wrong");
                }
       })
    }
    
  }
};
</script>

<style>

#post-list {
  margin: 20px;
}

.post {
  background-color: rgb(200, 216, 246);
  color: black;
  border: solid 1px #e9e9f3;
  box-shadow: 1px 2px #e5e5f6;
  border-radius: 10px;
  margin: 20px 0px;
  padding: 5px;

}

.post:hover {
  border: solid 1px #07a3eb;
  box-shadow: 1px 2px #07a3eb;
}

.post p {
  font-size: 14px;
}

.post a {
  border-bottom: solid 1px #555597;
}

.post a:hover {
  border-bottom: solid 1px #978555;
}

</style>
