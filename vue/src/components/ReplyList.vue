<template>
  <div>
    <h3>Replies:</h3>
    <div v-for="reply in replies" :key="reply.id" class="reply">
      <p>{{ reply.text }}</p>
      <small>Posted by user {{ reply.username }} on {{ formatDate(reply.dateCreated) }}</small>
      <button v-show="reply.userId == $store.state.user.id" v-on:click="deleteReply(reply.id)" class="delete-button">Delete Reply</button>
    </div>
   
  </div>
</template>

<script>
import ReplyService from '../services/ReplyService';
import UserService from '../services/UserService';



export default {
  components: {
    
  },
  props: {
    postId: {
      
      required: false,
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
      deleteReply(replyId) {
      // I use postId since I have it as a prop
      ReplyService.deleteReply(this.postId, replyId)
      .then(() => {
        // Remove the reply from the replies array
        this.replies = this.replies.filter(reply => reply.id !== replyId);
      })
      .catch((error) => {
        alert('Failed to delete reply: ' + error);
      });
  },
     getReplies() {
      ReplyService.listByPostId(this.postId)
        .then((response) => {
          if (response.status === 200) {
            this.replies = response.data;
            this.replies.forEach((reply) => {
              this.getUsername(reply); // Fetch username for each reply
              console.log(reply)
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
  color: black;
  font-weight: 600;
  border: 3px solid #0a629c;
  box-shadow: 1px 2px;
  border-radius: 8px;
  padding: 10px;
  margin: 10px 0;
  background-image: linear-gradient(to top, #accbee 0%, #e7f0fd 100%);
}



small {
  font-weight: 800;
  color: #000000;


}

small:hover {
  font-weight: 700;
}

 .delete-button {
      float: right;
      color: rgb(255, 255, 255);
      width: 125px;
      font-size: 14px;
      line-height: 14px;
      padding: 1px 3px;
      border-radius: 6px;
      background-image: linear-gradient(to right, rgb(28, 110, 164) 0%, rgb(35, 136, 203) 50%, rgb(134, 174, 224)); 
      box-shadow: rgb(10, 86, 109) 2px 3px 3px 3px;
      border: 2px solid rgb(28, 110, 164);
      
      }
.delete-button:hover {
background: #1C6EA4; 
    }
</style>
