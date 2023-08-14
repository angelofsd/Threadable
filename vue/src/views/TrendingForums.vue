<template>
  <div class="trending-forums"> 
    <trending-forum/>
  </div>
</template>


<script>
import TrendingForum from '../components/TrendingForum.vue'
import ForumService from '../services/ForumService';

export default {
  name: "trending-forums",
  components: {
    TrendingForum
  },
  data() {
    return {
      forum: {},
      forumName:"",
      invalidCredentials: false
    };
  },
  created() {
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
};
</script>