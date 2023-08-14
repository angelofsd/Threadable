<template>
  <div class="allForums">
    <create-forum/>
    <forum-list/>
    <create-post/>
  </div>
</template>

<script>
import CreateForum from '../components/CreateForum.vue';
import ForumList from '../components/ForumList.vue'
import CreatePost from '../components/CreatePost.vue'
import ForumService from '../services/ForumService';

export default {
  name: "allForums",
  components: {
    ForumList,
    CreateForum,
    CreatePost
  },
  data() {
    return {
      forum: {},
      forumName:"",
      invalidCredentials: false
    };
  },
  created() {
    if (this.$store.state.token != '') {
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
    }
  }
};
</script>
