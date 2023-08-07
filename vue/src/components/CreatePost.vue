<template>
  <form v-on:submit.prevent>
    <div class="field">
      <label for="postTitle">Title</label>
      <input type="text" name="postTitle" v-model="post.title" />
    </div>
    <div class="field">
      <label for="postText">Text</label>
      <input type="textarea" name="postText" v-model="post.body" />
    </div>
    <div class="actions">
      <button type="submit" v-on:click="savePost()">Save Post</button>
    </div>
  </form>
</template>

<script>
import PostService from "../services/PostService";

export default {
  name: "create-post",
  props: ["postId"],
  data() {
    return {
      post: {
        id: 0,
        
        title: "",
        body: ""
      }
    };
  },
  methods: {
    savePost() {
      PostService.createPost(this.post)
            .then((response) => {
                
                
                const id = response.data.postId;
                const route = {
                    name: "Posts",
                    params: {
                        id: id,
                        
                    }
              
                };

                this.$router.push(route);

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
};
</script>

<style>
</style>