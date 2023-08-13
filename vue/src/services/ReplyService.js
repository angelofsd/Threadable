import axios from 'axios';

const API_URL = '/posts/details';

export default {
  
  listByPostId(postId) {
    return axios.get(`${API_URL}/${postId}/replies`);
  },

  getReplyById(postId, replyId) {
    return axios.get(`${API_URL}/${postId}/replies/${replyId}`);
  },

  createReply(postId, reply) {
    return axios.post(`${API_URL}/${postId}/replies`, reply);
  },

  updateReply(postId, replyId, reply) {
    return axios.put(`${API_URL}/${postId}/replies/${replyId}`, reply);
  },

  deleteReply(postId, replyId) {
    return axios.delete(`${API_URL}/${postId}/replies/${replyId}`);
  },

  getHearted(postId, replyId) {
    const url = `${API_URL}/${postId}/replies/${replyId}/hearted`
    return axios.get(url);
  },
  
  setHearted(postId, replyId, userId) {
    const url = `${API_URL}/${postId}/replies/${replyId}/${userId}/hearted`
    return axios.post(url)
  },

  removeHearted(postId, replyId, userId) {
    const url = `${API_URL}/${postId}/replies/${replyId}/${userId}/hearted`
    return axios.delete(url)
  }
}



