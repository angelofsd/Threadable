<template>
  <div>
    <h3>Posts in Forum: {{forum.name}}</h3>
    <h3 id="sort-by">Sort Posts By:</h3>
    <!-- Still need to find out to refresh if on same URL -->
    <h3 class="options"><router-link v-bind:to="{ name: 'forumPageByNew', params: {id: this.forumId}}">New</router-link></h3>
    <h3 class="options"><router-link v-bind:to="{ name: 'forumPage', params: {id: this.forumId}}">Popular</router-link></h3>
    <div v-for="post in posts" :key="post.postId" class="post">
      <div id="post-subheader">
          <div v-show="post.imageURL != null" id="img-div">
            <img :src="post.imageURL" />
          </div>
          <div id="body-and-likes">
            <div id="body">
              <router-link v-bind:to="{ name: 'post', params: { id: post.postId } }">
                {{ post.title }}
              </router-link>
              <p>{{post.body}}</p>
            </div>
            <LikeAndDislike :postId="post.postId" :post="post" />
          </div>
        </div>
        <small>posted by {{post.username}} on {{formatDate(post.dateCreated)}}</small>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import LikeAndDislike from './LikeAndDislike.vue';
import ForumService from '../services/ForumService.js';
import UserService from '../services/UserService';

export default {
  props: {
    forumId: {

      required: true,
    },
  
  },
  components: {
    LikeAndDislike
  },
  data() {
    return {
      posts: [],
      username: "",
      forum: {}
    };
  },
  created() {
    ForumService.get(this.forumId).then((response) => {
      this.forum = response.data;
    });
    this.getPostsByForum();
    
  },
  methods: {
    getPostsByForum() {
      axios.get(`/posts/forums/${this.forumId}`)
        .then((response) => {
          if (response.status === 200) {
            this.posts = response.data;
            this.posts.forEach((post) => {
              this.getUsername(post)
            })
          }
        })
        .catch((error) => {
          console.error('An error occurred:', error);
        });
    },
    getUsername(post) {
      UserService.getUserById(post.userId)
        .then((response) => {
          post.username = response.data.username; // Set username to reply object
          console.log(post.username)
          this.$forceUpdate(); // Force re-render
        })
        .catch((error) => {
          console.error('An error occurred:', error);
        });
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return date.toLocaleDateString('en-US', options);
    },
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





  #post-list {
  margin: 20px;
}

#post-subheader{
  display: flex;
}

#body-and-likes {
  display: flex;
  justify-content: space-between;
  flex: 1;
}

#body {
  margin-left: 15px;
}

.post {
  background-color: rgb(200, 216, 246);
  color: black;
  border: solid 1px #e9e9f3;
  box-shadow: 1px 2px #e5e5f6;
  border-radius: 10px;
  margin: 20px 0px;
  padding: 10px;

}

.post:hover {
  border: solid 1px #07a3eb;
  box-shadow: 1px 2px #07a3eb;
  transition-duration: 250ms;
}

.post:not(:hover) {
  transition-duration: 250ms;
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

#img-div {
  padding: 15px;
  border-right: solid 1px rgb(179, 179, 179);
}

img {
  height: 125px;
  border-radius: 5px;
}
  
</style>
