<template>
  <div>
    <h3>Replies:</h3>
    <div v-for="reply in replies" :key="reply.id" class="reply">
      <p>{{ reply.text }}</p>
      <small>Posted by user {{ reply.username }} on {{ formatDate(reply.dateCreated) }}</small>
      <!-- TODO Optionally, add buttons/links to edit or delete replies -->
    </div>
    <!-- TODO Optionally(ask team), add a form to create a new reply -->
  </div>
</template>

<script>
import ReplyService from '../services/ReplyService';
import UserService from '../services/UserService';

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
      ReplyService.listByPostId(this.postId)
        .then((response) => {
          if (response.status === 200) {
            this.replies = response.data;
            this.replies.forEach((reply) => {
              this.getUsername(reply); // Fetch username for each reply
            });
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
     getUsername(reply) {
      UserService.getUserById(reply.userId)
        .then((response) => {
          reply.username = response.data.username; // Set username to reply object
          this.$forceUpdate(); // Force re-render
        })
        .catch((error) => {
          console.error('An error occurred:', error);
        });
    }
    
  },
};
</script>

<style scoped>


.reply {
  width: 90%;
  font-weight: 600;
  border: 3px solid #2BA8FA;
  border-radius: 5px;
  padding: 10px;
  margin: 10px 0;
  background-color: rgb(166, 199, 238);
}

.reply:hover {
  background-color: rgb(134, 174, 224);
}

small {
  font-weight: 700;
  color: #FFFFFF;


}

small:hover {
  background-color: rgb(134, 174, 224);
}
</style>
