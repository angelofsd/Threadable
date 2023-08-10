<template>
  <div>
  </div>
</template>

<script>
import ForumService from '../services/ForumService.js'

export default {
    name: "create-forum",
    props: ["forumId"],
    data() {
        return {
            forum: {
                forumId: this.forumId,
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
                if (response.status === 201) {
                    console.log("Successfully created a forum!")
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