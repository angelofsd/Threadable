<template>
  <form v-on:submit.prevent>
    <div class="field">
      <label for="postTitle">Title</label>
      <input type="text" name="postTitle" v-model="post.title" />
    </div>
    <div class="field">
      <label for="postText">Body</label>
      <input type="textarea" name="postBody" v-model="post.body" />
    </div>
    <div>
      <label for="image">Image</label>
      <input class="image-input" type="text" placeholder="Image" v-model="post.image" />
    </div>
    <div class="actions">
      <button type="submit" v-on:click="savePost">Save Post</button>
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
        postId: this.postId,
        title: "",
        body: "",
        imageURL: null,
        dateCreated: "",
        forumId: this.$route.params.id,
        userId: this.$store.state.user.id,
        liked: null
      }
    };
  },
  methods: {
    savePost() {
      PostService.createPost(this.post)
            .then((response) => {
                if (response.status === 201) {
              console.log("Succesfully posted!")
              //maybe reset the reply body here or navigate somewhere else?
            }
                
                // const id = response.data.postId;
                // const route = {
                //     name: "Posts",
                //     params: {
                //         id: id,
                        
                //     }
              
                // };

                // this.$router.push(route);

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