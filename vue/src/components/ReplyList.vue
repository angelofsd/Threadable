<template>
  <div>
    <h3>Replies:</h3>
    <div v-for="reply in replies" :key="reply.id" class="reply">
      <p>{{ reply.text }}</p>
      <small>Posted by user {{ reply.userId }} on {{ reply.dateCreated }}</small>
      <!-- TODO Optionally, add buttons/links to edit or delete replies -->
    </div>
    <!-- TODO Optionally, add a form to create a new reply -->
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
     getReplies() {
       ReplyService.listByPostId().then( (response) => {
         if (response.status === 200) {
           this.replies = response.data;
         }
       })
       .catch( (error) => {
         if (error.response.status === 401) {
           console.error('Error requesting data')
         }
       })
    },
    
  },
};
</script>

<style scoped>

.reply {
  border: 1px solid #ccc;
  padding: 10px;
  margin: 10px 0;
}
</style>
