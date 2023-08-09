<template>
  <div id="profile">
    <div id="profile-image-section">
      <div id="profile-image">
        <img v-bind:src="user.profilePic" />
      </div>
      <button>Edit</button>
      <h1>{{user.username}}</h1>
    </div>
    <div id="bio-section">
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
        bio: "No bio",
        authorities: []
      }
    }
  },
  created() {
    this.userId = this.$route.params.id

    userService.getUserById(this.userId).then((response) => {
      this.user.id = response.data.id;
      this.user.username = response.data.username;
      this.user.profilePic = response.data.profilePic;
      if (response.data.bio) {
        this.user.bio = response.data.bio;
      }
      this.user.authorities = response.data.autorities;
    })
  }
}
</script>

<style scoped>

  #profile {
    display: flex;
  }

  #profile-image-section {
    text-align: center;
  }

  #bio-section {
    margin-top: 40px;
    margin-left: 30px;
  }

  #profile-image {
    margin: 15px;
    border: solid 2px #555597;
    border-radius: 50%;
    height: 150px;
    width: 150px;
    overflow: hidden;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  #profile-image img {
    width: 150px;
  }
</style>