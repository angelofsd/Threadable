<template>
  <form>
      <div>
        <label for="forumName">Name of Forum: </label>
        <input type="text" name="postTitle" v-model="forum.name"/>
      </div>
      <div>
        <label for="forumDescription">Description: </label>
        <input type="text" name="forumDescription" v-model="forum.description"/>
      </div>
      <div class="saveForum">
          <button type="submit" :click="saveForum">Save Forum</button>
      </div>
  </form>
</template>

<script>
import ForumService from '../services/ForumService.js'

export default {
    name: "create-forum",
    data() {
        return {
            forum: {
                
                name: "",
                description: "",
                dateCreated: "",
                createdBy: ""
            }
        }
    },
    methods: {
        saveForum() {
            ForumService.create(this.forum).then((response) => {
                if (response.status === 200) {
                    alert("Successfully created a forum!")
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

<style scoped>

</style>