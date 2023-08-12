import axios from 'axios';

const API_URL = '/posts';

export default {
  
  listByPostId(postId) {
    return axios.get(`${API_URL}/${postId}/replies`);
  },

  getReplyById(postId, replyId) {
    return axios.get(`${API_URL}/${postId}/replies/${replyId}`);
  },

  createReply(postId, reply) {
    return axios.post(`${API_URL}/details/${postId}/replies`, reply);
  },

  updateReply(postId, replyId, reply) {
    return axios.put(`${API_URL}/${postId}/replies/${replyId}`, reply);
  },

  deleteReply(postId, replyId) {
    return axios.delete(`${API_URL}/${postId}/replies/${replyId}`);
  }
}



