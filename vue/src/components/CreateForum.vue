<template>
  <form v-on:submit.prevent>
      <div>
        <label for="forumName">Name of Forum: </label>
        <input type="text" name="postTitle" v-model="forum.name"/>
      </div>
      <div>
        <label for="forumDescription">Description: </label>
        <input type="text" name="forumDescription" v-model="forum.description"/>
      </div>
      <div class="saveForum">
          <button type="submit" v-on:click="saveForum()">Save Forum</button>
      </div>
  </form>
</template>

<script>
import ForumService from '../services/ForumService.js'

export default {
    name: "create-forum",
    props:["forumId"],
    data() {
        return {
            forum: {
                id: this.forumId,
                name: '',
                description: '',
                createdBy: this.$store.state.user.id,
                dateCreated: ''
            }
        };
    },
    computed: {
      userId() {
          return this.$store.state.user.id;
      }
  },
    methods: {
        saveForum() {
            ForumService.create(this.forum).then((response) => {
                this.id = response.data.id;
                this.name = response.data.name;
                this.description = response.data.name;
                this.createdBy = response.data.createdBy;
                this.dateCreated = response.data.dateCreated;
                if (response.status === 200 || response.status === 201) {
                    console.log("Successfully created a forum!");
                }
            })
            .catch((error) => {
                if(error.response) {
                    
                    alert("Something went wrong: " + error.response.statusText);
                } else if(error.request){
                    
                    alert("We could not reach the server");
                } else {
                    alert("Something went horribly wrong");
                }
            })
        }
    }
}
</script>

<style>

</style>