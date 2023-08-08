<template>
  <div class="post-details">
    <h1>{{ this.$store.state.activePost.title }}</h1>
    <router-link
      :to="{ name: 'AddReply', params: {postId: $store.state.activePost.id} }"
      class="addReply"
    >Add New Reply</router-link>
    <div
      v-for="reply in this.$store.state.activePost.replies"
      v-bind:key="reply.id"
      class="post-reply bubble"
    >
      
      <p class="reply-text">{{ reply.replytext }}</p>
      <router-link
        :to="{name: 'EditReply', params: {replyId: $store.state.activePost.id, replyId: reply.id} }"
        class="btnEditReply"
      >Edit</router-link>
      <button class="btnDeleteReply" v-on:click="deleteReply(message.id)">Delete</button>
    </div>
  </div>
</template>

<script>
import PostService from "../services/PostService.js";
import ReplyService from "../services/ReplyService.js";

export default {
  name: "post-details",
  props: {
    postId: Number
  },
  methods: {
    deleteMessage(id) {
      ReplyService.deleteReply(id);
      this.$store.commit("DELETE_REPLY", id);
    }
  },
  created() {
    PostService
      .get(this.postId)
      .then(response => {
        this.$store.commit("SET_ACTIVE_POST", response.data);
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
/** page structure **/
.post-details {
  padding: 20px 20px;
  margin: 0 auto;
  max-width: 600px;
}

/** ios1-ios6 bubbles **/
.post-details .bubble {
  box-sizing: border-box;
  width: auto;
  position: relative;
  clear: both;

  background: #95c2fd;
  background-image: -webkit-gradient(
    linear,
    left bottom,
    left top,
    color-stop(0.15, #bee2ff),
    color-stop(1, #95c2fd)
  );
  background-image: -webkit-linear-gradient(bottom, #bee2ff 15%, #95c2fd 100%);
  background-image: -moz-linear-gradient(bottom, #bee2ff 15%, #95c2fd 100%);
  background-image: -ms-linear-gradient(bottom, #bee2ff 15%, #95c2fd 100%);
  background-image: -o-linear-gradient(bottom, #bee2ff 15%, #95c2fd 100%);
  background-image: linear-gradient(to top, #bee2ff 15%, #95c2fd 100%);
  filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#95c2fd', endColorstr='#bee2ff');

  border: solid 1px rgba(0, 0, 0, 0.5);
  -webkit-border-radius: 20px;
  -moz-border-radius: 20px;
  border-radius: 20px;

  -webkit-box-shadow: inset 0 8px 5px rgba(255, 255, 255, 0.65),
    0 1px 2px rgba(0, 0, 0, 0.2);
  -moz-box-shadow: inset 0 8px 5px rgba(255, 255, 255, 0.65),
    0 1px 2px rgba(0, 0, 0, 0.2);
  box-shadow: inset 0 8px 5px rgba(255, 255, 255, 0.65),
    0 1px 2px rgba(0, 0, 0, 0.2);
  margin-bottom: 20px;
  padding: 6px 20px;
  color: #000;
  text-shadow: 0 1px 1px rgba(255, 255, 255, 0.8);
  word-wrap: break-word;
}


</style>
