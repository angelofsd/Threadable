<template>
  <div class="profile">
    <div id="profile-image">
      <img v-bind:src="user.profilePic" />
      <button>Edit</button>
    </div>
        <h1>{{user.username}}</h1>
    <div id="bio">
      <p>{{user.bio}}</p>
      <button>Edit</button>
    </div>
  </div>
</template>

<script>
import userService from '../services/UserService'
export default {
  name: 'user-details',
  userId: 0,
  data() {
    return {
      user:{
        id: 0,
        username: "",
        profilePic: "",
        bio: "",
        authorities: []
      }
    }
  },
  created() {
    this.userId = this.$route.params.id

    userService.getUserById(this.userId).then((response) => {
      this.user = response.data
    })
  }
}
</script>

<style>
  #profile {
    display: flex;
  }

  #profile-image img {
    width: 150px;
    border: solid 2px ;
    border-radius: 50%;
  }
</style>