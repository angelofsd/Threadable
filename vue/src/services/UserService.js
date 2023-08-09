import axios from 'axios';

const API_URL = '/users';

export default {
  
  getUsers() {
    return axios.get(API_URL);
  },

  getUserById(userId) {
    return axios.get(`${API_URL}/${userId}`);
  },

  updateUser(user) {
    return axios.put(API_URL, user);
  },

}