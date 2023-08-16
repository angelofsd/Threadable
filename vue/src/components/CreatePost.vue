<template>
  <form v-on:submit.prevent>
    <h3>Create Post</h3>
    <div class="field">
      <label for="postTitle">Title</label>
      <input type="text" name="postTitle" v-model="post.title" />
    </div>
    <div class="field" >
      <label for="postText">Body</label>
      <textarea name="postBody" id="body" v-model="post.body"></textarea>
    </div>
    <div class="field">
      <label for="image">Image URL</label>
      <input class="image-input" type="text" placeholder="Image URL" v-model="post.imageURL" />
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
        forumId: this.$route.params.id,
        userId: this.$store.state.user.id,
        liked: null,
      },
    };
  },
  methods: {
    savePost() {
      PostService.createPost(this.post)
        .then((response) => {
          if (response.status === 201) {
            console.log("Successfully posted!");
            //maybe reset the reply body here or navigate somewhere else?
          }
        })
        .catch((error) => {
          if (error.response) {
            alert("Something went wrong: " + error.response.statusText);
          } else if (error.request) {
            alert("We could not reach the server");
          } else {
            alert("Something went horribly wrong");
          }
        });
    },
  },
};
</script>

<style scoped>


  .field {
    margin-bottom: 10px;
    
    display: flex;
    flex-direction: column;
  }

  label {
    font-weight: bold;
    margin-bottom: 5px;
  }

  input[type="text"],
  textarea {
    width: 85%;
    padding: 5px;
    border: 1px solid rgb(41, 34, 34);
    border-radius: 4px;
  }

  textarea {
    height: 100px; 
    
  }

  .image-input {
    width: 100%;
  }

  .actions {
    margin-top: 20px;
  }

  #body {
    margin-left: 10px;
  }

  button {
    background-color: #5390b3;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }

  button:hover {
    background-color: #225979;
  }
</style>
