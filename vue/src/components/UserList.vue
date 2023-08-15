<template>
  <div id="sidebar">
      <h3>List of Moderators</h3>
          <div class="mod-list" v-for="mod in mods" v-bind:key="mod.id">
            <h4 class="username-list"> {{mod.username}}</h4>
          </div>
      <h3>List of Users</h3>
          <div class="user-list" v-for="user in users" v-bind:key="user.id">
            <h4 class="username-list"> {{user.username}}</h4>
            <button class="promote-moderator" v-on:click="updateUser(user.id, this.$route.params.id)" >Promote to Moderator</button>
          </div>
  </div>
</template>

<script>
import UserService from '../services/UserService.js'

export default {
    name: 'mod-list',
    props: {
        userId: {
            username: ""
        }
    },
    data() {
        return {
            users: [],
            mods: [],
            username: "",
            forumId: this.$route.params.id
        }
    },
    methods: {
        getModerators() {
            UserService.getModsByForumId(this.$route.params.id).then((response) => {
                if (response.status === 200) {
                    this.mods = response.data
                }
            })
        },
        getUsers() {
            UserService.getUsersByFavorited(this.$route.params.id).then((response) => {
                if (response.status === 200) {
                    this.users = response.data
                }
            })
        },
        updateUser(userId, forumId) {
            UserService.makeModerator(userId, forumId).then((response) => {
                if (response.status === 201) {
                    console.log("Successfully posted!");
                }
            })
            .catch((error) => {
                if(error.response) {
                    //We found the server and got a response back
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
    created() {
        this.getModerators();
        this.getUsers();
    }

}
</script>

<style>

</style>