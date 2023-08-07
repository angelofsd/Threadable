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
      <button type="submit" v-on:click="updatePost()">Save Post</button>
    </div>
  </form>
</template>

<script>
import PostService from "../services/PostService";

export default {
  name: "update-post",
  props: ["postId"],
  data() {
    return {
      title: "",
      postText: ""
    };
  },
  methods: {
    updatePost() {
      const post = {
        id: this.postId,
        
        title: this.title,
        body: this.body
      };
      
      PostService.updatePost(post)
      .then((response) => {
                
                
                const id = response.data.id;
                
                const route = {
                    name: "Posts",
                    params: {
                        id: id,
                        
                        
                    }
              
                };

                this.$router.push(route);

            })
    }
  },
  created() {
    PostService
      .get(this.id)
      .then(response => {
        this.$store.commit("SET_ACTIVE_POST", response.data);
        this.title = response.data.title;
        this.body = response.data.body;
      })
      .catch(error => {
        if (error.response.status == 404) {
          this.$router.push({name: 'NotFound'});
        }
      });
  }
};
</script>

<style>
</style>
