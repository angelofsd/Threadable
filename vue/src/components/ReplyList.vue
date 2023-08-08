<template>
  <div>
    <h3>Replies:</h3>
    <div v-for="reply in replies" :key="reply.id" class="reply">
      <p>{{ reply.text }}</p>
      <small>Posted by user {{ reply.userId }} on {{ formatDate(reply.dateCreated) }}</small>
      <!-- TODO Optionally, add buttons/links to edit or delete replies -->
    </div>
    <!-- TODO Optionally(ask team), add a form to create a new reply -->
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
  data() {
    return {
      replies: [],
    };
  },
  created() {
    this.getReplies();
  },
  methods: {
     formatDate(dateString) {
      const date = new Date(dateString);
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return date.toLocaleDateString('en-US', options);
    },
     getReplies() {
       ReplyService.listByPostId(this.postId).then( (response) => {
         if (response.status === 200) {
           this.replies = response.data;
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
    },
    
  },
};
</script>

<style scoped>


.reply {
  font-weight: 600;
  border: 3px solid #2BA8FA;
  border-radius: 4px;
  padding: 10px;
  margin: 10px 0;
  background-color: rgb(166, 199, 238);
}
</style>
