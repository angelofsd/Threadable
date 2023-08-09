<template>
  <div class="post-list">
    <table>
      <thead>
        <tr>
          <th>Post</th>
          <th></th>
          <!-- <th>Edit</th>
          <th>Delete</th> -->
        </tr>
      </thead>
      <tbody>
        <tr v-for="post in posts" v-bind:key="post.id">
          <td width="60%">
            <router-link
              v-bind:to="{ name: 'Posts', params: { id: post.id } }"
            >{{ post.title }}</router-link>
          </td>
          <td>{{post.body}}</td>
          <!-- <td>
            <router-link :to="{ name: 'EditPost', params: {id: post.id} }">Edit</router-link>
          </td>
          <td>
            <a href="#" v-on:click="deletePost(post.id)">Delete</a>
          </td> -->
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import PostService from "../services/PostService.js";

export default {
  name: "post-list",
  data() {
    return {
      posts: [],
    };
  },
  created() {
    this.getPosts();
  },
  methods: {
    getPosts() {
      PostService.getAllPosts()
      .then( (response) => {
         if (response.status === 200) {
           this.posts = response.data;
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
    
  }
};
</script>

<style>

.post:last-child {
  border: 0px;
}
table {
  text-align: left;
  width: 800px;
  border-collapse: collapse;
}
td {
  padding: 4px;
}
tbody tr:nth-child(even) {
  background-color: #f2f2f2;
}

.post-list a:link,
.post-list a:visited {
  color: blue;
  text-decoration: none;
}
.post-list a:hover {
  text-decoration: underline;
}
</style>
