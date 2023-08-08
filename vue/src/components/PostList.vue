<template>
  <div class="post-list">
    <table>
      <thead>
        <tr>
          <th>Post</th>
          <th>Edit</th>
          <th>Delete</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="post in this.$store.state.posts" v-bind:key="post.id">
          <td width="60%">
            <router-link
              v-bind:to="{ name: 'Posts', params: { id: post.id } }"
            >{{ post.title }}</router-link>
          </td>
          <td>
            <router-link :to="{ name: 'EditPost', params: {id: post.id} }">Edit</router-link>
          </td>
          <td>
            <a href="#" v-on:click="deletePost(post.id)">Delete</a>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import PostService from "../services/PostService.js";

export default {
  name: "post-list",
  methods: {
    getPosts() {
      PostService.list().then(response => {
        this.$store.commit("SET_POSTS", response.data);
      });
    },
    deletePost(id) {
      PostService.deletePost(id);
      location.reload();
    }
  },
  created() {
    this.getPosts();
  }
};
</script>

<style>
.post-list {
  margin: 0 auto;
  max-width: 800px;
}
.post {
  font-size: 24px;
  border-bottom: 1px solid #f2f2f2;
  padding: 10px 20px;
}
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
