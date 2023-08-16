<template>
  <div class="allForums">
    <forum-list/>
    <button @click="createNewForum ^= true">Create New Forum</button>
  <div v-show="createNewForum">
    <create-forum/>
  </div>
  </div>
</template>

<script>
import CreateForum from '../components/CreateForum.vue';
import ForumList from '../components/ForumList.vue'

import ForumService from '../services/ForumService';

export default {
  name: "allForums",
  components: {
    ForumList,
    CreateForum
  },
  methods: {
    clicked() {
      if (this.createNewForm == true) {
        this.createNewForm == false;
      } else if (this.createNewForm == false) {
        this.createNewForm == true;
      }
    }
  },
  data() {
    return {
      forum: {},
      forumName:"",
      invalidCredentials: false,
      createNewForum: false
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
  },
};
</script>
<style scoped>
button {
    background-color: #5390b3;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    width: 20%
  }
</style>