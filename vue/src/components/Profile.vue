<template>
  <div>
    <div id="profile">
      <div id="profile-image-section">
        <div id="profile-image">
          <img v-bind:src="user.profilePic" />
        </div>
        <button v-show="isCurrentUserProfile">Edit</button>
        <h1>{{user.username}}</h1>
      </div>
      <div id="bio-section">
        <p>{{user.bio}}</p>
        <button v-show="isCurrentUserProfile">Edit</button>
      </div>
    </div>
  <Activity></Activity>
  </div>
</template>

<script>
import userService from '../services/UserService'
import Activity from './Activity.vue'
export default {
  components: { Activity },
  name: 'user-details',
  userId: 0,
  data() {
    return {
      isCurrentUserProfile: true,
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

    this.isCurrentUserProfile = (parseInt(this.$route.params.id) === this.$store.state.user.id)
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