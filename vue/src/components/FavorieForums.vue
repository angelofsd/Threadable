<template>
  <div id="favorited-list">
      <h3>Favorited</h3>
      <div class="forum" v-for="forum in forums" v-bind:key="forum.id">
        <div id="forum-subheader">
          <router-link
            v-bind:to="{ name: 'forumPage', params: { id: forum.id } }"
          >{{ forum.name }}</router-link>
          <FavoriteButton :forumId="forum.id"></FavoriteButton>
        </div>
        <p>{{forum.description}}</p>
      </div>
  </div>
</template>

<script>
import UserService from '../services/UserService.js';
import FavoriteButton from '../components/FavoriteButton.vue'
import PostService from '../services/PostService.js';
import ForumService from '../services/ForumService.js';

export default {
  name: "favorited",
  components: {FavoriteButton},
  
  data() {
    return {
      forums: [],
    };
  },
  created() {
    
    PostService.getLikedPostsByUserId(this.$store.state.user.id).then((response) => {
      this.$store.commit("SET_LIKED_POSTS", response.data)
    })
    //Need to move this somewhere else so it updates more often
    ForumService.getFavorited(this.$store.state.user.id).then((response) => {
      if (response.status === 200) {
        this.forums = response.data;
        this.$store.commit("SET_FAVORITED_FORUMS", response.data)
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

#favorited-list {
  margin: 20px;
}

#forum-subheader {
  display: flex;
  justify-content: space-between;
}

.forum {
  border: solid 1px #555597;
  box-shadow: 1px 2px #555597;
  border-radius: 10px;
  margin: 20px 0px;
  padding: 5px;

}

.forum:hover {
  border: solid 1px #978555;
  box-shadow: 1px 2px #978555;
}

.forum p {
  font-size: 14px;
}

.forum a {
  border-bottom: solid 1px #555597;
}

.forum a:hover {
  border-bottom: solid 1px #978555;
}

</style>