<template>
  <div id="sidebar">
      <h3>List of Moderators</h3>
          <div class="modList" v-for="mod in mods" v-bind:key="mod.id">
            <h4 class="username-list"> {{mod.username}}</h4>
          </div>
      <h3>List of Users</h3>
          <div class="userList" v-for="user in users" v-bind:key="user.id">
            <h4 class="username-list"> {{user.username}}</h4>
            <button>Promote to Moderator</button>
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