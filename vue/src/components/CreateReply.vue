<template>
  <div>
    <h3>Create a Reply:</h3>
    <textarea v-model="replyBody" placeholder="Enter your reply here..." rows="4" cols="50"></textarea>
    <button v-on:click="createReply">Submit Reply</button>
  </div>
</template>

<script>
import ReplyService from '../services/ReplyService';

export default {
  props: {
    postId: {
      type: Number,
      required: true,
    },
  },
  computed: {
      userId() {
          return this.$store.state.user.id;
      }
  },
  data() {
    return {
      replyBody: '',
    };
  },
  methods: {
    createReply() {
        
        const reply = {
            text: this.replyBody,
            postId: this.postId,
            userId: this.userId
        }
      ReplyService.createReply(this.postId, reply)
      .then( (response) => {
          if (response.status === 201) {
              console.log("Succesfully posted!")
              //maybe reset the reply body here or navigate somewhere else?
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
    },
  },
};
</script>

<style scoped>
/* Add any required styling here */
</style>
