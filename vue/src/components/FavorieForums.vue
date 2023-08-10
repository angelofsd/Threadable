<template>
  <div id="post-list">
      <h3>Favorites</h3>
      <div class="forum" v-for="forum in forums" v-bind:key="forum.id">
        <div>
          <router-link
            v-bind:to="{ name: 'Forum', params: { id: forum.id } }"
          >{{ forum.name }}</router-link>
        </div>
        <p>{{forum.description}}</p>
      </div>
  </div>
</template>

<script>
import ForumService from '../services/ForumService.js';
import UserService from '../services/UserService.js';


export default {
  name: "post-list",
  data() {
    return {
      forums: [],
    };
  },
  created() {
    ForumService.getFavorited(this.$store.state.user.id).then((response) => {
        if (response.status === 200) {
           this.forums = response.data;
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
</script>

<style>

#post-list {
  margin: 20px;
}

.post {
  border: solid 1px #555597;
  box-shadow: 1px 2px #555597;
  border-radius: 10px;
  margin: 20px 0px;
  padding: 5px;

}

.post:hover {
  border: solid 1px #978555;
  box-shadow: 1px 2px #978555;
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