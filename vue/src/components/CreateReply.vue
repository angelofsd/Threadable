<template>
  <div>
    <h3>Create a Reply:</h3>
    <textarea v-model="replyBody" placeholder="Enter your reply here..." rows="6" cols="65" class="text-box"></textarea>
    <button v-on:click="createReply">Submit Reply</button>
  </div>
</template>

<script>
import ReplyService from '../services/ReplyService';

export default {
  props: ["postId"],
  computed: {
      userId() {
          return this.$store.state.user.id;
      },
    
  },
  data() {
    return {
      reply: {},
      replyBody: ""
    };
  },
  methods: {
    createReply() {
        
        const reply = {
            text: this.replyBody,
            postId: this.$route.params.id,
            userId: this.userId
        }
      ReplyService.createReply(this.$route.params.id, reply)
      .then( (response) => {
          if (response.status === 201) {
            this.reply = response.data;
            console.log("Succesfully posted!")
            location.reload(); 
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

</style>
