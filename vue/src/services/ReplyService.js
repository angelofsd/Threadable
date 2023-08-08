import axios from 'axios';

export default {

  createReply(postId) {
    return axios.post(url)
  },

  getReplies(postId) {
    return axios.get('/posts/${postId}/replies');
  }

}
